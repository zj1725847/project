package dome1;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
//����һ��������
public class communicateTest {
 
	public static void main(String[] args) throws IOException {
		//����һ������������
		ServerSocket server=new ServerSocket(9000);
		//����������Ķ˿���Ϣ
		System.out.println("�����������ɹ����˿ں�Ϊ��"+server.getLocalPort());
		while(true) {
			//����һ��Socket����������,���ﲻ��Ҫ�½�һ��������ֻҪֱ������server����
			Socket socket=server.accept();
			
			//����socket���������������������
			//������һ����Ҫע�⣬������������InputStreamд������ݻᱻ���͵��ͻ��ˣ�
			//�����Ǵ������OutputStream��ȡ���ǿͻ��˷��͹���������
			OutputStream output=socket.getOutputStream();
			InputStream input=socket.getInputStream();
			
			//���ſ�ʼ����ͨ�Ų���
			String s="Hello,Welcome to My ServerSocket!";
			//������Ϣ�ǵ��ͻ������������Ǵ����ķ�����ʱ�����������͸��ͻ�����һ����Ϣ
			//Ҳ��������Ҫ��ͻ���������Ϣ����ô����Ӧ���õ���OutputStream
			
			//��������Ҫ�Ƚ�������Ϣת��Ϊbyte���ͣ���Ϊ�������д�뷽��write()�еĲ�����byte����
			byte[] dataout=s.getBytes();
			//���ŵ����������д�뷽��������Ϣ���͸��ͻ���
			output.write(dataout);
			//Ȼ����������Ϣ������������ʾ�������Ա����Ǽ����Ϣ�Ƿ�����Ѿ������ͳ�ȥ
			output.flush();
			
			//����ÿһ�����Կͻ������ַ�
			int ascii=input.read();
			//������յ��س��ַ��ͽ���ѭ��
			while(ascii!=13) {
				char accept=(char) ascii;
				//����ͻ��������ģ��������յ���ÿһ���ַ�
				System.out.print(accept);
				ascii=input.read();
			}
			
			//�ر�����
			socket.close();	
		}
	}
	
}