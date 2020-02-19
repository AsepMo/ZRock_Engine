package zrock.application.engine.app.content;

import zrock.application.engine.R;
import zrock.application.engine.content.resolver.SafeContentResolver;
import zrock.application.engine.content.resolver.SafeContentResolverCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import okio.BufferedSource;
import okio.Okio;

public class ShareReceiverActivity extends AppCompatActivity {
    private TextView extraStreamValue;
    private TextView extraStreamContentsTraditional;
    private TextView extraStreamContentsSafe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_receiver);

		// Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_activity_engine_plugin);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
		
        extraStreamValue = (TextView) findViewById(R.id.extraStreamValue);
        extraStreamContentsTraditional = (TextView) findViewById(R.id.extraStreamContentsTraditional);
        extraStreamContentsSafe = (TextView) findViewById(R.id.extraStreamContentsSafe);

        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            displaySendIntent(intent);
        } else {
            finish();
        }
    }

    private void displaySendIntent(Intent intent) {
        Uri streamUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);

        String streamUriValue = streamUri.toString();
        extraStreamValue.setText(streamUriValue);

        try {
            String streamContents = readStreamContentsTraditional(streamUri);
            extraStreamContentsTraditional.setText(String.format("\"%s\"", streamContents.trim()));
        } catch (FileNotFoundException e) {
            extraStreamContentsTraditional.setText("Error opening file: " + e.getMessage());
        } catch (IOException e) {
            extraStreamContentsTraditional.setText("Error reading file: " + e.getMessage());
        }

        try {
            String streamContents = readStreamContentsSafe(streamUri);
            extraStreamContentsSafe.setText(String.format("\"%s\"", streamContents.trim()));
        } catch (FileNotFoundException e) {
            extraStreamContentsSafe.setText("Error opening file: " + e.getMessage());
        } catch (IOException e) {
            extraStreamContentsSafe.setText("Error reading file: " + e.getMessage());
        }
    }

    private String readStreamContentsTraditional(Uri streamUri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(streamUri);
        return readStreamContents(inputStream);
    }

    private String readStreamContentsSafe(Uri streamUri) throws IOException {
        SafeContentResolver safeContentResolver = SafeContentResolverCompat.newInstance(this);
        InputStream inputStream = safeContentResolver.openInputStream(streamUri);
        return readStreamContents(inputStream);
    }

    private String readStreamContents(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new FileNotFoundException("openInputStreamForFile() returned null");
        }

        BufferedSource bufferedSource = Okio.buffer(Okio.source(inputStream));
        try {
            return bufferedSource.readUtf8();
        } finally {
            bufferedSource.close();
        }
    }
}
