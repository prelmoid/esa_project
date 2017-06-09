package esa.ffhs.ch.esa_noteboard.noteboard.gui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import esa.ffhs.ch.esa_noteboard.R;
import esa.ffhs.ch.esa_noteboard.noteboard.db.DatabaseNotes;
import esa.ffhs.ch.esa_noteboard.noteboard.db.NotesTbl;

/**
 * Created by Benjamin Kaeslin on 09.06.2017.
 */

public class Notesearch extends AppCompatActivity {

    private ListView lvListView;
    private SimpleCursorAdapter scaListAdapter;
    private SearchView svSearchView;
    private DatabaseNotes dbNotes;
    private Toolbar tbTopToolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notesearch);

        // Laden des Layouts
        lvListView = (ListView) findViewById(R.id.searchList);

        //Datenbank initialisieren
        dbNotes = DatabaseNotes.getInstance(this);

        initToolBar();


        //Laden der Daten
        Cursor cursor = dbNotes.getReadableDatabase().rawQuery("SELECT _id, title, strftime('%d.%m.%Y %H:%M:%S',createdate,'unixepoch') as createdate FROM notes ORDER BY createdate DESC", null);
        scaListAdapter = new SimpleCursorAdapter(this, R.layout.listview_note_layout, cursor, new String[]{"_id",
                "title", "createdate"}, new int[]{R.id.tvCode, R.id.tvTitle, R.id.tvCreatedate}, 0);

        lvListView.setAdapter(scaListAdapter);
        registerForContextMenu(lvListView);

        // Die Searchview verknüpfen und den Listener registrieren
        svSearchView = (SearchView) findViewById(R.id.searchView);
        svSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //listAdapter.getFilter().filter(newText);
                try {
                    // String mQuery = "SELECT  * FROM " + DatabaseContract.SongBook.TABLE_NAME+" WHERE _ID LIKE '%"+newText+ "%'";
                    String[] selectionArgs;
                    selectionArgs= new String[] {"%"+ newText + "%", "%"+ newText + "%", "%"+ newText + "%", "%"+ newText + "%" };
                    Cursor cursor = dbNotes.getReadableDatabase().rawQuery("SELECT _id, title, strftime('%d.%m.%Y %H:%M:%S',createdate,'unixepoch') as createdate FROM notes  WHERE note LIKE ? OR title LIKE ? OR keywords LIKE ? OR location LIKE ? ORDER BY createdate DESC", selectionArgs);
                    SimpleCursorAdapter searchAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.listview_note_layout, cursor, new String[]{"_id",
                            "title", "createdate"}, new int[]{R.id.tvCode, R.id.tvTitle, R.id.tvCreatedate}, 0);

                    lvListView.setAdapter(searchAdapter);
                    TextView text = (TextView) findViewById(R.id.empty);
                    text.setText(R.string.txt_empty_search);
                    lvListView.setEmptyView(text);

                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
    public void initToolBar() {
        tbTopToolbar = (Toolbar) findViewById(R.id.topToolbar);
        tbTopToolbar.setTitle(R.string.title_search);
        setSupportActionBar(tbTopToolbar);
        tbTopToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        //Navigation Klickt zur vorhergehenden View
        tbTopToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        //Eindeutige ID der Note aus dem Cursor lesen, zum weitergeben
        AdapterView.AdapterContextMenuInfo info=
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int itemPosition = info.position;
        Cursor cursor = scaListAdapter.getCursor();
        cursor.moveToPosition(itemPosition);
        int idnotes = cursor.getInt(0);
        if(item.getTitle()==getText(R.string.txt_edit)){
            //Test Nachricht
            //Toast.makeText(getApplicationContext(),Integer.toString(idnotes),Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Notesearch.this, Noteedit.class);
            myIntent.putExtra("idnotes", Integer.toString(idnotes)); //Optional parameters
            Notesearch.this.startActivity(myIntent);
        } else if(item.getTitle()==getText(R.string.txt_send)){
            Toast.makeText(getApplicationContext(),getText(R.string.txt_send)+"idnotes"+String.valueOf(idnotes),Toast.LENGTH_LONG).show();
        } else if(item.getTitle()==getText(R.string.txt_delete)) {
            String[] params = new String[]{Integer.toString(idnotes)};
            dbNotes.getWritableDatabase().delete(NotesTbl.TABLE_NAME,"_id="+Integer.toString(idnotes),null);
            reloadData();
            Toast.makeText(getApplicationContext(), getText(R.string.txt_deleted), Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

    private void reloadData(){
        Cursor newCursor= dbNotes.getReadableDatabase().rawQuery("SELECT _id, title, strftime('%d.%m.%Y %H:%M:%S',createdate,'unixepoch') as createdate FROM notes ORDER BY createdate DESC",null);
        scaListAdapter.swapCursor(newCursor);
        scaListAdapter.notifyDataSetChanged();
    }
}
