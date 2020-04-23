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
	/*
	 * ���������ڱ�������ClientHandler��Ӧ�ͻ��˵�
	 * ��������Ա�����ClientHandler�����Ի�����Щ
	 * ��������㲥��Ϣ
	 */
	private PrintWriter[] allOut = {};
	
	public Server() {
		try {
			/*
			 * ʵ����ServerSocket��ͬʱҪ��ϵͳ
			 * �������˿ڣ��ͻ��˾���ͨ�������
			 * �������˽������ӵġ�
			 */
			System.out.println("�������������...");
			server = new ServerSocket(8088);
			System.out.println("������������!");
			
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
			while(true) {
				System.out.println("�ȴ��ͻ�������...");
				Socket socket = server.accept();
				System.out.println("һ���ͻ���������!");
				
				//����һ���̸߳�����ÿͻ��˽���
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
	 * ���߳������Ǹ�����ָ���ͻ��˽��н�������
	 * @author ta
	 *
	 */
	private class ClientHandler implements Runnable{
		private Socket socket;
		
		//�ͻ��˵ĵ�ַ��Ϣ
		private String host;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
			//��ȡԶ�˼����IP��ַ��Ϣ���ַ�����ʽ
			host = socket.getInetAddress().getHostAddress();
			
		}
		
		public void run() {
			PrintWriter pw = null;
			try {
				/*
				 * ͨ��Socket��ȡ�����������Զ�ȡ��Զ��
				 * ��������͹������ֽڡ�
				 */
				InputStream in = socket.getInputStream();
				InputStreamReader isr
					= new InputStreamReader(in,"UTF-8");
				BufferedReader br
				 	= new BufferedReader(isr);
				
				/*
				 * ͨ��socket��ȡ����������ڸ��ͻ��˷���
				 * ��Ϣ��
				 */
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw
					= new OutputStreamWriter(out,"UTF-8");
				BufferedWriter bw
					= new BufferedWriter(osw);
				pw = new PrintWriter(bw,true);
				/*
				 * ����ǰ�ͻ��˶�Ӧ�����������allOut������
				 * ����ClientHandler���Խ���Ϣת��������ͻ�
				 * �ˡ�
				 */
				synchronized (allOut) {
					//1 ��allOut��������
					allOut = Arrays.copyOf(allOut, allOut.length+1);
					//2 ������������������һ��λ��
					allOut[allOut.length-1] = pw;				
				}
				System.out.println(host+"������!");
				System.out.println("��ǰ��������:"+allOut.length);
				
				for(int i=0;i<allOut.length;i++) {
					allOut[i].println(host+"�����ˣ�");
				}
				
				String line = null;
				while((line = br.readLine())!=null) {
					System.out.println(host+"˵:"+line);
					//�����ݷ��͸��ͻ���
					synchronized (allOut) {
						for(int i=0;i<allOut.length;i++) {
							allOut[i].println(host+"˵:"+line);
						}
					}
				}
			} catch (Exception e) {
				
			} finally {
				//����ͻ��˶Ͽ����Ӻ�Ĳ���
				
				//1����ǰ�ͻ����������allOut��ɾ��
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
				System.out.println(host+"������!");
				System.out.println("��ǰ��������:"+allOut.length);
				//2�ر�socket,�ͷ���Դ
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}


















