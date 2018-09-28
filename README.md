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
  <version>100.00.02</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
implementation 'com.adept.coffeelab:fly-caching:100.00.02'
```

Usage
-----

In your Activity

```java
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rz.logwriter.LogWriter;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        LogWriter.Log("Test Log");
    }
}
```