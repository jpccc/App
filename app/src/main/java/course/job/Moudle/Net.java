package course.job.Moudle;
import course.job.Controller.Controller;

import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Net {
    public static void sent(final String cmd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //#define the post url
                //open the url
                String url_str="http://192.168.1.109:8080/leisurelife/dealcmd?";
                url_str=url_str+cmd;
                try {
                    URL url=new URL(url_str);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    if(conn.getResponseCode()==200){
                        Log.e("Reg","Success");
                        InputStream inputStream=conn.getInputStream();
                        byte buffer[]=new byte[1024];
                        int len=-1;
                        StringBuilder sb=new StringBuilder();
                        while((len=inputStream.read(buffer))!=-1){
                            sb.append(new String(buffer,0,len,"gbk"));
                        }
                        String reg_str=sb.toString();
                        Log.e("Reg_str",reg_str);
                        Message m=new Message();
                        m.obj=reg_str;
                        Controller.handler.handleMessage(m);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
