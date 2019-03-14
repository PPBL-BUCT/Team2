import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ChatClient {
    // 创建一个Socket
    private static Socket client = null;
    // 定义输入流变量
    private static DataInputStream di = null;
    // 定义输出流变量
    private static DataOutputStream dos = null;
    // 创建scanner对象接收数据
    Scanner sc = new Scanner(System.in);
    // 定义一个String变量保存用户名
    static String name;
    // 定义一个布尔值来判断是否循环接受用户名
    boolean flag = true;

    public static void main(String args[]) {
        // 创建对象
        ChatClient cc = new ChatClient();
        // 创建客户端的socket对象
        client = new Socket();
        // 调用方法
        cc.connect();
        // 调用方法
        cc.send();
    }

    // 连接
    public void connect() {

        // 创建客户端的socket对象
        client = new Socket();
        // 定义一个String接收IP地址
        String IP = null;
        // 定义一个int端口号
        int port = 0;
        System.out.println("***！！Welcome！！***");
        try {
            System.out.println("请输入服务器地址，无输入则默认地址为127.0.0.1");
            // 接收一个IP保存到string对象中
            IP = sc.nextLine();
            // 匹配接收到的IP，接收到后将IP赋值为默认
            if (IP.equalsIgnoreCase("")) {
                IP = "127.0.0.1";
            }
            // 设置端口号
            port = 8000;

        } catch (Exception e) {
        	e.printStackTrace();
        }
        try {
            // 从给定的主机名得到ip存入inetaddress对象中
            InetAddress address = InetAddress.getByName(IP);
            // 根据得到的ip和端口号创建套接字地址
            InetSocketAddress socketaddress = new InetSocketAddress(address,
                    port);
            // 将客户端的套接字链接到服务器
            try {
                // 连接服务器与客户端
                client.connect(socketaddress);
                // 判断是否有连接
                if (client.isConnected()) {
                    // 调用方法
                    run();
                } else {
                    client.connect(socketaddress);
                    // 调用方法
                    connect();
                }
            } catch (SocketException e) {
                System.out.println("不能连接到服务器，请重新输入");
                e.printStackTrace();
                // 调用connect()重新连接
                connect();
            }
        } catch (Exception e) {
            System.out.println("不能连接到服务器，请重新输入");
            e.printStackTrace();
            // 调用connect()重新连接
            connect();
        }
		System.out.println("已成功连接服务器:"+IP);
    }

    public void run() {
        try {
            // 定义read对象
            ClientThread read = null;
            // 创建read对象
            read = new ClientThread();
            // 创建readdata线程对象
            Thread readData = new Thread(read);
            // 封装一个DataInputStream对象得到输入流
            di = new DataInputStream(client.getInputStream());
            // 封装一个DataOutputStream对象得到输出流
            dos = new DataOutputStream(client.getOutputStream());
            // 接受用户名
            while (flag) {
                boolean wait = true;
                System.out.println("请输入用户名：");
                name = sc.next();
                dos.writeUTF(name);
                dos.flush();
                while(wait){
                    String str = di.readUTF();
                    if(str.equals("用户名可用")){
                        wait = false;
                        System.out.println(name + "上线了");
                        System.out.println("欢迎进入聊天室，需要帮助请输入/A");
                        read.setDataInputStream(di);
                        // 启动线程
                        readData.start();
                        // 改变flag中断循环
                        flag = false;
                    }else if(str.equals("用户名重复")){
                        System.out.println("用户名重复，请重新输入");
                        break;
                    }
                }
            }
        } catch (IOException e) {

        }
    }
    public void select(String mes) {
    	if(mes.equalsIgnoreCase("/H")) {
    		helplist();
    	}else if(mes.equalsIgnoreCase("/C")) {
    		try {
                System.out.println("请输入你要查看的聊天记录的名字"+"\n");
                String str1 = sc.nextLine();
                File file = new File(str1 + ".txt");
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String str = null;
                while ((str = bf.readLine()) != null) {
                    System.out.println(str);
                }
                bf.close();
            } catch (IOException e) {
            	System.out.println("找不到该用户！");
            }
    	}else if(mes.equalsIgnoreCase("/Q")) {
    		System.exit(0);
    	}
    	else {
    		try {
                // 将消息发送给服务器
                dos.writeUTF(mes);
                // 清空输出流
                dos.flush();
            } catch (IOException e) {

            }
    	}
    }
    public void helplist() {
    	 System.out.println("帮助列表：");
         System.out.println("/O 用户在线列表，用户/信息 私聊，/C 查看聊天记录，/Q 退出系统");
    }

}
