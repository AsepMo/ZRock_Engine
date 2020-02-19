package zrock.application.engine.app.fragment;

import zrock.application.engine.R;
import zrock.application.engine.app.UpdaterActivity;
import zrock.application.engine.app.InstallActivity;
import zrock.application.engine.app.BackupActivity;
import zrock.application.engine.app.UninstallActivity;
import zrock.application.engine.app.LibrariesActivity;
import zrock.application.engine.app.MonitorActivity;
import zrock.application.engine.app.PluginActivity;
import zrock.application.engine.app.EditorActivity;
import zrock.application.engine.app.PackageActivity;
import zrock.application.engine.app.DeviceInfoActivity;
import zrock.application.engine.app.ZRockPackageActivity;
import zrock.application.engine.app.setting.SettingsActivity;
import zrock.application.engine.app.picker.PickerUI;
import zrock.application.engine.app.picker.PickerUISettings;
import zrock.application.engine.app.libraries.util.UIUtils;
import zrock.application.engine.app.content.ContentActivity;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EngineMeFragment extends Fragment
{
	private ImageView aboutIcon;
	private TextView aboutAppName;
	private View aboutSpecialContainer;
	Button aboutSpecial1;
	Button aboutSpecial2;
	Button aboutSpecial3;
	private TextView aboutVersion;
	private PickerUI mPickerUI;
    private int currentPosition = -1;
    private List<String> optionDevice;
	private List<String> optionTools;
	private static final String[] ZROCK = new String[] {"Package","Device Info","ZRock Package"};
	private static final int POS_PACKAGE = 0;
    private static final int POS_DEVICE_INFO = 1;
    private static final int POS_ZROCK_PACKAGE = 2;
    
	private static final String[] PACKAGE = new String[] {"Un/Installer","Plugin","Backup","Updater","Monitor","Libraries","Editor"};
	private static final int POS_INSTALLER = 0;
    private static final int POS_PLUGIN = 1;
    private static final int POS_BACKUP = 2;
    private static final int POS_UPDATER = 3;
    private static final int POS_MONITOR = 4;
    private static final int POS_LIBRARIES = 5;
	private static final int POS_EDITOR = 6;

	private View headerView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		headerView = inflater.inflate(R.layout.fragment_application_dashboard , container , false);
		aboutIcon = (ImageView) headerView.findViewById(R.id.icon_engine);
		aboutIcon.setImageResource(R.drawable.ic_launcher);
		aboutAppName = (TextView) headerView.findViewById(R.id.name_activity);
		aboutAppName.setText(R.string.app_activity_engine);
		aboutAppName.setTextColor(UIUtils.getThemeColorFromAttrOrRes(headerView.getContext(), R.attr.about_libraries_title_openSource, R.color.about_libraries_title_openSource));
		aboutSpecialContainer = headerView.findViewById(R.id.aboutSpecialContainer);
		aboutSpecial1 = (Button) headerView.findViewById(R.id.aboutSpecial1);
		aboutSpecial1.setText("Home");
		aboutSpecial1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					ContentActivity.start(getActivity());
					Toast.makeText(getActivity(),"HOME" ,Toast.LENGTH_SHORT);
				}
			});
		aboutSpecial2 = (Button) headerView.findViewById(R.id.aboutSpecial2);
		aboutSpecial2.setText("Device");
		aboutSpecial2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					optionDevice = Arrays.asList(ZROCK);			
					PickerUISettings pickerUISettings = new PickerUISettings.Builder()
						.withItems(optionDevice)
						.withBackgroundColor(Color.WHITE)
						.withAutoDismiss(true)
						.withItemsClickables(true)
						.withUseBlur(true)
						.build();

                    //Populate list
				    mPickerUI.setSettings(pickerUISettings);
					mPickerUI.setOnClickItemPickerUIListener(Device);
					if (currentPosition == -1)
					{
						mPickerUI.slide();
					}
					else
					{
						mPickerUI.slide(currentPosition);
					}
				}
			});
		aboutSpecial3 = (Button) headerView.findViewById(R.id.aboutSpecial3);
		aboutSpecial3.setText("Tools");
		aboutSpecial3.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					optionTools = Arrays.asList(PACKAGE);	
					PickerUISettings pickerUISettings = new PickerUISettings.Builder()
					    .withItems(optionTools)
						.withBackgroundColor(Color.WHITE)
						.withAutoDismiss(true)
						.withItemsClickables(true)
						.withUseBlur(true)
						.build();

					//Populate list
				    mPickerUI.setSettings(pickerUISettings);
					mPickerUI.setOnClickItemPickerUIListener(Tools);
					if (currentPosition == -1)
					{
						mPickerUI.slide();
					}
					else
					{
						mPickerUI.slide(currentPosition);
					}
				}
			});
		aboutVersion = (TextView) headerView.findViewById(R.id.activity_version);
		aboutVersion.setText(R.string.hello_mil);

		mPickerUI = (PickerUI) headerView.findViewById(R.id.picker_ui_view);
        mPickerUI.setColorTextCenter(R.color.background_picker);
        mPickerUI.setColorTextNoCenter(R.color.background_picker);
        mPickerUI.setBackgroundColorPanel(R.color.background_picker);
        mPickerUI.setLinesColor(R.color.background_picker);
        mPickerUI.setItemsClickables(true);
        mPickerUI.setAutoDismiss(false);

        return headerView;
	}

	private PickerUI.PickerUIItemClickListener Device = new PickerUI.PickerUIItemClickListener() {

		@Override
		public void onItemClickPickerUI(int which, int position, String valueResult)
		{
			currentPosition = position;
			if (position == POS_PACKAGE)
			{
			     PackageActivity.start(getActivity());
			}
			if (position == POS_DEVICE_INFO)
			{
				DeviceInfoActivity.start(getActivity());
			}
			if (position == POS_ZROCK_PACKAGE)
			{
				ZRockPackageActivity.start(getActivity());
			}
			Toast.makeText(getActivity(), valueResult, Toast.LENGTH_SHORT).show();
		}
	};

	private PickerUI.PickerUIItemClickListener Tools = new PickerUI.PickerUIItemClickListener() {

		@Override
		public void onItemClickPickerUI(int which, int position, String valueResult)
		{
			currentPosition = position;
			if (position == POS_INSTALLER)
			{
				InstallActivity.start(getActivity());
			}
			if (position == POS_PLUGIN)
			{
				PluginActivity.start(getActivity());
			}
			if (position == POS_BACKUP)
			{
				BackupActivity.start(getActivity());
			}
			if (position == POS_UPDATER)
			{
				UpdaterActivity.start(getActivity());
			}
			if (position == POS_LIBRARIES)
			{
				LibrariesActivity.start(getActivity());
			}
			if (position == POS_MONITOR)
			{
				MonitorActivity.start(getActivity());
			}
			if (position == POS_EDITOR)
			{
				EditorActivity.start(getActivity());
			}
			//mPickerUI.setAutoDismiss(true);
			Toast.makeText(getActivity(), valueResult, Toast.LENGTH_SHORT).show();
		}
	};
}
