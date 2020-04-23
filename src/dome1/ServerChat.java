package dome1;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
//����һ��ͨ����
public class ServerChat {
	OutputStream output;//�����
	InputStream input;//������
	ServerSocket server;//����һ����������������
	
	//����һ�������������ķ���
	private void setUpServer(int port) throws IOException {
		//������Ķ˿�����Ϊ������
		server=new ServerSocket(port);
		//�����ǰ�������Ķ˿ں�
		System.out.println("�����������ɹ����˿ںţ�"+server.getLocalPort());
		//����һ����Ϊ�н�Ľ��ն���Socket
		Socket socket=server.accept();
		
		//Ϊ�����������ֵ
		output=socket.getOutputStream();
		input=socket.getInputStream();
		
		//��ʼͨ��
		//������Ϣ���ͻ���
		String outS="Hello,welcome to my ServerSocket!\r\n";
		out(outS);
		
		//������Ϣ��������
		ReadString();
	}	
	
	
	
	//����һ�������Ϣ���ͻ����ķ���
	private void out(String outS) throws IOException {
		//���ַ���ת��Ϊbyte����
		byte[] dataout=outS.getBytes();
		//����write()����Ϣ���Ϳͻ���
		output.write(dataout);
		//ǿ������������еĽ�����
		output.flush();
	}
 
	//����һ�������ַ������������ķ���
	public void ReadString() throws IOException {
		String inputS="";
		while(!inputS.equals("bye")) {
			//��ȡ��һ���ַ�
			int AsciiNumber=input.read();
			while(AsciiNumber!=13) {
				//��ascii��ת��Ϊ��Ӧ��char���ַ�
				inputS+=(char)AsciiNumber;
				//������һ���ַ�
				AsciiNumber=input.read();
			}
			System.out.println(inputS);
		}
		//�ر�����
		output.close();
	}
	
	//���������
	public static void main(String[] args) throws IOException {
		//����һ��ͨ����Ķ���
		ServerChat server=new ServerChat();
		server.setUpServer(9009);
	}
}