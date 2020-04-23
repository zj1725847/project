package socket05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket server;
	
	public Server() {
		try {
			System.out.println("正在启动服务器....");
			server = new ServerSocket(9000);
			System.out.println("服务器启动成功！端口号："+server.getLocalPort());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		try {
			while(true) {
				System.out.println("正在等待客户端连接....");
				Socket socket = server.accept();
				System.out.println("一个客户端连接成功！地址："+socket.getInetAddress().getHostAddress());
				InputStream in = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(in,"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while((line = br.readLine()) != null) {
					System.out.println(socket.getInetAddress().getHostAddress()+"说："+line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
}
