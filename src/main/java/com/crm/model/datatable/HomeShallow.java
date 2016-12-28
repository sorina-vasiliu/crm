package com.crm.model.datatable;

public class HomeShallow {
    private int id;
    private String html;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HomeShallow) {
            HomeShallow toCompare = (HomeShallow) o;
            return this.id == (toCompare.id);
        }
        return false;
    }
}
