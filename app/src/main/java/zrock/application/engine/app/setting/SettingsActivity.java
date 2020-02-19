package zrock.application.engine.app.setting;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.CheckBoxPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import zrock.application.engine.R;
import zrock.application.engine.app.updater.AppUpdater;
import zrock.application.engine.app.updater.enums.Display;
import zrock.application.engine.app.updater.enums.UpdateFrom;
import zrock.application.engine.*;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener 
{

	public static void start(Context mContext)
	{
		Intent mApplication = new Intent(mContext, SettingsActivity.class);
		mContext.startActivity(mApplication);
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        Preference prefCheckForUpdates = findPreference("prefCheckForUpdates");

        prefCheckForUpdates.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference)
				{
					new AppUpdater(SettingsActivity.this)
                        .setUpdateFrom(UpdateFrom.XML)
						.setUpdateXML("https://raw.githubusercontent.com/ZRock-Application/ZRock_Engine/master/app/update-changelog.xml")
                        .setDisplay(Display.DIALOG)
                        .showAppUpdated(true)
                        .start();
					return true;
				}
			});

    }

    @Override
    public void setContentView(int layoutResID)
	{
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_application_engine_app_settings, new LinearLayout(this), false);
        Toolbar toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_appupdater);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view)
				{
					onBackPressed();
				}
			});

        ViewGroup contentWrapper = (ViewGroup) contentView.findViewById(R.id.content_wrapper);
        LayoutInflater.from(this).inflate(layoutResID, contentWrapper, true);
        getWindow().setContentView(contentView);

    }

	@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
        PreferenceKeys preferenceKeys = new PreferenceKeys(getResources());
        if (key.equals(preferenceKeys.night_mode_pref_key))
		{
            SharedPreferences themePreferences = getSharedPreferences(EngineActivity.THEME_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor themeEditor = themePreferences.edit();
            //We tell our MainLayout to recreate itself because mode has changed
            themeEditor.putBoolean(EngineActivity.RECREATE_ACTIVITY, true);

            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(preferenceKeys.night_mode_pref_key);
            if (checkBoxPreference.isChecked())
			{
                //Comment out this line if not using Google Analytics
                themeEditor.putString(EngineActivity.THEME_SAVED, EngineActivity.DARKTHEME);
            }
			else
			{
                themeEditor.putString(EngineActivity.THEME_SAVED, EngineActivity.LIGHTTHEME);
            }
            themeEditor.apply();

            recreate();
        }
    }

    @Override
    protected void onResume()
	{
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause()
	{
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
