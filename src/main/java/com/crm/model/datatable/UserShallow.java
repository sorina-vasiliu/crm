package com.crm.model.datatable;

/**
 * Entity class for modeling a UserShallow, in the datatable jquery.
 */
public class UserShallow {
    private int id;
    private String mail;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
