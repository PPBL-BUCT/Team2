import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

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

	/**
	 * 广播方法
	 * @param str
	 */

	public void Broadcast(String str){
		Enumeration<ServerThread> allclients = clientlist.elements();

		while (allclients.hasMoreElements()){
		ServerThread st = (ServerThread) allclients.nextElement();
		{
			try{
				st.dos.writeUTF(str);

			}catch (IOException e){
				e.printStackTrace();
			}

			}
		}
	}

	/**
	 * 检查用户昵称是否重复
	 * @param str
	 */
	public boolean checkp(String str) {
		boolean flag = true;
		Enumeration<String> checkname = clientlist.keys();//取得枚举集合
		while (checkname.hasMoreElements()) {
			String sn = (String) checkname.nextElement();//遍历
			// 判断用户名是否重复，重复的话返回false
			if (str.equalsIgnoreCase(sn)) {
				flag = false;
				//检查是否为空字符串
			} else if (str.equalsIgnoreCase("")) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public void run() {

	}
}
