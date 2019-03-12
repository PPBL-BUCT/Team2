import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

}
