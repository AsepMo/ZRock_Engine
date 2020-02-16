package zrock.application.engine.app.libraries;

import zrock.application.engine.R;
import zrock.application.engine.app.libraries.LibsBuilder;
import zrock.application.engine.app.libraries.LibsActivity;

import android.os.Bundle;

public class ExtendActivity extends LibsActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
        Intent intent = new Intent();
        intent.putExtra(Libs.BUNDLE_FIELDS, Libs.toStringArray(R.string.class.getFields()));
        intent.putExtra(Libs.BUNDLE_LIBS, new String[]{"activeandroid", "caldroid"});
        setIntent(intent);
        */

        setIntent(new LibsBuilder().withFields(R.string.class.getFields()).withLibraries("activeandroid", "caldroid").withActivityTheme(R.style.MaterialDrawerTheme).intent(this));


        super.onCreate(savedInstanceState);
    }
}
