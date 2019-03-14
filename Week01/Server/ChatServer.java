import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    // 定义一个serversocket对象
    private static ServerSocket server = null;
    // 定义一个socket对象
    private static Socket client = null;
    // 定义一个变量用来保存客户端的用户名
    private static String name;
    // 定义一个布尔值变量
    private static boolean flag = true;
    // 定义输入流变量
    private static DataInputStream di = null;
    // 定义输出流变量
    private static DataOutputStream dos = null;
    //线程池
    private static ExecutorService pool =  Executors.newCachedThreadPool();


    public static void main(String[] args) {
        //ServerThread st = new ServerThread();
        try {
            server = new ServerSocket(8000);//
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("waiting for connection");
                client = server.accept();
                System.out.println("已连接");
                di = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                //防止用户输入错误/重复用户名用while阻塞
                while (flag){
                    name = di.readUTF();//这是一个阻塞方法！
                    if (new ServerThread().checkp(name)){
                        flag = false;
                    }

                }
                //再次确认连接后在线程池中建立新线程
                if(client.isConnected()) {
                    ServerThread st = new ServerThread(client, name);
                    pool.execute(st);
                    flag = true;
                }
                else {
                    System.out.println("连接意外断开");
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
