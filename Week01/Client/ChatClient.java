import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
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

}
