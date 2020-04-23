package socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �����ҷ����
 * @author ta
 *
 */
public class Server {
	/*
	 * java.net.ServerSocket 
	 * �����ڷ���˵�ServerSocket��Ҫ����������
	 * 1:��ϵͳ�������˿ڣ��ͻ��˾���ͨ������˿�
	 *   �����˽������ӵġ�
	 * 2:�����ö˿ڣ�����һ��һ���ͻ���ͨ���ö˿ڳ�
	 *   �Խ�������ʱ��ServerSocket�ͻ��Զ�ʵ����
	 *   һ��Socket����ôͨ�����Socket�Ϳ�����ͻ�
	 *   �˶ԵȲ��������ݽ����ˡ�  
	 */
	private ServerSocket server;
	
	public Server() {
		try {
			/*
			 * ʵ����ServerSocket��ͬʱҪ��ϵͳ
			 * �������˿ڣ��ͻ��˾���ͨ�������
			 * �������˽������ӵġ�
			 */
			System.out.println("�������������...");
			server = new ServerSocket(8088);
			System.out.println("������������!�˿ں�Ϊ��"+server.getLocalPort());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			/*
			 * Socket accept()
			 * ServerSocket��accept������һ������
			 * ���������ú��һֱ�ȴ���ֱ��һ���ͻ���
			 * ��������Ϊֹ����ʱ�÷����᷵��һ��Socket
			 * ʵ����ͨ�����Socket�Ϳ�����������ϵ�
			 * �ͻ��˽������ݽ����ˡ�
			 * ��ε���accept���Եȴ���ͬ�ͻ��˵�����
			 */
			System.out.println("�ȴ��ͻ�������...");
			Socket socket = server.accept();
			System.out.println("һ���ͻ���������!");
			
			/*
			 * ͨ��Socket��ȡ�����������Զ�ȡ��Զ��
			 * ��������͹������ֽڡ�
			 */
			InputStream in = socket.getInputStream();
			InputStreamReader isr
				= new InputStreamReader(in,"UTF-8");
			BufferedReader br
			 	= new BufferedReader(isr);
			
			String line = br.readLine();
			System.out.println("�ͻ���˵:"+line);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
}


















