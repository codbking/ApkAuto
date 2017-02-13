package codbking.impl;

import codbking.bean.APPInfo;

/**
 * Created by wulang on 2017/2/9.
 */
public interface UpdateI {
    /**
     *
     * @param info apk信息
     * @param apkPath apk本地路径
     * @param updateLog  上传日志
     */
    void  updateAPk(APPInfo info, String apkPath, String updateLog) throws Exception;

}
