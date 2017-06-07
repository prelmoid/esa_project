package esa.ffhs.ch.esa_noteboard.noteboard.gui;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import esa.ffhs.ch.esa_noteboard.R;
import esa.ffhs.ch.esa_noteboard.noteboard.db.DatabaseNotes;
import esa.ffhs.ch.esa_noteboard.noteboard.db.NotesColumns;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public class Noteoverview extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private ListView mListView;
    private SimpleCursorAdapter mListAdapter;
    private DatabaseNotes mDbNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Datenbank initialisieren
        mDbNotes = DatabaseNotes.getInstance(this);
        setContentView(R.layout.noteoverview);
        initToolBar();
        initListView(savedInstanceState);
    }

    public void initListView(Bundle savedInstanceState) {
        mListView = (ListView) findViewById(R.id.overviewList);
//        mListView.setEmptyView(findViewById(R.id.emptyView));

        Cursor cNotes = mDbNotes.getReadableDatabase().rawQuery("SELECT idnotes as _id, title, createdate FROM notes ORDER BY createdate DESC",null);
        int anz = cNotes.getCount();
        //Test, wieviele Rrecords in notes existieren
        //String anzString = Integer.toString(anz);
        //Log.d("notes","Anzahl Records: "+anzString);

        mListAdapter = new SimpleCursorAdapter(this, R.layout.listview_note_layout, cNotes, new String[]{"_id",
                "title", "createdate"}, new int[]{R.id.tvCode, R.id.tvTitle, R.id.tvCreatedate}, 0);

        //mListAdapter.notifyDataSetChanged();

        mListView.setAdapter(mListAdapter);
        registerForContextMenu(mListView);
    }

    public void initToolBar() {
        mTopToolbar = (Toolbar) findViewById(R.id.topToolbar);
        mTopToolbar.setTitle(R.string.title_note_overview);
        setSupportActionBar(mTopToolbar);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, getText(R.string.txt_edit));//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, getText(R.string.txt_send));
        menu.add(0, v.getId(), 0, getText(R.string.txt_delete));
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()==getText(R.string.txt_edit)){
            Toast.makeText(getApplicationContext(),"bearbeiten",Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Noteoverview.this, Noteedit.class);
            myIntent.putExtra("idnotes", 1); //Optional parameters
            Noteoverview.this.startActivity(myIntent);
        } else if(item.getTitle()==getText(R.string.txt_send)){
            Toast.makeText(getApplicationContext(),"sende",Toast.LENGTH_LONG).show();
        } else if(item.getTitle()==getText(R.string.txt_delete)) {
            Toast.makeText(getApplicationContext(), "löschen", Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }
}

