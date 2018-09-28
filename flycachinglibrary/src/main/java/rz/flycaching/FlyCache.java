package rz.flycaching;

import android.content.Context;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FlyCache {
    public static void setCache(Context argContext, String argCacheKey, Object argObject) {
        String caseKey = "";
        caseKey = getBase64MD5(argCacheKey);
        //caseKey = argCacheKey;
        System.out.println("DEBUG_LOG_PRINT: setCache -> dataCacheKey " + caseKey);
        try {
            FileOutputStream fileOutputStream = argContext.openFileOutput(caseKey, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(argObject);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getCache(Context argContext, String argCacheKey) {
        String caseKey = "";
        caseKey = getBase64MD5(argCacheKey);
        //caseKey = argCacheKey;
        System.out.println("DEBUG_LOG_PRINT: getCache -> dataCacheKey " + caseKey);
        Object object = null;
        try {
            FileInputStream fileInputStream = argContext.openFileInput(caseKey);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static boolean onDeleteCache(Context argContext, String argCacheKey) {
        String caseKey = "";
        caseKey = getBase64MD5(argCacheKey);
        String cacheDirectory = argContext.getCacheDir().getParent();
        File file = new File(cacheDirectory, caseKey);
        System.out.println("DEBUG_LOG_PRINT: setCache cache_key " + file);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private static String getBase64MD5(String argString) {
        try {
            //argString = argString.replaceAll("\\s+", "");
            //argString = argString.replaceAll("(\\\\+|/+)", Matcher.quoteReplacement(System.getProperty("file.separator")));
            /*argString = argString.replaceAll("/", "");
            argString = argString.replaceAll("\\\\", "");*/
            byte[] byteArray = argString.getBytes("UTF-8");
            String base64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
            base64String = base64String.replaceAll("\\s+", "");
            //base64String = base64String.replaceAll("(\\\\+|/+)", Matcher.quoteReplacement(System.getProperty("file.separator")));
            //base64String = base64String.replaceAll("(?<!^)(\\\\|/)", "");
            /*base64String = base64String.replaceAll("/", "");
            base64String = base64String.replaceAll("\\\\", "");*/
            base64String = getMD5(base64String);
            return base64String;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMD5(String argString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(argString.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(String.format("%02x", b & 0xff));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createCachedFile(Context argContext) throws IOException {
        String filename = "myfile";
        String fileContents = "Hello world!";
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        FileOutputStream outputStream;
        outputStream = argContext.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(list);
        oos.close();
        outputStream.close();

        /*try {
            outputStream = argContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private static Object readCachedFile(Context argContext) throws IOException, ClassNotFoundException {
        String filename = "myfile";
        FileInputStream fis = argContext.openFileInput(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    private static void createCachedFile(Context argContext, String argKey, ArrayList<String> argFileName) throws IOException {

        String tempFile = null;
        //File file = new File(Environment.getExternalStorageDirectory() + "/afdadf");
        for (String item : argFileName) {
            FileOutputStream fos = argContext.openFileOutput(argKey, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(argFileName);
            oos.close();
            fos.close();

        }
    }

    //To Read a ArrayList<File>
    private static Object readCachedFile(Context argContext, String argKey) throws IOException, ClassNotFoundException {
        FileInputStream fis = argContext.openFileInput(argKey);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    private static void Serialize(Object object, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            fileOutputStream.close();
            //Logging.log(String.format("SERIALIZED [%s]: %s", object.getClass().getName(), object));
        } catch (NotSerializableException exception) {
            // Output expected NotSerializeableExceptions.
            //Logging.log(exception);
        } catch (IOException exception) {
            // Output unexpected IOException.
            //Logging.log(exception, false);
        }
    }

    /**
     * Deserializes object found in passed filePath.
     *
     * @param filePath Path to file where serialized object is found.
     * @param <T>      Type of object that is expected.
     */
    private static <T> void Deserialize(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T object = (T) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            //Logging.log(String.format("DESERIALIZED [%s]: %s", object.getClass().getName(), object));
        } catch (NotSerializableException exception) {
            // Output expected NotSerializeableExceptions.
            //Logging.log(exception);
        } catch (IOException | ClassNotFoundException exception) {
            // Output unexpected IOExceptions and ClassNotFoundExceptions.
            //Logging.log(exception, false);
        }
    }
}
/*
FileMemoryCache fileMemoryCache = new FileMemoryCache();
ArrayList<File> fileList = new ArrayList<>();
try {
    fileList.add("test.text");
    fileMemoryCache.createCachedFile(context, "apk", fileList);
} catch (IOException e) {
    e.printStackTrace();
}
readCachedFile(context, "apk");
https://readyandroid.wordpress.com/caching-arraylist-of-custom-objects-in-android-internal-storage/
http://www.vogella.com/tutorials/JavaSerialization/article.html
https://www.wikihow.com/Serialize-an-Object-in-Java
https://www.tutorialspoint.com/java/java_serialization.htm
https://stackoverflow.com/questions/8116147/java-how-to-make-this-serializable
how to serialize an object in java without using serializable interface
java.io.FileNotFoundException: /data/data/com.sm.cattleshurjohms/files/SFRUUFJlcXVlc3RTcGlubmVyQ293SWRMaXN0aHR0cDovc2h1cmpvaG1zLmNvbS9obXNhcGkvY293Lz9mYXJtPTE=

public class Box<T> {
   private T t;

   public void add(T t) {
      this.t = t;
   }

   public T get() {
      return t;
   }

   public static void main(String[] args) {
      Box<Integer> integerBox = new Box<Integer>();
      Box<String> stringBox = new Box<String>();
    
      integerBox.add(new Integer(10));
      stringBox.add(new String("Hello World"));

      System.out.printf("Integer Value :%d\n\n", integerBox.get());
      System.out.printf("String Value :%s\n", stringBox.get());
   }
}
*/