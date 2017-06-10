package esa.ffhs.ch.esa_noteboard.noteboard.db;

/**
 * Created by Benjamin Kaeslin on 10.06.2017.
 */

interface PicturesColumns {
    //Primärschlüssel
    String ID = "_id";

    //Pflichtfeld: Verlinkung zur Note - int
    String IDNOTES = "idnotes";

    //Ablageort der Bilddatei, zur Abrufung - text
    String LOOKUP_KEY = "lookup_key";
}
