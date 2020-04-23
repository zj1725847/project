package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
	
	public Server() {
		try {
			/*
			 * 实例化ServerSocket的同时要向系统
			 * 申请服务端口，客户端就是通过这个端
			 * 口与服务端建立连接的。
			 */
			System.out.println("正在启动服务端...");
			server = new ServerSocket(8088);
			System.out.println("服务端启动完毕!端口号为："+server.getLocalPort());
			
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
			System.out.println("等待客户端连接...");
			Socket socket = server.accept();
			System.out.println("一个客户端连接了!");
			
			/*
			 * 通过Socket获取输入流，可以读取到远端
			 * 计算机发送过来的字节。
			 */
			InputStream in = socket.getInputStream();
			InputStreamReader isr
				= new InputStreamReader(in,"UTF-8");
			BufferedReader br
			 	= new BufferedReader(isr);
			
			String line = br.readLine();
			System.out.println("客户端说:"+line);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
}


















