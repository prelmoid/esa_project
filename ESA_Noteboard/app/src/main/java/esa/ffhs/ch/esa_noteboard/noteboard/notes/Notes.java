package esa.ffhs.ch.esa_noteboard.noteboard.notes;

import java.util.Date;

/**
 * Created by Benjamin Kaeslin on 03.06.2017.
 */

public class Notes {
    private int idnotes;
    private String title;
    private String description;
    private String keywords;
    private Date createdate;
    private String place;

    public Notes() {
    }

    public Notes(int idnotes, String title, String description, String keywords, String place) {
        this.idnotes = idnotes;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.place = place;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
