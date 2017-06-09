package esa.ffhs.ch.esa_noteboard.noteboard.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public class DatabaseNotes extends SQLiteOpenHelper {

    // Datenbankversion
    private static final int DB_VERSION = 7;
    // Datenbankname
    private static final String DB_NAME = "noteboard";
    //Instanz
    private static DatabaseNotes sINSTANCE;
    //Lockobjekt
    private static Object sLOCK = "";

    /*
    * Instanz der Datenbank erhalten
    * @param context Context der aufrufenden Anwendung.
    * @reutrn Das 'eine' Exemplar der Datenbenk, welches in der Anwedung verwendet wird.
     */
    public static DatabaseNotes getInstance(Context context) {
        if (sINSTANCE == null) {
            synchronized (sLOCK) {
                if (sINSTANCE == null && context != null) {
                    sINSTANCE = new DatabaseNotes(context.getApplicationContext());
                }
            }
        }
        return sINSTANCE;
    }

    /*
    *   Constructor
     */
    private DatabaseNotes(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabellen erstellen
        db.execSQL(NotesTbl.SQL_CREATE);
        db.execSQL(NotesTbl.STMT_INSERT_TEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //bisherige Tabellen droppen
        db.execSQL(NotesTbl.SQL_DROP);
        //DB neu erstellen
        onCreate(db);
    }
}
