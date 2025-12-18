package com.klu.dat.io;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class ReportWriter {

    public void writeReport(Path file, Map<String, Double> data, String title)
            throws IOException {

        StringBuilder sb = new StringBuilder();

        sb.append("DATA ANALYSIS REPORT\n");
        sb.append(title).append("\n\n");

        for (var e : data.entrySet()) {
            sb.append(e.getKey()).append(" = ").append(e.getValue()).append("\n");
        }

        Files.writeString(file, sb.toString());
    }
}