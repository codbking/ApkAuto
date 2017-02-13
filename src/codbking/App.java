package codbking;

import codbking.bean.APPInfo;
import codbking.impl.FirUpdate;
import codbking.impl.UpdateI;

/**
 * Created by codbking on 2017/2/9.
 */
public class App {


    public static void main(String[] args) {

        String apkPath = PropertiesUtils.getValue("apkPath");
        String projectPath=PropertiesUtils.getValue("appProjectPath");
        String updateLog=PropertiesUtils.getValue("updateLog");

        try {
            //1. 生成apk
            APkUtils.genAPK(projectPath);

             //2. 解析apk,获取apk的信息
            APPInfo info2 =APkUtils.parasAPk(apkPath);
            System.out.println(info2.toString());

            //3. 上传apk
            UpdateI updateI=new FirUpdate();
            try {
                updateI.updateAPk(info2,apkPath,updateLog);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("上传失败");
        }
    }



}
