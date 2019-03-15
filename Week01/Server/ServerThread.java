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
	private DataInputStream di = null;
	// 定义一个Dataoutputstream变量
	private DataOutputStream dos = null;
	// 定义一个变量保存连接当前线程的用户名
	private String name = null;
	// 创建一个hashtable对象用来保存所有的为客户端开辟的线程对象
	private static Hashtable<String, ServerThread> clientlist = new Hashtable<String, ServerThread>();

	/**
	 * 构造方法1
	 * @param client socket客户端实例
	 * @param name 客户端名称
	 */

	public  ServerThread(Socket client,String name){
		try {
			// 将传入的client赋值给成员变量的client
			this.client = client;
			// 将传入的name赋值给成员变量的name
			this.name = name;
			// 将服务器的输出流封装到DataInputStream中
			di = new DataInputStream(client.getInputStream());
			// 将服务器的输出流封装到DataOutputStream中
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {

		}
	}

	/**
	 * 构造方法2，这里是因为主函数要先调用查重方法，值得商榷
	 */
	public  ServerThread(){

	}

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
			try {
				st1.dos.writeUTF("你发送的信息有误，请重新发送！");
			} catch (IOException e1) {
				e1.printStackTrace();
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

	/**
	 * 获取当前时间
	 * @return String类型时间
	 */
	public String getTime(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(new Date());

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

	public void run() {
		try {
			// 添加当前对象到hashtable
			clientlist.put(name, this);
			System.out.println(clientlist);
			// 发送新用户进入的消息给所有客户端
			Broadcast(getTime() + "\t"+name + "进入聊天室");

			while (true) {
				// 定义一个string对象接受从流中读取到的信息
				String mess = di.readUTF();
				System.out.println("mess:"+mess);
				// 创建一个stringtokenizer对象分析接收到的消息
				StringTokenizer str = new StringTokenizer(mess, "/");
				// 判断截取到的信息有没分隔符
				// 如果有分隔符则判断为私聊发送信息
				if (str.countTokens() == 2) {
					// 得到要发送私聊信息用户的姓名
					String nameid = str.nextToken();
					// 得到要发送的私聊信息
					String message = str.nextToken();
					// 调用发sendclient送私聊信息
					sendClient(nameid, message);

					// 没有分隔符或者有多个分隔符是信息默认为公聊发送
				} else if (mess.equalsIgnoreCase("/0")) {
					// 匹配到调用getlist方法
					getList();
					// 判断信息是否与-change匹配
				} else if (mess.equalsIgnoreCase("/Q")) {
					System.out.println("name" + "退出聊天室");
					break;
				} else if (str.countTokens() == 1 || str.countTokens() >= 3) {
					// 调用sendallclient发送公聊信息
					Broadcast(getTime() + "\t"+ name + "说：" + mess);
				}
			}
			client.close();
		} catch (Exception e) {

		} finally {
			// 清除客户端信息
			clientlist.remove(name);
			// 有人退出时，给所有人发送退出信息
			Broadcast(getTime() + "\t"+ name + "退出聊天室");
			System.out.println(getTime() + " " + name + "退出聊天室");
		}
	}
}
