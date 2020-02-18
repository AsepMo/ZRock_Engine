package zrock.application.engine;

import zrock.application.engine.app.setting.SettingsActivity;
import zrock.application.engine.app.chrome.Shared;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class EngineActivity extends AppCompatActivity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine);
		
		// Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.menu_main ,menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.action_github:
				String urlGithub = "https://github.com/ZRock-Application/ZRock_Engine/releases";
				Shared.setLink(EngineActivity.this ,urlGithub);
				break;
			case R.id.action_settings:
				SettingsActivity.start(EngineActivity.this);
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
