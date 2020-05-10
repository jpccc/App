package course.job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import course.job.Controller.Controller;

public class register extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("config", 0);
        String token = null;
        token = sp.getString("token", null);
        if (token != null) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), mainPage.class);
                    startActivity(intent);
                }
            }.start();
        } else {
            reg();
        }
    }

    public void reg() {
        username = findViewById(R.id.name);
        password = findViewById(R.id.password);
        register = findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString().trim();
                String user_word = password.getText().toString();
                //create a http post with information
                String cmd_str="cmd=1";
                cmd_str =cmd_str+"&name="+user_name;
                cmd_str=cmd_str+"&password="+user_word;
                //send post
                Controller c=new Controller(getApplicationContext());
                c.sent(cmd_str);
            }
        });
    }
}
