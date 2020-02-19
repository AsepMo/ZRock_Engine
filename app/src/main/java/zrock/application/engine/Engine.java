package zrock.application.engine;

import zrock.application.engine.app.content.*;

import java.io.File;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Engine
{
	public static void shareInternalFile(Context mContext) {
        File internalFile = ApplicationMain.getInternalFile(mContext.getApplicationContext());
        Uri streamUri = Uri.fromFile(internalFile);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        shareIntent.putExtra(Intent.EXTRA_STREAM, streamUri);
        shareIntent.setPackage(mContext.getPackageName());

        mContext.startActivity(shareIntent);
    }

    public static void shareAllowedContent(Context mContext) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        Uri contentUri = Uri.parse("content://" + SampleContentProvider.AUTHORITY + "/dummy");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setPackage(mContext.getPackageName());

        mContext.startActivity(shareIntent);
    }

    public static void shareBlockedContent(Context mContext) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        Uri contentUri = Uri.parse("content://" + SampleInternalContentProvider.AUTHORITY + "/dummy");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setPackage(mContext.getPackageName());

        mContext.startActivity(shareIntent);
    }
	
	public void addThemeToSharedPreferences(Context mContext,String theme) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(EngineActivity.THEME_PREFERENCES, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EngineActivity.THEME_SAVED, theme);
        editor.apply();
    }
}
