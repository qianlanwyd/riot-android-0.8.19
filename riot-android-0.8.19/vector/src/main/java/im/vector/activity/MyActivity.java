package im.vector.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import im.vector.R;

public class MyActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
    }
    public void onClick3(View view)
    {
        finish();
    }
    public void onClick4(View view)
    {
        startActivity(new Intent("ResumeActivity"));
    }
    public void onClick5(View view)
    {
        startActivity(new Intent("ChecknetActivity"));
    }
}




