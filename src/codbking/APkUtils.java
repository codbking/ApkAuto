package codbking;

import codbking.bean.APPInfo;

import java.io.IOException;

/**
 * Created by codbking on 2017/2/9.
 */
public class APkUtils {

    private static final String PARASE_APK = "aapt dump badging ";


    public static APPInfo parasAPk(String apkPath) throws IOException {
         String reulst=Cmd.exec(PARASE_APK+apkPath);
         return APPInfo.getAPPInfo(reulst);
    }


    public static  String  genAPK(String projectPath) throws IOException {
//
        String params1=projectPath.substring(0,1);
        String params2=projectPath;

        String cmd=params1+": "+"&cd "+params2+" &gradlew assembleRelease";

        String cdE=  Cmd.exec(cmd);
        System.out.println(cdE);
         return "";
    }



}
