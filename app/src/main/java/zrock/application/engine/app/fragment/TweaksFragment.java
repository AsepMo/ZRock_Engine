package zrock.application.engine.app.fragment;

import zrock.application.engine.R;
import zrock.application.engine.app.picker.PickerUI;
import zrock.application.engine.app.picker.PickerUISettings;
import zrock.application.engine.app.libraries.util.UIUtils;

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

public class TweaksFragment extends Fragment
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
	private static final String[] ROM = new String[] {"STOCK ROM","CUSTOM ROM"};
	private static final int POS_STOCK_ROM = 0;
    private static final int POS_CUSTOM_ROM = 1;

	private static final String[] TOOLS = new String[] {"TWRP","SP FLASH TOOLS","ODIN"};
	private static final int POS_TWRP = 0;
    private static final int POS_SP_FLASHTOOLS = 1;
    private static final int POS_ODIN = 2;

	private View headerView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		headerView = inflater.inflate(R.layout.fragment_application_dashboard , container , false);
		aboutIcon = (ImageView) headerView.findViewById(R.id.icon_engine);
		aboutIcon.setImageResource(R.drawable.ic_launcher);
		aboutAppName = (TextView) headerView.findViewById(R.id.name_activity);
		aboutAppName.setText(R.string.app_activity_engine_tweaks);
		aboutAppName.setTextColor(UIUtils.getThemeColorFromAttrOrRes(headerView.getContext(), R.attr.about_libraries_title_openSource, R.color.about_libraries_title_openSource));
		aboutSpecialContainer = headerView.findViewById(R.id.aboutSpecialContainer);
		aboutSpecial1 = (Button) headerView.findViewById(R.id.aboutSpecial1);
		aboutSpecial1.setText("Home");
		aboutSpecial2 = (Button) headerView.findViewById(R.id.aboutSpecial2);
		aboutSpecial2.setText("Script");
		aboutSpecial2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v)
				{
					optionDevice = Arrays.asList(ROM);			
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
					optionTools = Arrays.asList(TOOLS);	
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

			Toast.makeText(getActivity(), valueResult, Toast.LENGTH_SHORT).show();
		}
	};

	private PickerUI.PickerUIItemClickListener Tools = new PickerUI.PickerUIItemClickListener() {

		@Override
		public void onItemClickPickerUI(int which, int position, String valueResult)
		{
			currentPosition = position;
			Toast.makeText(getActivity(), valueResult, Toast.LENGTH_SHORT).show();
		}
	};
}
