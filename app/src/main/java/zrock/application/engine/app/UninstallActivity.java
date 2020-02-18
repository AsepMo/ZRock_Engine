package zrock.application.engine.app;

import zrock.application.engine.R;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UninstallActivity extends AppCompatActivity
{
	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, UninstallActivity.class);
		mContext.startActivity(mApplication);
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine_app_uninstaller);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_uninstaller);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
	} 
}
