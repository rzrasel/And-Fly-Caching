package rz.adept.flycaching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rz.logwriter.LogWriter;

public class ActSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        LogWriter.Log("Hi");
    }
}
