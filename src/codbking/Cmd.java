package codbking;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by codbking on 2017/2/10.
 */
public class Cmd {


    public static String exec(String cmd) throws IOException {
       return exec(cmd,true);
    }


    public static String exec(String cmd,boolean isClose) throws IOException {

        if(isClose){
            cmd="cmd /c "+cmd;
        }else {
            cmd="cmd /k"+cmd;
        }

        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String b = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((b = in.readLine()) != null) {
            stringBuffer.append(b);
        }
        String result = new String(stringBuffer.toString().getBytes(), "utf-8");
        return result;
    }


    public static String exec(String cmd, String[] var2,File file) throws IOException {
        cmd="cmd /c "+cmd;
        Process process = Runtime.getRuntime().exec(cmd,var2,file);


        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String b = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((b = in.readLine()) != null) {
            stringBuffer.append(b);
        }
        String result = new String(stringBuffer.toString().getBytes(), "utf-8");
        return result;
    }


    public static String exec(String cmd, String[] var2) throws IOException {


        Process process = Runtime.getRuntime().exec(cmd,var2);


        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String b = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((b = in.readLine()) != null) {
            stringBuffer.append(b);
        }
        String result = new String(stringBuffer.toString().getBytes(), "utf-8");
        return result;
    }


}
