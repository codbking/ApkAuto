package codbking.bean;

import java.lang.reflect.Field;

public class APPInfo {
	// name
	public String packAge;
	public String versionCode;
	public String versionName;
	// application-label
	public String name;

	public static APPInfo getAPPInfo(String info) {
		APPInfo appInfo = new APPInfo();
		String[] arr = info.split("'");
		appInfo.packAge = arr[1];
		appInfo.versionCode = arr[3];
		appInfo.versionName = arr[5];

		String sp = "application-label:";
		int index = info.indexOf(sp);
		if (index != -1) {
			int index1 = info.indexOf("'", index);
			int index2 = info.indexOf("'", index1+1);
			String name = info.substring(index1+1, index2);
			appInfo.name = name;
		}

		return appInfo;
	}

	@Override
	public String toString() {

		Field[] field = getClass().getFields();
		String string = "";

		try {
			for (Field f : field) {
				string += "[" + f.getName() + "=" + f.get(this).toString() + "]";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return string;

	}

}
