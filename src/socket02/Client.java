package socket02;

import java.io.BufferedWriter;
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
			socket = new Socket("localhost",8088);
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
				pw.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}










