package zrock.application.engine.app;

import zrock.application.engine.R;
import zrock.application.engine.animation.CardsTransitionController;
import zrock.application.engine.app.setting.SettingsActivity;
import zrock.application.engine.app.chrome.Shared;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TweaksActivity extends AppCompatActivity 
{

	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, TweaksActivity.class);
		mContext.startActivity(mApplication);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine_app_tweaks);

		// Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_tweaks);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});
		View input = findViewById(R.id.input);
        View inputDone = findViewById(R.id.input_done);
        final View focusHolder = findViewById(R.id.focus_holder);

        input.setOnFocusChangeListener(CardsTransitionController.newInstance(this));
        inputDone.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(@NonNull View v) {
					focusHolder.requestFocus();
				}
			});
    }
}

