package course.job.Controller;

import course.job.mainPage;
import course.job.Moudle.Net;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class Controller {
    private static Context context;
    public Controller(Context context){
        this.context=context;
    }
    public void sent(String cmd){
        Net.sent(cmd);
    }
    public static Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json_str=msg.obj.toString();
            try {
                JSONObject json=new JSONObject(json_str);
                String cmd=json.getString("cmd");
                String code=json.getString("code");
                if(cmd!=null&&code!=null&&code.equals("0")){
                    if(cmd.equals("1")){
                        String uid=json.getString("uid");
                        SharedPreferences sp=context.getSharedPreferences("config",0);
                        SharedPreferences.Editor editer= sp.edit();
                        editer.putString("token",uid);
                        editer.commit();
                        Intent intent = new Intent(context, mainPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
                else {

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
