import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
	 实现接口 实现消息记录及消息存储
	 */
public class ClientThread implements Runnable {
	// 定义一个DataInputStream变量
	DataInputStream di=null;
	//定义一个字符串，用来存储接收消息
	String str;
	
	
	public void setDataInputStream(DataInputStream di) {
		this.di = di;
	}
	
	
	public void run() {
		while(true) {
			try {
				str=di.readUTF();//读取输入流中的信息
				String name="default";
				if(str.indexOf("说")>=0) {
					name = str.substring(20, str.indexOf("说"));
					
				}else if(str.indexOf("进入聊天室")>=0){
					System.out.println(str);
					name = str.substring(20, str.indexOf("进入聊天室"));
					System.out.println(name);
				}else if(str.indexOf("退出聊天室")>=0){
					name = str.substring(20, str.indexOf("退出聊天室"));
				}
				writeFile(name,str);
				System.out.println(str); //在客户端中打印接收消息内容
				
			}
			catch(IOException e){
				System.out.println("系统出错啦");//输出错误
				System.exit(0);//关闭客户端
				
			}
		}
		
	}
	public void writeFile(String name,String str1){     //定义消息记录方法
		try {
			File file = new File(name + ".txt");//创建新本地文件
			FileWriter fw = new FileWriter(file, true);//创建一个FileWriter对象
			BufferedWriter bw = new BufferedWriter(fw);//创建一个字符缓冲输出流对象
			bw.write(str1);//写入消息内容
			bw.newLine();//换行
			bw.close();//关闭字符缓冲流
			fw.close();//关闭字符输出流
			}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("出错啦");//输出错误
		}
		
	}
	
}
