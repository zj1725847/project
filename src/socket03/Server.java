package socket03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * 聊天室服务端
 * @author ta
 *
 */
public class Server {
	/*
	 * java.net.ServerSocket 
	 * 运行在服务端的ServerSocket主要有两个作用
	 * 1:向系统申请服务端口，客户端就是通过这个端口
	 *   与服务端建立连接的。
	 * 2:监听该端口，这样一旦一个客户端通过该端口尝
	 *   试建立连接时，ServerSocket就会自动实例化
	 *   一个Socket，那么通过这个Socket就可以与客户
	 *   端对等并进行数据交互了。  
	 */
	private ServerSocket server;
	/*
	 * 该数组用于保存所有ClientHandler对应客户端的
	 * 输出流，以便所有ClientHandler都可以互访这些
	 * 输出流来广播消息
	 */
	private PrintWriter[] allOut = {};
	
	public Server() {
		try {
			/*
			 * 实例化ServerSocket的同时要向系统
			 * 申请服务端口，客户端就是通过这个端
			 * 口与服务端建立连接的。
			 */
			System.out.println("正在启动服务端...");
			server = new ServerSocket(8088);
			System.out.println("服务端启动完毕!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			/*
			 * Socket accept()
			 * ServerSocket的accept方法是一个阻塞
			 * 方法，调用后会一直等待，直到一个客户端
			 * 建立连接为止，此时该方法会返回一个Socket
			 * 实例，通过这个Socket就可以与刚连接上的
			 * 客户端进行数据交互了。
			 * 多次调用accept可以等待不同客户端的连接
			 */
			while(true) {
				System.out.println("等待客户端连接...");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了!");
				
				//启动一个线程负责与该客户端交互
				ClientHandler handler
					= new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	/**
	 * 该线程任务是负责与指定客户端进行交互操作
	 * @author ta
	 *
	 */
	private class ClientHandler implements Runnable{
		private Socket socket;
		
		//客户端的地址信息
		private String host;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
			//获取远端计算机IP地址信息的字符串格式
			host = socket.getInetAddress().getHostAddress();
			
		}
		
		public void run() {
			PrintWriter pw = null;
			try {
				/*
				 * 通过Socket获取输入流，可以读取到远端
				 * 计算机发送过来的字节。
				 */
				InputStream in = socket.getInputStream();
				InputStreamReader isr
					= new InputStreamReader(in,"UTF-8");
				BufferedReader br
				 	= new BufferedReader(isr);
				
				/*
				 * 通过socket获取输出流，用于给客户端发送
				 * 消息。
				 */
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw
					= new OutputStreamWriter(out,"UTF-8");
				BufferedWriter bw
					= new BufferedWriter(osw);
				pw = new PrintWriter(bw,true);
				/*
				 * 将当前客户端对应的输出流存入allOut，便于
				 * 其他ClientHandler可以将消息转发给这个客户
				 * 端。
				 */
				synchronized (allOut) {
					//1 对allOut数组扩容
					allOut = Arrays.copyOf(allOut, allOut.length+1);
					//2 将输出流存入数组最后一个位置
					allOut[allOut.length-1] = pw;				
				}
				System.out.println(host+"上线了!");
				System.out.println("当前在线人数:"+allOut.length);
				
				for(int i=0;i<allOut.length;i++) {
					allOut[i].println(host+"上线了！");
				}
				
				String line = null;
				while((line = br.readLine())!=null) {
					System.out.println(host+"说:"+line);
					//将内容发送给客户端
					synchronized (allOut) {
						for(int i=0;i<allOut.length;i++) {
							allOut[i].println(host+"说:"+line);
						}
					}
				}
			} catch (Exception e) {
				
			} finally {
				//处理客户端断开连接后的操作
				
				//1将当前客户端输出流从allOut中删除
				synchronized (allOut) {
					for(int i=0;i<allOut.length;i++) {
						if(allOut[i]==pw) {
							allOut[i] = allOut[allOut.length-1];
							allOut = Arrays.copyOf(
									allOut, allOut.length-1);
							break;
						}
					}
				}
				System.out.println(host+"下线了!");
				System.out.println("当前在线人数:"+allOut.length);
				//2关闭socket,释放资源
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}


















