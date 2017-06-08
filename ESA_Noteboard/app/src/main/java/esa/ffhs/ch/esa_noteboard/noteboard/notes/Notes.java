package esa.ffhs.ch.esa_noteboard.noteboard.notes;

import android.database.Cursor;

import java.util.Date;

import esa.ffhs.ch.esa_noteboard.noteboard.db.NotesColumns;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public class Notes {
    private int idnotes;
    private String title;
    private String note;
    private String keywords;
    private Date createdate;
    private String location;

    public Notes() {
    }

    public Notes(Cursor cursor){
        try
        {   //Columnindex aus Cursor ermitteln
            int idnotexIdx = cursor.getColumnIndex(NotesColumns.ID);
            int titleIdx = cursor.getColumnIndex(NotesColumns.TITLE);
            int noteIdx = cursor.getColumnIndex(NotesColumns.NOTE);
            int keywordsIdx = cursor.getColumnIndex(NotesColumns.KEYWORDS);
            int createdateIdx = cursor.getColumnIndex(NotesColumns.CREATEDATE);
            int locationIdx = cursor.getColumnIndex(NotesColumns.LOCATION);
            //Daten aus Cursor in Objekt laden
            if( cursor != null && cursor.moveToFirst() ){
                this.idnotes = cursor.getInt(idnotexIdx);
                this.title = cursor.getString(titleIdx);
                this.note = cursor.getString(noteIdx);
                this.keywords = cursor.getString(keywordsIdx);
                this.createdate = new Date(cursor.getLong(createdateIdx)*1000);
                cursor.close();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setIdnotes(int idnotes){
        this.idnotes = idnotes;
    }

    public int getIdnotes() {
        return idnotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
