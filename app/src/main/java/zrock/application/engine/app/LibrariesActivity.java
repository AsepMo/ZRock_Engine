package zrock.application.engine.app;

import zrock.application.engine.R;
import zrock.application.engine.app.libraries.Libs;
import zrock.application.engine.app.libraries.LibsBuilder;
import zrock.application.engine.app.libraries.LibsConfiguration;
import zrock.application.engine.app.libraries.entity.Library;
import zrock.application.engine.app.libraries.LibsFragment;
import zrock.application.engine.app.libraries.*;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class LibrariesActivity extends AppCompatActivity
{
	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, LibrariesActivity.class);
		mContext.startActivity(mApplication);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine_app_libraries);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_libraries);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        
        new DrawerBuilder(this)
			.withToolbar(toolbar)
			.addDrawerItems(
			new PrimaryDrawerItem().withName("Home"),
			new PrimaryDrawerItem().withName(R.string.action_manifestactivity).withIdentifier(R.id.action_manifestactivity).withCheckable(false),
			new PrimaryDrawerItem().withName(R.string.action_extendactivity).withIdentifier(R.id.action_extendactivity).withCheckable(false),
			new PrimaryDrawerItem().withName(R.string.action_customsortactivity).withIdentifier(R.id.action_customsortactivity).withCheckable(false),
			new PrimaryDrawerItem().withName(R.string.action_opensource).withIdentifier(R.id.action_opensource).withCheckable(false)
		)
			.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
				@Override
				public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem drawerItem)
				{
					// Handle action bar item clicks here. The action bar will
					// automatically handle clicks on the Home/Up button, so long
					// as you specify a parent activity in AndroidManifest.xml.

					/*
					 Intent i = new Intent(getApplicationContext(), LibsActivity.class);
					 i.putExtra(Libs.BUNDLE_FIELDS, Libs.toStringArray(R.string.class.getFields()));
					 i.putExtra(Libs.BUNDLE_LIBS, new String[]{"crouton", "actionbarsherlock", "showcaseview"});
					 i.putExtra(Libs.BUNDLE_AUTODETECT, true);

					 i.putExtra(Libs.BUNDLE_VERSION, true);
					 i.putExtra(Libs.BUNDLE_LICENSE, true);

					 i.putExtra(Libs.BUNDLE_TITLE, "Open Source");
					 i.putExtra(Libs.BUNDLE_THEME, R.style.AppTheme);

					 //INFO you can set the about app text with these extra data too
					 i.putExtra(Libs.BUNDLE_APP_ABOUT_ICON, true);
					 i.putExtra(Libs.BUNDLE_APP_ABOUT_VERSION, true);
					 i.putExtra(Libs.BUNDLE_APP_ABOUT_DESCRIPTION, "This is a small sample which can be set in the about my app description file.<br /><b>You can style this with html markup :D</b>");

					 startActivity(i);
					 */

					int id = drawerItem.getIdentifier();
					if (id == R.id.action_opensource)
					{
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mikepenz/AboutLibraries"));
						startActivity(browserIntent);
					}
					else if (id == R.id.action_extendactivity)
					{
						Intent intent = new Intent(getApplicationContext(), ExtendActivity.class);
						startActivity(intent);
					}
					else if (id == R.id.action_customsortactivity)
					{
						Intent intent = new Intent(getApplicationContext(), CustomSortActivity.class);
						startActivity(intent);
					}
					else if (id == R.id.action_manifestactivity)
					{
						new LibsBuilder()
							.withFields(R.string.class.getFields())
							.withLibraries("crouton", "actionbarsherlock", "showcaseview")
							.withAutoDetect(true)
							.withLicenseShown(true)
							.withVersionShown(true)
							.withAnimations(true)
							.withActivityTitle("Open Source")
							.withActivityStyle(Libs.ActivityStyle.DARK)
							.withListener(new LibsConfiguration.LibsListener() {
								@Override
								public void onIconClicked(View v)
								{
									Toast.makeText(v.getContext(), "We are able to track this now ;)", Toast.LENGTH_LONG).show();
								}

								@Override
								public boolean onLibraryAuthorClicked(View v, Library library)
								{
									return false;
								}

								@Override
								public boolean onLibraryContentClicked(View v, Library library)
								{
									return false;
								}

								@Override
								public boolean onLibraryBottomClicked(View v, Library library)
								{
									return false;
								}

								@Override
								public boolean onExtraClicked(View v, Libs.SpecialButton specialButton)
								{
									return false;
								}

								@Override
								public boolean onIconLongClicked(View v)
								{
									return false;
								}

								@Override
								public boolean onLibraryAuthorLongClicked(View v, Library library)
								{
									return false;
								}

								@Override
								public boolean onLibraryContentLongClicked(View v, Library library)
								{
									return false;
								}

								@Override
								public boolean onLibraryBottomLongClicked(View v, Library library)
								{
									return false;
								}
							})
							.start(LibrariesActivity.this);
					}

					return false;
				}
			})
			.build();


        /*
		 Bundle bundle = new Bundle();
		 bundle.putStringArray(Libs.BUNDLE_FIELDS, Libs.toStringArray(R.string.class.getFields()));
		 bundle.putStringArray(Libs.BUNDLE_LIBS, new String[]{"crouton", "activeandroid", "actionbarsherlock", "showcaseview"});

		 bundle.putBoolean(Libs.BUNDLE_VERSION, true);
		 bundle.putBoolean(Libs.BUNDLE_LICENSE, true);

		 //NOTE: This is how you can modify a specific library definition during runtime
		 HashMap<String, HashMap<String, String>> libsModification = new HashMap<String, HashMap<String, String>>();
		 HashMap<String, String> modifyAboutLibraries = new HashMap<String, String>();
		 modifyAboutLibraries.put("name", "_AboutLibraries");
		 libsModification.put("aboutlibraries", modifyAboutLibraries);
		 bundle.putSerializable(Libs.BUNDLE_LIBS_MODIFICATION, libsModification);

		 LibsFragment fragment = new LibsFragment();
		 fragment.setArguments(bundle);
		 */

        LibsFragment fragment = new LibsBuilder()
			.withFields(R.string.class.getFields())
			.withLibraries("crouton", "activeandroid", "actionbarsherlock", "showcaseview")
			.withVersionShown(false)
			.withLicenseShown(false)
			.withLibraryModification("aboutlibraries", Libs.LibraryFields.LIBRARY_NAME, "_AboutLibraries")
			.withAutoDetect(true)
			.withAboutAppName("Libraries")
			.withLicenseDialog(true)
			.withLicenseShown(true)
			.withActivityStyle(Libs.ActivityStyle.DARK)
			.withListener(new LibsConfiguration.LibsListener() {
				@Override
				public void onIconClicked(View v)
				{
					Toast.makeText(v.getContext(), "We are able to track this now ;)", Toast.LENGTH_LONG).show();
				}

				@Override
				public boolean onLibraryAuthorClicked(View v, Library library)
				{
					return false;
				}

				@Override
				public boolean onLibraryContentClicked(View v, Library library)
				{
					return false;
				}

				@Override
				public boolean onLibraryBottomClicked(View v, Library library)
				{
					return false;
				}

				@Override
				public boolean onExtraClicked(View v, Libs.SpecialButton specialButton)
				{
					return false;
				}

				@Override
				public boolean onIconLongClicked(View v)
				{
					return false;
				}

				@Override
				public boolean onLibraryAuthorLongClicked(View v, Library library)
				{
					return false;
				}

				@Override
				public boolean onLibraryContentLongClicked(View v, Library library)
				{
					return false;
				}

				@Override
				public boolean onLibraryBottomLongClicked(View v, Library library)
				{
					return false;
				}
			})
			.fragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

    }
}
