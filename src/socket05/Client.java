package socket05;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private Socket socket;
	
	public Client() {
		try {
			System.out.println("正在连接服务器....");
			socket = new Socket("192.168.1.14",9000);
			
			System.out.println("连接服务器成功！端口号："+socket.getPort());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		try {
			Scanner scanner = new Scanner(System.in);
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw, true);
			while(true) {
				String line = scanner.nextLine();
				if(!line.equals("")) {
//					if(line.equals("bye")) {
//						pw.println(line);
//						socket.close();
//					}
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
}
