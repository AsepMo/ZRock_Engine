package zrock.application.engine;

import zrock.application.engine.R;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import okio.BufferedSink;
import okio.Okio;

import java.util.Map;
import java.io.File;
import java.io.IOException;

public class ApplicationMain extends Application {
    private static final String INTERNAL_FILE_NAME = "internal.dat";
	
	@Override
    public void onCreate() {
        super.onCreate();

        createInternalFileIfNecessary();
    }

    private void createInternalFileIfNecessary() {
        File internalFile = getInternalFile(this);
        if (!internalFile.exists()) {
            createInternalFile(internalFile);
            writeSecretToInternalFile(internalFile);
        }
    }

    private void createInternalFile(File internalFile) {
        try {
            boolean success = internalFile.createNewFile();
            if (!success) {
                throw new RuntimeException("File wasn't created");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file", e);
        }
    }

    private void writeSecretToInternalFile(File internalFile) {
        try {
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(internalFile));
            try {
                bufferedSink.writeUtf8("secret");
            } finally {
                bufferedSink.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }

    public static File getInternalFile(Context context) {
        File filesDir = context.getFilesDir();
        return new File(filesDir, INTERNAL_FILE_NAME);
    }
}
