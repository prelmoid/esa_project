package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import esa.ffhs.ch.esa_noteboard.R;
import esa.ffhs.ch.esa_noteboard.noteboard.db.DatabaseNotes;

/**
 * Created by Benjamin Kaeslin on 07.06.2017.
 */

public class Noteedit extends AppCompatActivity {
    private Toolbar mTopToolbar;
    private DatabaseNotes mDbNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Datenbank initialisieren
        mDbNotes = DatabaseNotes.getInstance(this);
        setContentView(R.layout.noteedit);
        initToolBar();
    }

    public void initToolBar() {
        mTopToolbar = (Toolbar) findViewById(R.id.topToolbarEdit);
        mTopToolbar.setTitle(R.string.title_note_edit);
        setSupportActionBar(mTopToolbar);

        mTopToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Die toolbar_top zur Menübar hinzufügen.
        getMenuInflater().inflate(R.menu.toolbar_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Abhandlung der Klicks auf die Actionbar.
        int id = item.getItemId();

        //Testaktion für den Moment
        //TODO korrekte Verlinkung zur Suche
        if (id == R.id.action_search) {
            Toast.makeText(Noteedit.this, "Search Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
