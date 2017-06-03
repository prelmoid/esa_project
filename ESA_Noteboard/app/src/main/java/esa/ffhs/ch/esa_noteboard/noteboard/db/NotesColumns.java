package esa.ffhs.ch.esa_noteboard.noteboard.db;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public interface NotesColumns {

    //Primärschlüssel
    String ID = "idnotes";

    //Pflichtfeld: Titel der Notiz - text
    String TITLE = "title";

    //Inhalt der Notiz - text
    String NOTE = "note";

    //Stichworte der Notiz - text
    String KEYWORDS = "keywords";

    //Ortsinformation der Notiz - text
    String LOCATION = "location";

    //Pflichtfeld: Erstellungsdatum - datetime
    String CREATEDATE = "createdate";
}
