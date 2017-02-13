package codbking;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by codbking on 2017/2/10.
 */
public class PropertiesUtils {


    private static Properties getProperties(){
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./././default.properties");
            pro.load(new InputStreamReader(in, "UTF-8"));
            in.close();
            return pro;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    public static String getValue(String key){
        String vlaue=getProperties().getProperty(key);
        System.out.println(key+"="+vlaue);
        return vlaue;
    }


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(getValue("appProjectPath"));
    }


}
