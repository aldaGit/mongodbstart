package org.hbrs.ia.solutions.model;

import org.bson.Document;

import java.util.Objects;

public class SalesMan {
    private String firstname;
    private String lastname;
    private Integer sid;

    public SalesMan(String firstname, String lastname, Integer sid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
    }

    public SalesMan(Document document) {
        this.firstname = document.getString("firstname");
        this.lastname = document.getString("lastname");
        this.sid = document.getInteger("sid");
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return sid;
    }

    public void setId(Integer sid) {
        this.sid = sid;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("firstname" , this.firstname );
        document.append("lastname" , this.lastname );
        document.append("sid" , this.sid);
        return document;
    }

    @Override
    public String toString() {
        return "SalesMan{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", sid=" + sid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesMan salesMan = (SalesMan) o;
        return Objects.equals(firstname, salesMan.firstname) && Objects.equals(lastname, salesMan.lastname) && Objects.equals(sid, salesMan.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, sid);
    }

    public class Constants {
        public static final String FIRST_NAME = "firstname";
        public static final String LAST_NAME = "lastname";
        public static final String SID = "sid";
        public static final String LEADERSHIP = "ship";
    }
}
