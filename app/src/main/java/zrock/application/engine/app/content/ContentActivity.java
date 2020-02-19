package zrock.application.engine.app.content;

import zrock.application.engine.R;
import zrock.application.engine.ApplicationMain;

import java.io.File;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ContentActivity extends AppCompatActivity 
{
	public static void start(Context mContext){
		Intent mApplication = new Intent(mContext, ContentActivity.class);
		mContext.startActivity(mApplication);
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_engine_app_content);
		
        findViewById(R.id.shareFileButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shareInternalFile();
            }
        });
        findViewById(R.id.shareAllowedContentButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shareAllowedContent();
            }
        });
        findViewById(R.id.shareBlockedContentButton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                shareBlockedContent();
            }
        });
    }

    private void shareInternalFile() {
        File internalFile = ApplicationMain.getInternalFile(getApplicationContext());
        Uri streamUri = Uri.fromFile(internalFile);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        shareIntent.putExtra(Intent.EXTRA_STREAM, streamUri);
        shareIntent.setPackage(getPackageName());

        startActivity(shareIntent);
    }

    private void shareAllowedContent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        Uri contentUri = Uri.parse("content://" + SampleContentProvider.AUTHORITY + "/dummy");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setPackage(getPackageName());

        startActivity(shareIntent);
    }

    private void shareBlockedContent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("x-test/madeup");
        Uri contentUri = Uri.parse("content://" + SampleInternalContentProvider.AUTHORITY + "/dummy");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setPackage(getPackageName());

        startActivity(shareIntent);
    }
}
