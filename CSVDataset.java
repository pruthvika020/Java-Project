package com.klu.dat.dataset;

import com.klu.dat.exception.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVDataset extends Dataset {
    private final Map<String, double[]> columns = new LinkedHashMap<>();

    public CSVDataset(String path) throws IOException, MalformedDataException {
        List<String> lines = Files.readAllLines(Path.of(path));
        if (lines.isEmpty()) throw new MalformedDataException("Empty CSV!");

        headers = lines.get(0).split(",");

        int colCt = headers.length;
        List<List<Double>> temp = new ArrayList<>();
        for (int i = 0; i < colCt; i++) temp.add(new ArrayList<>());

        for (int r = 1; r < lines.size(); r++) {
            String[] parts = lines.get(r).split(",");

            if (parts.length != colCt)
                throw new MalformedDataException("Malformed row: " + (r + 1));

            for (int c = 0; c < colCt; c++) {
                try {
                    temp.get(c).add(Double.parseDouble(parts[c]));
                } catch (NumberFormatException e) {
                    throw new MalformedDataException("Invalid number in row " + (r + 1));
                }
            }
        }

        for (int c = 0; c < colCt; c++) {
            double[] arr = temp.get(c).stream().mapToDouble(Double::doubleValue).toArray();
            columns.put(headers[c], arr);
        }
    }

    public double[] getColumn(String name) throws MissingColumnException {
        if (!columns.containsKey(name))
            throw new MissingColumnException("Column not found: " + name);
        return columns.get(name);
    }

    public int columnCount() {
        return headers.length;
    }
}