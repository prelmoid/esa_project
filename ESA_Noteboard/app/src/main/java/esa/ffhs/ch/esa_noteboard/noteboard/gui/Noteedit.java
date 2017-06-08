package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import esa.ffhs.ch.esa_noteboard.R;
import esa.ffhs.ch.esa_noteboard.noteboard.db.DatabaseNotes;
import esa.ffhs.ch.esa_noteboard.noteboard.notes.Notes;

/**
 * Created by Benjamin Kaeslin on 07.06.2017.
 */

public class Noteedit extends AppCompatActivity {
    private Toolbar mTopToolbar;
    private DatabaseNotes mDbNotes;
    private Cursor cNotes;
    private Notes note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Datenbank initialisieren
        mDbNotes = DatabaseNotes.getInstance(this);
        //View setzen
        setContentView(R.layout.noteedit);
        //Toolbar setgzen
        initToolBar();
        //Übergabe Parameter auslesen
        Intent intent = getIntent();
        String idnotes = intent.getStringExtra("idnotes");
        //Übergabe mit Toast testen
        //Toast.makeText(getApplicationContext(),idnotes,Toast.LENGTH_LONG).show();
        //Übergabe testen
        if(idnotes.isEmpty()){
            //Leeres Notes Objekt erstellen
            note = new Notes();
        }else{
            //bestehendes Notes Objekt laden
            String[] params = new String[]{idnotes};
            cNotes = mDbNotes.getReadableDatabase().rawQuery("SELECT * FROM notes WHERE idnotes = ?",params);
            note = new Notes(cNotes);
        }
    }

    public void initToolBar() {
        mTopToolbar = (Toolbar) findViewById(R.id.topToolbarEdit);
        mTopToolbar.setTitle(R.string.title_note_edit);
        setSupportActionBar(mTopToolbar);

        mTopToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        //Navigation Click für Back to previous View
        mTopToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
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
        if (id == R.id.action_save) {
            Toast.makeText(Noteedit.this, "Save action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
