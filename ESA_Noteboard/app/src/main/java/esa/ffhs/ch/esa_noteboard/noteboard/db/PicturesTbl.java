package esa.ffhs.ch.esa_noteboard.noteboard.db;

/**
 * Created by Benjamin Kaeslin on 10.06.2017.
 */

class PicturesTbl implements PicturesColumns {
    //Name der Datenbanktablle
    private static final String TABLE_NAME = "pictures";


    /*
    * SQL Anweisung zur Schemadefinition
     */
    public static final String SQL_CREATE = "CREATE TABLE pictures (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idnotes INTEGER NOT NULL," +
            "lookup_key TEXT NOT NULL";

    /*
    * Standardsortierung für die Tabelle der Notizen
     */
    public static final String DEFAULT_SORT_ORDER = IDNOTES + " DESC";

    /*
    * SQL-Anweisung zur Löschung der Tabelle
     */
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /*
    * SQL-Anweisung zur Erstellung einer vollständigen Notiz
     */
    public static final String STMT_INSERT = "INSERT INTO " + TABLE_NAME + "(" + IDNOTES + "," + LOOKUP_KEY + ") VALUES(?,?)";

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
            IDNOTES,
            LOOKUP_KEY
    };

    //WHERE-Bedingung für Abfrage über ID
    public static final String WHERE_ID_EQUALS = ID + "=?";

    //Klasse für Konstanten, somit keine Objekterzeugung vorgesehen
    private PicturesTbl() {

    }
}
