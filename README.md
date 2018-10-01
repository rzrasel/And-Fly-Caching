# And-Fly-Caching
And Fly Caching

### GIT Command
```git_command
git init
git remote add origin https://github.com/rzrasel/And-Fly-Caching.git
git remote -v
git fetch && git checkout master
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all
```

<a href='https://bintray.com/rzrasel/CommonLibraries/and-fly-caching?source=watch' alt='Get automatic notifications about new "and-fly-caching" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
[ ![Download](https://api.bintray.com/packages/rzrasel/CommonLibraries/and-fly-caching/images/download.svg) ](https://bintray.com/rzrasel/CommonLibraries/and-fly-caching/_latestVersion)

Download
--------

Download the latest JAR or grab via Maven:
```xml
<dependency>
  <groupId>com.adept.coffeelab</groupId>
  <artifactId>fly-caching</artifactId>
  <version>100.00.01</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
implementation 'com.adept.coffeelab:fly-caching:100.00.01'
```

Usage
-----

In your Activity

```java
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
```