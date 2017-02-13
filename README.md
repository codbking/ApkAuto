#简介
apk自动打包上传困扰了我很久，很想有一个脚本或程序能够一键自动打包上传，这样可以节省很多时间，还不容易出打包的错误，拿这点时间，去抽跟烟，扯扯蛋，该多好。。。


#思路
以下为上传到fir服务器为例：

1. 配置gradle的apk输出路径
```
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    //开始输出,自定义输出路径
                    output.outputFile =
                            new File("f:/app/apkauto"+".apk")
                }
            }
        }
    }
```
2. 通过Java运行cmd命令到项目目录，运行grald命令,gradlew assembleRelease命令为正式环境打包，你可以手动配置打包环境命令
```
  public static  String  genAPK(String projectPath) throws IOException {
        String params1=projectPath.substring(0,1);
        String params2=projectPath;

        String cmd=params1+": "+"&cd "+params2+" &gradlew assembleRelease";
         return   Cmd.exec(cmd);
    }
```
3. 通过 aapt解析apk的信息，解析命令为<code>aapt dump badging apk路径</code>
```
 public static APPInfo parasAPk(String apkPath) throws IOException {
         String reulst=Cmd.exec(PARASE_APK+apkPath);
         return APPInfo.getAPPInfo(reulst);
    }
```
4. 配置上传文件信息
```
#apk生成后绝对地址，这个路径需要在gralde里面设置
apkPath=f:/app/apkauto.apk
#app项目的本地路径
appProjectPath=E:/work/APkAuto/
#更新日志
updateLog=更新日志
```
5. 上传到服务器（目前以上传到fir为例，具体请参考[fir上传接口](https://fir.im/docs/publish)）
 a.获取apk上传信息->b.上传icon->c.上传apk

#流程


![apk自动打包流程](http://upload-images.jianshu.io/upload_images/4046518-2229f5fc58267644.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

启动程序(App.java)
```java
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
```