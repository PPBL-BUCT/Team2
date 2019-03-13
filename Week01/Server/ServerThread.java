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
	 * 私聊方法
	 * @param receiver
	 * @param mess
	 */
	public void sendClient(String receiver, String mess) {
		ServerThread st = clientlist.get(receiver);//接收者的线程
		ServerThread st1 = clientlist.get(name);//发送者的线程
		try {
			// 将要发送的信息保存到流中
			st.dos.writeUTF(getTime() + "\t" + name + "对你说：\t" + mess);

			// 把信息发给原客户端
			st1.dos.writeUTF(getTime() + "\t你对" + receiver + "说：\t" + mess);
		} catch (IOException e) {

			System.out.println("信息错误");
			st1.dos.writeUTF("你发送的信息有误，请重新发送！");
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

	/**
	 * 获取当前时间
	 * @return String类型时间
	 */
	public String getTime(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sd.format(new Date());
		return currentTime;

	}

	/**
	 * 获取当前在线用户列表
	 */
	public void getList(){
		Enumeration<String> checkNames = clientlist.keys();
		ServerThread st = clientlist.get(name);//通过名称获取当前线程（请求列表的线程）
		try{
			st.dos.writeUTF("当前在线列表");
		}catch (IOException e){
			e.printStackTrace();
		}
		while (checkNames.hasMoreElements()){
			try{
				st.dos.writeUTF(checkNames.nextElement());//发送列表中每个用户的名称
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {

	}
}
