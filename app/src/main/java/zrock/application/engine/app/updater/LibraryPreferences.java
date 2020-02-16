package zrock.application.engine.app.updater;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LibraryPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String KeyAppUpdaterShow = "prefAppUpdaterShow";
    public static final String KeySuccessfulChecks = "prefSuccessfulChecks";

    public LibraryPreferences(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = sharedPreferences.edit();
    }

    public Boolean getAppUpdaterShow() {
        return sharedPreferences.getBoolean(KeyAppUpdaterShow, true);
    }

    public void setAppUpdaterShow(Boolean res) {
        editor.putBoolean(KeyAppUpdaterShow, res);
        editor.commit();
    }

    public Integer getSuccessfulChecks() {
        return sharedPreferences.getInt(KeySuccessfulChecks, 0);
    }

    public void setSuccessfulChecks(Integer checks) {
        editor.putInt(KeySuccessfulChecks, checks);
        editor.commit();
    }

}
