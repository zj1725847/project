package socket04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 聊天室客户端
 * @author ta
 *
 */
public class Client {
	/*
	 * java.net.Socket 套接字
	 * 封装了TCP协议的通讯细节，使得我们可以通过它
	 * 来与远端计算机建立TCP连接，并利用两个流的读写
	 * 完成数据交互。
	 */
	private Socket socket;
	/**
	 * 构造方法，用来初始化客户端
	 */
	public Client() {
		try {
			/*
			 * Socket实例化是需要传入两个参数
			 * 参数1:服务端IP地址
			 * 参数2:服务端程序申请的端口
			 * 我们通过IP可以找到网络上的服务端计算机
			 * 通过端口可以连接到服务端计算机上运行的
			 * 服务端应用程序了。
			 * 注意:
			 * 实例化Socket的过程就是连接的过程，若
			 * 服务端没有响应这里会抛出异常。
			 */
			System.out.println("正在连接服务端...");
			socket = new Socket("192.168.1.14",8088);
			System.out.println("与服务端成功建立连接!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户端开始工作的方法
	 */
	public void start() {
		try {
			//先启动用于读取服务端发送过来消息的线程
			ServerHandler handler = new ServerHandler();
			Thread t = new Thread(handler);
			t.start();
			
			Scanner scanner = new Scanner(System.in);
			/*
			 * Socket提供的方法:
			 * OutputStream getOutputStream()
			 * 该方法获取的输出流是一个字节输出流
			 * 通过这个流写出的字节会通过网络发送
			 * 到远端计算机上(对于客户端这边而言，
			 * 远端计算机指的就是服务端了。)
			 */
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw
				= new OutputStreamWriter(out,"UTF-8");
			BufferedWriter bw
				= new BufferedWriter(osw);
			PrintWriter pw
				= new PrintWriter(bw,true);
			
			while(true) {
				String line = scanner.nextLine();
				if(!line.equals("")) {
					pw.println(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
	
	/**
	 * 该线程负责循环读取服务端发送过来的消息并输出
	 * 到客户端的控制台上
	 * @author ta
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run() {
			try {
				//通过socket获取输入流，读取服务端发送过来的内容
				InputStream in = socket.getInputStream();
				InputStreamReader isr
					= new InputStreamReader(in,"UTF-8");
				BufferedReader br
				 	= new BufferedReader(isr);
				
				String line = null;
				while((line = br.readLine())!=null) {
					System.out.println(line);
				}
				
			} catch (Exception e) {
				
			}
		}
	}
}










