package dome1;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
//创建一个测试类
public class communicateTest {
 
	public static void main(String[] args) throws IOException {
		//创建一个服务器对象
		ServerSocket server=new ServerSocket(9000);
		//输出服务器的端口信息
		System.out.println("服务器创建成功，端口号为："+server.getLocalPort());
		while(true) {
			//创建一个Socket对象来连接,这里不需要新建一个对象，它只要直接引用server即可
			Socket socket=server.accept();
			
			//利用socket来接收输出输入流的数据
			//这里有一点需要注意，我们向输入流InputStream写入的数据会被发送到客户端，
			//而我们从输出流OutputStream读取的是客户端发送过来的数据
			OutputStream output=socket.getOutputStream();
			InputStream input=socket.getInputStream();
			
			//接着开始进行通信测试
			String s="Hello,Welcome to My ServerSocket!";
			//这条消息是当客户机连接上我们创建的服务器时，服务器发送给客户机的一条信息
			//也就是我们要向客户机发送消息，那么我们应该用的是OutputStream
			
			//首先我们要先将发送信息转化为byte类型，因为输出流的写入方法write()中的参数是byte类型
			byte[] dataout=s.getBytes();
			//接着调用输出流的写入方法，把信息发送给客户机
			output.write(dataout);
			//然后让这条信息在命令行中显示出来，以便我们检测信息是否真的已经被发送出去
			output.flush();
			
			//接收每一个来自客户机的字符
			int ascii=input.read();
			//如果接收到回车字符就结束循环
			while(ascii!=13) {
				char accept=(char) ascii;
				//输出客户机发出的，服务器收到的每一个字符
				System.out.print(accept);
				ascii=input.read();
			}
			
			//关闭连接
			socket.close();	
		}
	}
	
}