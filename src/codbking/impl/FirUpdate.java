package codbking.impl;

import codbking.bean.APPInfo;
import codbking.net.HttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import codbking.bean.FirGetUrlData;

import java.io.File;
import java.util.HashMap;

/**
 * Created by codbking on 2017/2/9.
 */
public class FirUpdate implements UpdateI {

    //fir获取apk上传信息
    private static final String firUrl = "http://api.fir.im/apps";
    //fir的token值
    private static String token = "you fir token";

    @Override
    public void updateAPk(APPInfo info, String apkPath, String updateLog) throws Exception {


        HashMap<String, String> map = new HashMap<>();
        map.put("type", "android");
        map.put("bundle_id", info.packAge);
        map.put("api_token", token);

        //获取上传信息
        String reuslt = HttpHelper.post(firUrl, map, null);
        FirGetUrlData data = JSON.parseObject(reuslt, FirGetUrlData.class);

        System.out.println("获取上传信息："+JSON.toJSON(data));

        if (data == null) {
            throw new Exception("请求出错");
        }


        updateIcon(data, apkPath);
        updateApk(data, info, apkPath, updateLog);

    }


    //上传icon
    private void updateIcon(FirGetUrlData data, String apkPath) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", data.cert.icon.key);
        params.put("token", data.cert.icon.token);
        params.put("file", new File(apkPath));

        String result = HttpHelper.update(data.cert.icon.upload_url, params);

        JSONObject json = JSON.parseObject(result);
        boolean isSuccess = json.getBoolean("is_completed");
        if (isSuccess) {
            System.out.println("apk icon上传成功");
        } else {
            throw new Exception("apk icon上传失败");
        }

    }

    //上传apk
    private void updateApk(FirGetUrlData data, APPInfo info, String apkPath, String updateLog) throws Exception {

        HashMap<String, Object> upateAPkParams = new HashMap<>();
        upateAPkParams.put("key", data.cert.binary.key);
        upateAPkParams.put("token", data.cert.binary.token);
        upateAPkParams.put("file", new File(apkPath));

        upateAPkParams.put("x:name", info.packAge);
        upateAPkParams.put("x:version", info.versionName);
        upateAPkParams.put("x:build", info.versionCode);
        upateAPkParams.put("x:changelog", updateLog);
        String result = HttpHelper.update(data.cert.binary.upload_url, upateAPkParams);

        JSONObject json = JSON.parseObject(result);
        boolean isSuccess = json.getBoolean("is_completed");
        if (isSuccess) {
            System.out.println("apk上传成功");
        } else {
            throw new Exception("apk上传失败");
        }

    }


}
