package socket;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * �����ҿͻ���
 * @author ta
 *
 */
public class Client {
	/*
	 * java.net.Socket �׽���
	 * ��װ��TCPЭ���ͨѶϸ�ڣ�ʹ�����ǿ���ͨ����
	 * ����Զ�˼��������TCP���ӣ��������������Ķ�д
	 * ������ݽ�����
	 */
	private Socket socket;
	/**
	 * ���췽����������ʼ���ͻ���
	 */
	public Client() {
		try {
			/*
			 * Socketʵ��������Ҫ������������
			 * ����1:�����IP��ַ
			 * ����2:����˳�������Ķ˿�
			 * ����ͨ��IP�����ҵ������ϵķ���˼����
			 * ͨ���˿ڿ������ӵ�����˼���������е�
			 * �����Ӧ�ó����ˡ�
			 * ע��:
			 * ʵ����Socket�Ĺ��̾������ӵĹ��̣���
			 * �����û����Ӧ������׳��쳣��
			 */
			System.out.println("�������ӷ����...");
			socket = new Socket("localhost",8088);
			System.out.println("�����˳ɹ���������!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ͻ��˿�ʼ�����ķ���
	 */
	public void start() {
		try {
			/*
			 * Socket�ṩ�ķ���:
			 * OutputStream getOutputStream()
			 * �÷�����ȡ���������һ���ֽ������
			 * ͨ�������д�����ֽڻ�ͨ�����緢��
			 * ��Զ�˼������(���ڿͻ�����߶��ԣ�
			 * Զ�˼����ָ�ľ��Ƿ�����ˡ�)
			 */
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw
				= new OutputStreamWriter(out,"UTF-8");
			BufferedWriter bw
				= new BufferedWriter(osw);
			PrintWriter pw
				= new PrintWriter(bw,true);
			
			
			String line = "���!";
			
			pw.println(line);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}










