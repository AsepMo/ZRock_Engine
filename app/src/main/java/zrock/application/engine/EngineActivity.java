package zrock.application.engine;

import zrock.application.engine.app.setting.SettingsActivity;
import zrock.application.engine.app.chrome.Shared;
import zrock.application.engine.app.libraries.CenteredTextFragment;
import zrock.application.engine.view.menu.DrawerAdapter;
import zrock.application.engine.view.menu.DrawerItem;
import zrock.application.engine.view.menu.SimpleItem;
import zrock.application.engine.view.menu.SpaceItem;
import zrock.application.engine.widget.SlidingRootNav;
import zrock.application.engine.widget.SlidingRootNavBuilder;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

public class EngineActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener
{
	public static void start(Context mContext)
	{
		Intent mApplication = new Intent(mContext, EngineActivity.class);
		mContext.startActivity(mApplication);
	}

	public static final String TAG  = EngineActivity.class.getSimpleName();
	private Toolbar mToolbar; 
	private ActionBar mActionBar;
	private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
    private EngineActivity mActivity;
	

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine);
		
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
		mActionBar = getSupportActionBar();
		if (mActionBar != null)
		{   
            mActionBar.setTitle(R.string.app_name);
	    }

		slidingRootNav = new SlidingRootNavBuilder(this)
			.withToolbarMenuToggle(mToolbar)
			.withMenuOpened(false)
			.withContentClickableWhenMenuOpened(false)
			.withSavedState(savedInstanceState)
			.withMenuLayout(R.layout.menu_left_drawer)
			.inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
													  createItemFor(POS_DASHBOARD).setChecked(true),
													  createItemFor(POS_ACCOUNT),
													  createItemFor(POS_MESSAGES),
													  createItemFor(POS_CART),
													  new SpaceItem(48),
													  createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = (RecyclerView)findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_DASHBOARD);
		
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

	@Override
    public void onItemSelected(int position)
	{
        if (position == POS_DASHBOARD)
		{
            //switchContent(new DashboardFragment());
			mActionBar.setSubtitle("DASHBOARD");
        }
		if (position == POS_ACCOUNT)
		{
            //switchContent(new ProfileFragment());
			mActionBar.setSubtitle("PROFILE");
        }
		if (position == POS_MESSAGES)
		{
            //switchContent(new MessageFragment());
			mActionBar.setSubtitle("MESSAGES");
        }
		if (position == POS_CART)
		{
            //switchContent(new ChartFragment());
			mActionBar.setSubtitle("CART");
        }
		if (position == POS_LOGOUT)
		{
            finish();
        }
        slidingRootNav.closeMenu();
		Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        switchContent(selectedScreen);
		
    }

    private void switchContent(Fragment fragment)
	{
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.frame_container, fragment)
			.commit();
	}

    private DrawerItem createItemFor(int position)
	{
        return new SimpleItem(screenIcons[position], screenTitles[position])
			.withIconTint(color(R.color.textColorSecondary))
			.withTextTint(color(R.color.textColorPrimary))
			.withSelectedIconTint(color(R.color.colorAccent))
			.withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles()
	{
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons()
	{
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++)
		{
            int id = ta.getResourceId(i, 0);
            if (id != 0)
			{
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res)
	{
        return ContextCompat.getColor(this, res);
    }
}
