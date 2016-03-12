package socket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

/**
 * @author xiliang.zxl
 * @date 2015-11-18 下午9:55
 */
public class SocketTest {

    @Test
    public void testServer()throws Exception{
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    new SocketTest().serverRun();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
        Thread.sleep(3000);
        Socket socket=new Socket();
        SocketAddress inetAddress=new InetSocketAddress(InetAddress.getLocalHost(),9999);
        socket.setKeepAlive(true);
        socket.connect(inetAddress);
        OutputStream outputStream=socket.getOutputStream();
        String msg="hello world";
        outputStream.write(msg.getBytes("utf-8"));
        outputStream.flush();
        socket.close();

    }

    public void serverRun()throws Exception{
        ServerSocket serverSocket=new ServerSocket(9999);
        while (true){
            Socket socket=serverSocket.accept();
            boolean isKeepAlive=socket.getKeepAlive();
            System.out.println("keealive : "+isKeepAlive);
            InputStream inputStream=socket.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;
            while ((line=reader.readLine())!=null){
                System.out.println("read line : "+line);
            }
            socket.close();
            System.out.println("socket closed.");
        }
    }
}
