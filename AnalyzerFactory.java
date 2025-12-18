package com.klu.dat.factory;

import com.klu.dat.analyzer.*;

public class AnalyzerFactory {

    public static Analyzer getAnalyzer(String type) {
        if (type.equalsIgnoreCase("stat"))
            return new StatAnalyzer();

        return null;
    }
}