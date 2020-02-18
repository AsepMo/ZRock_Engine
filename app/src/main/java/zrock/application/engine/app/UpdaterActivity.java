package zrock.application.engine.app;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import zrock.application.engine.R;
import zrock.application.engine.app.updater.AppUpdater;
import zrock.application.engine.app.updater.enums.Display;
import zrock.application.engine.app.updater.enums.UpdateFrom;

public class UpdaterActivity extends PreferenceActivity {

	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, UpdaterActivity.class);
		mContext.startActivity(mApplication);
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        Preference prefCheckForUpdates = findPreference("prefCheckForUpdates");

        prefCheckForUpdates.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					new AppUpdater(UpdaterActivity.this)
                        //.setUpdateFrom(UpdateFrom.GITHUB)
                        //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
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
    public void setContentView(int layoutResID) {
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_application_engine_app_settings, new LinearLayout(this), false);
        Toolbar toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_appupdater);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});

        ViewGroup contentWrapper = (ViewGroup) contentView.findViewById(R.id.content_wrapper);
        LayoutInflater.from(this).inflate(layoutResID, contentWrapper, true);
        getWindow().setContentView(contentView);

    }

}
