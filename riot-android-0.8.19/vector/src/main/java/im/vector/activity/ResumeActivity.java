package im.vector.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import im.vector.R;

public class ResumeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_activity);
        InputStream input=getResources().openRawResource(R.raw.resume);
        Reader reader=new InputStreamReader(input);
        StringBuffer stringBuffer=new StringBuffer();
        char b[]=new char[1024];
        int len=-1;
        try {
            while ((len = reader.read(b))!= -1){
                stringBuffer.append(b);
            }
        }catch (IOException e){
            //Log.e("ReadingFile","IOException");
        }
        String string=stringBuffer.toString();
        TextView textView=findViewById(R.id.textView);
        textView.setText(string);
    }
    public void onClick6(View view)
    {
        TextView textView=findViewById(R.id.textView);
        TextView textView2=findViewById(R.id.editText2);
        String string=textView2.getText().toString();
        textView.setText(string);
        //finish();
    }

    public void onClick9(View view)
    {

        finish();
    }
    public void onClick10(View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {//判断是否有相机应用
            startActivityForResult(takePictureIntent,1);
        }

    }
}