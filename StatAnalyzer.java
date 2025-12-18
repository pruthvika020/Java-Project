package com.klu.dat.analyzer;

import com.klu.dat.dataset.Dataset;
import com.klu.dat.util.MathUtil;

import java.util.*;
import java.util.stream.DoubleStream;

public class StatAnalyzer implements Analyzer {
    @Override
    public Map<String, Double> analyze(Dataset ds, Map<String, String> options) throws Exception {
        Map<String, Double> out = new LinkedHashMap<>();

        // Get column name from options
        String col = options.get("column");

        if (col != null) {
            double[] data = ds.getColumn(col);  // may throw MissingColumnException

            // Compute statistics
            double sum = DoubleStream.of(data).sum();
            double mean = sum / data.length;
            double var = MathUtil.populationVariance(data,mean);

            // Store results in map
            out.put("sum", sum);
            out.put("mean", mean);
            out.put("variance", var);
        }

        return out;
    }
}