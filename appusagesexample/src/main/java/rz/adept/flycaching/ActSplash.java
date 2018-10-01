package rz.adept.flycaching;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rz.flycaching.FlyCache;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private String dataCacheKey = "dataCacheKey";
    private String dataCacheKey2 = "dataCacheKey_cacheKey01";
    private String dataCacheKey3 = "dataCacheKey_cacheKey02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        FlyCache.setCache(context, dataCacheKey, "Test cache data");
        FlyCache.setCache(context, dataCacheKey2, "Test cache data 02");
        FlyCache.setCache(context, dataCacheKey3, "Test cache data 03");
        System.out.println("DEBUG_LOG_PRINT: " + FlyCache.getCache(context, dataCacheKey));
        FlyCache.getCacheTime(context, dataCacheKey);
        FlyCache.onClearAll(context);
    }
}
