package com.crm.util;

/**
 * Created by Dragos on 4/13/2016.
 */
public class DataTableResponse<T> {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private T[] data;
    private String error;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getrecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getrecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }
}
