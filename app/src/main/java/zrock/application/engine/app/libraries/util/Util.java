package zrock.application.engine.app.libraries.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Util {
    public static PackageInfo getPackageInfo(Context ctx) {
        //get the packageManager to load and read some values :D
        PackageManager pm = ctx.getPackageManager();
        //get the packageName
        String packageName = ctx.getPackageName();
        //Try to load the applicationInfo
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, 0);
        } catch (Exception ex) {
        }
        return packageInfo;
    }

    public static ApplicationInfo getApplicationInfo(Context ctx) {
        //get the packageManager to load and read some values :D
        PackageManager pm = ctx.getPackageManager();
        //get the packageName
        String packageName = ctx.getPackageName();
        //Try to load the applicationInfo
        ApplicationInfo appInfo = null;
        try {
            appInfo = pm.getApplicationInfo(packageName, 0);
        } catch (Exception ex) {
        }
        return appInfo;
    }
}
