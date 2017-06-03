package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import esa.ffhs.ch.esa_noteboard.R;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public class Noteoverview extends AppCompatActivity {

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteoverview);
        initToolBar();


        setSupportActionBar(mTopToolbar);
    }

    public void initToolBar() {
        mTopToolbar = (Toolbar)findViewById(R.id.topToolbar);
        mTopToolbar.setTitle(R.string.title_note_overview);
        setSupportActionBar(mTopToolbar);

        //mTopToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Die toolbar_top zur Menübar hinzufügen.
        getMenuInflater().inflate(R.menu.toolbar_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Abhandlung der Klicks auf die Actionbar.
        int id = item.getItemId();

        //Testaktion für den Moment
        //TODO korrekte Verlinkung zur Suche
        if (id == R.id.action_search) {
            Toast.makeText(Noteoverview.this, "Search Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

