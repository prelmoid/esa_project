package esa.ffhs.ch.esa_noteboard.noteboard.db;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public final class NotesTbl implements NotesColumns {
    //Name der Datenbanktablle
    public static final String TABLE_NAME = "notes";


    /*
    * SQL Anweisung zur Schemadefinition
     */
    public static final String SQL_CREATE = "CREATE TABLE notes (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT NOT NULL," +
            "note TEXT," +
            "keywords TEXT," +
            "location TEXT," +
            "createdate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP);";

    /*
    * Standardsortierung für die Tabelle der Notizen
     */
    public static final String DEFAULT_SORT_ORDER = CREATEDATE + " DESC";

    /*
    * SQL-Anweisung zur Löschung der Tabelle
     */
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /*
    * SQL-Anweisung zur Erstellung einer minimalen Notiz
     */
    public static final String STMT_MIN_INSERT = "INSERT INTO " + TABLE_NAME + "(" + TITLE + "," + CREATEDATE + ") VALUES(?,?)";

    /*
    * SQL-Anweisung zur Erstellung einer vollständigen Notiz
     */
    public static final String STMT_INSERT = "INSERT INTO " + TABLE_NAME + "(" + TITLE + "," + NOTE + "," + KEYWORDS + "," + LOCATION + "," + CREATEDATE + ") VALUES(?,?,?,?,?)";

    /*
    * SQL-Anweisung zur Löschung aller Notizen
     */
    public static final String STMT_DELETE = "DELETE " + TABLE_NAME;

    /*
    * SQL-Anweisung zur Löschung einer Notiz
     */
    public static final String STMT_DELETE_BY_ID = "DELETE " + TABLE_NAME + " WHERE " + ID + "=?";

    //Liste aller Attribute.
    public static final String[] ALL_COLUMNS = new String[]{
            ID,
            TITLE,
            NOTE,
            KEYWORDS,
            LOCATION,
            CREATEDATE
    };

    //WHERE-Bedingung für Abfrage über ID
    public static final String WHERE_ID_EQUALS = ID + "=?";

    //TEST DATEN ERSTELLEN
    public static final String STMT_INSERT_TEST = "INSERT INTO " + TABLE_NAME + "(" + TITLE + "," + CREATEDATE +") VALUES('NOTE1',datetime()), ('NOTE2',datetime())";

    //Klasse für Konstanten, somit keine Objekterzeugung vorgesehen
    private NotesTbl() {

    }
}
