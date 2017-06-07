package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
}
