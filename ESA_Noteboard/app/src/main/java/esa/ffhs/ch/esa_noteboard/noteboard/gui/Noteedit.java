package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import esa.ffhs.ch.esa_noteboard.R;
import esa.ffhs.ch.esa_noteboard.noteboard.db.DatabaseNotes;
import esa.ffhs.ch.esa_noteboard.noteboard.db.NotesColumns;
import esa.ffhs.ch.esa_noteboard.noteboard.db.NotesTbl;
import esa.ffhs.ch.esa_noteboard.noteboard.notes.Notes;

/**
 * Created by Benjamin Kaeslin on 07.06.2017.
 */

public class Noteedit extends AppCompatActivity {
    private DatabaseNotes mDbNotes;
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
        if (idnotes == null) {
            //Leeres Notes Objekt erstellen
            note = new Notes();
            TextView tEditCreatedate = (TextView) findViewById(R.id.editDate);
            String dateString = DateFormat.getDateTimeInstance().format(note.getCreatedate());
            tEditCreatedate.setText(dateString);
        } else {
            //bestehendes Notes Objekt laden
            loadNotes(idnotes);
            Toast.makeText(getApplicationContext(), "idnotes übergeben: " + idnotes, Toast.LENGTH_LONG).show();
            initView();
        }

    }

    @Override
    public void onPause() {
        saveNote();
        super.onPause();
    }

    private void loadNotes(String idnotes) {
        String[] params = new String[]{idnotes};
        Cursor cNotes = mDbNotes.getReadableDatabase().rawQuery("SELECT _id, title, note, keywords, location, createdate FROM notes WHERE _id = ?", params);
        note = new Notes(cNotes);
        cNotes.close();
    }

    private void initView() {
        //view abfüllen
        TextView tEditTitle = (TextView) findViewById(R.id.editTitle);
        tEditTitle.setText(note.getTitle());

        TextView tEditNote = (TextView) findViewById(R.id.editNote);
        tEditNote.setText(note.getNote());

        TextView tEditKeyword = (TextView) findViewById(R.id.editKeywords);
        tEditKeyword.setText(note.getKeywords());

        TextView tEditLocation = (TextView) findViewById(R.id.editLocation);
        tEditLocation.setText(note.getLocation());


        TextView tEditCreatedate = (TextView) findViewById(R.id.editDate);
        String dateString = DateFormat.getDateTimeInstance().format(note.getCreatedate());
        tEditCreatedate.setText(dateString);
    }


    private void saveNote() {
        TextView tEditTitle = (TextView) findViewById(R.id.editTitle);
        note.setTitle(tEditTitle.getText().toString());

        TextView tEditNote = (TextView) findViewById(R.id.editNote);
        note.setNote(tEditNote.getText().toString());

        TextView tEditKeyword = (TextView) findViewById(R.id.editKeywords);
        note.setKeywords(tEditKeyword.getText().toString());

        TextView tEditLocation = (TextView) findViewById(R.id.editLocation);
        note.setLocation(tEditLocation.getText().toString());

        Long createDate = note.getCreatedate().getTime() / 1000;
        Log.d("time", createDate.toString());
        if (note.getIdnotes() < 1) {
            //new Note, INSERT
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesColumns.TITLE, note.getTitle());
            contentValues.put(NotesColumns.NOTE, note.getNote());
            contentValues.put(NotesColumns.KEYWORDS, note.getKeywords());
            contentValues.put(NotesColumns.LOCATION, note.getLocation());
            contentValues.put(NotesColumns.CREATEDATE, createDate);
            long idnew = mDbNotes.getWritableDatabase().insert(NotesTbl.TABLE_NAME, null, contentValues);
            String lIdNotes = String.valueOf(idnew);
            loadNotes(lIdNotes);
        } else {
            //existing Note, UPDATE
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesColumns.TITLE, note.getTitle());
            contentValues.put(NotesColumns.NOTE, note.getNote());
            contentValues.put(NotesColumns.KEYWORDS, note.getKeywords());
            contentValues.put(NotesColumns.LOCATION, note.getLocation());
            contentValues.put(NotesColumns.CREATEDATE, createDate);
            mDbNotes.getWritableDatabase().update(NotesTbl.TABLE_NAME, contentValues, "_id=" + note.getIdnotes(), null);
        }

    }

    private void initToolBar() {
        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.topToolbarEdit);
        mTopToolbar.setTitle(R.string.title_note_edit);
        setSupportActionBar(mTopToolbar);

        mTopToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        //Navigation Klickt zur vorhergehenden View
        mTopToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        //Speichern auslösen
        if (id == R.id.action_save) {
            saveNote();
            //Toast.makeText(Noteedit.this, "Save action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
