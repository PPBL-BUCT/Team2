import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {
	// 定义一个socket变量
	Socket client = null;
	// 定义一个Datainputstream变量
	DataInputStream di = null;
	// 定义一个Dataoutputstream变量
	DataOutputStream dos = null;
	// 定义一个变量保存连接当前线程的用户名
	String name = null;
	// 创建一个hashtable对象用来保存所有的为客户端开辟的线程对象
	static Hashtable<String, ServerThread> clientlist = new Hashtable<String, ServerThread>();

	@Override
	public void run() {

	}
}
