package com.klu.dat.main;

import com.klu.dat.dataset.*;
import com.klu.dat.factory.*;
import com.klu.dat.analyzer.*;
import com.klu.dat.io.ReportWriter;

import java.nio.file.*;
import java.util.*;

public class MainApp {

    // 🔥 This method creates dataset.csv automatically
    private static void createDefaultCSV() throws Exception {
        String data =
                "age,height,weight\n" +
                "20,170,65\n" +
                "22,172,68\n" +
                "21,171,66\n" +
                "23,174,70\n";

        Files.writeString(Path.of("dataset.csv"), data);
        System.out.println("Created dataset.csv automatically.");
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            // Create CSV automatically
            createDefaultCSV();

            // Use the file without asking user
            String path = "dataset.csv";

            Dataset ds = new CSVDataset(path);

            System.out.println("Available Columns:");
            for (String h : ds.getHeaders()) System.out.println(" -> " + h);

            System.out.print("Enter column name (age/height/weight): ");
            String col = sc.nextLine();

            Analyzer analyzer = AnalyzerFactory.getAnalyzer("stat");

            Map<String, String> options = new HashMap<>();
            options.put("column", col);

            Map<String, Double> result = analyzer.analyze(ds, options);

            ReportWriter rw = new ReportWriter();
            rw.writeReport(Path.of("analysis.txt"), result, "Auto-generated CSV Report");

            System.out.println("Report generated: analysis.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}