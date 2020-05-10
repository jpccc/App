package course.job;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class takephoto extends AppCompatActivity {

    Button btn_open_camera;
    ImageView image_show;
    public Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);


        btn_open_camera = findViewById(R.id.open_camera);
        image_show = findViewById(R.id.showplace);

        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File tempImage = new File(getExternalCacheDir(), "temp.jpg");//temp是图片的名字
                if (tempImage.exists()) {
                    tempImage.delete();
                }
                try {
                    tempImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = FileProvider.getUriForFile(takephoto.this, "com.course.file_provider", tempImage);

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, 1);//1表示要拍照

                //上面的操作是:打开一个文件,调用摄像头，并将摄像头获得的照片存到该文件中。
                //ContentProvider起到在不同的app之间通信的作用,用Uri
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode==RESULT_OK){
                    Bitmap bitmap=null;
                    try {
                        bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    image_show.setImageBitmap(bitmap);
                }
                break;
            default:
                break;
        }
    }
}
