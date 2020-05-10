package course.job.Moudle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GrayPhoto {
    public static float[] gray(){
        float []list=new float[9];
        String filename;
        for(int r=1;r<=9;r++)
        {
            filename="/sdcard/Pictures/";
            filename = filename + r + ".jpg";
            Log.e("name",filename);
            //以文件流的方式读取图片
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            if (bitmap != null) {
                int pixel;
                int height = bitmap.getHeight();//图片的高度
                int width = bitmap.getWidth();//图片的宽度
                int minx = 0;
                int miny = 0;
                int[] rgb = new int[3];
                float sum = 0;
                float avarage = 0;
                float img[][] = new float[width][height];
                for (int i = minx; i < width; i++)//可自行更改起点位置
                    for (int j = miny / 2; j < height * 3 / 4; j++) {
                        pixel = bitmap.getPixel(i, j);//下面三行获取像素点（i, j）的RGB值
                        rgb[0] = (pixel & 0xff0000) >> 16;
                        rgb[1] = (pixel & 0xff00) >> 8;
                        rgb[2] = (pixel & 0xff);
                        img[i][j] = (rgb[0] * 150 + rgb[1] * 59 + rgb[2] * 11 + 150) / 150;
                        sum += img[i][j];
                    }
                avarage = sum / (width * height);
                    list[r-1]=avarage;
                Log.i("灰度值", "value:" + avarage);
            } else {
                Log.i("灰度值", "空！");
            }
        }
        return list;
    }
}
