package com.klu.dat.dataset;

import com.klu.dat.exception.MissingColumnException;

public abstract class Dataset {
    protected String[] headers;

    public String[] getHeaders() {
        return headers;
    }

    public abstract double[] getColumn(String name) throws MissingColumnException;

    public abstract int columnCount();
}