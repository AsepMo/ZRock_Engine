package zrock.application.engine.app.libraries;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import zrock.application.engine.app.libraries.Libs;
import zrock.application.engine.app.libraries.entity.Library;
import zrock.application.engine.R;

public class LibsCard extends Card {
    private Context ctx;

    private Library library;

    public LibsCard(Context context, Libs libs, String libraryName) {
        super(libraryName);

        library = libs.getLibrary(libraryName);
    }

    public LibsCard(Context context, Library library) {
        super(library.getLibraryName());

        this.library = library;
    }

    @Override
    public View getCardContent(final Context context) {
        this.ctx = context;

        View view = LayoutInflater.from(context).inflate(R.layout.card_library, null);

        ((TextView) view.findViewById(R.id.libraryname)).setText(library.getLibraryName());
        ((TextView) view.findViewById(R.id.librarycreator)).setText(library.getAuthor());
        ((TextView) view.findViewById(R.id.libraryversion)).setText(library.getLibraryVersion());
        ((TextView) view.findViewById(R.id.description)).setText(library.getLibraryDescription());
        ((TextView) view.findViewById(R.id.description)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!library.getLibraryWebsite().equals("")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(library.getLibraryWebsite()));
                    context.startActivity(browserIntent);
                }
            }
        });

        return view;
    }

    @Override
    public boolean convert(View view) {

        ((TextView) view.findViewById(R.id.libraryname)).setText(library.getLibraryName());
        ((TextView) view.findViewById(R.id.librarycreator)).setText(library.getAuthor());
        ((TextView) view.findViewById(R.id.libraryversion)).setText(library.getLibraryVersion());
        ((TextView) view.findViewById(R.id.description)).setText(library.getLibraryDescription());
        ((TextView) view.findViewById(R.id.description)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!library.getLibraryWebsite().equals("")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(library.getLibraryWebsite()));
                    ctx.startActivity(browserIntent);
                }
            }
        });

        return false;
    }
}
