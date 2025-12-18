package com.klu.dat.analyzer;

import com.klu.dat.dataset.Dataset;
import java.util.Map;

public interface Analyzer {
    Map<String, Double> analyze(Dataset ds, Map<String, String> options) throws Exception;
}