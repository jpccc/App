package course.job;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import course.job.Moudle.GrayPhoto;

public class mainPage extends AppCompatActivity {
    public Button btn_take_photo;
    public Button btn_import_photo;
    float[] list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        list=GrayPhoto.gray();
        for(int i=0;i<9;i++){
            Log.e("value","a"+list[i]);
        }
        setListener();

    }
    void setListener(){
        btn_take_photo=findViewById(R.id.take_photo);
        btn_import_photo=findViewById(R.id.import_photo);
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainPage.this, takephoto.class);
                startActivity(intent);
            }
        });
        btn_import_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainPage.this, photoalbum.class);
                startActivity(intent);
            }
        });
    }
}
