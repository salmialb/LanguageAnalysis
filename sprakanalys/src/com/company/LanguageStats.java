package com.company;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class LanguageStats {
    HashMap<String, Language> languages = new HashMap<String, Language>();
    HashMap<Double, String> results1 = new HashMap<>();
    HashMap<Double, String> results2 = new HashMap<>();
    HashMap<Double, String> results3 = new HashMap<>();
    HashMap<Double, String> totalResults = new HashMap<>();

    private static void printTotalResults(HashMap<Double, String> rt) {
        ArrayList<Double> sortedKeys = new ArrayList<Double>(rt.keySet());
        Collections.sort(sortedKeys);
        int rank = 1;
        System.out.println();
        System.out.println("Slutliga resultat i rangordning: ");
        System.out.println("Språk          " + "Totalt" + "       Rang");
        final Object[][] table = new String[1][];
        for (Double x : sortedKeys) {
            table[0] = new String[] { rt.get(x), String.valueOf(Math.round(x * 1000.0) / 1000.0), String.valueOf(rank++) };
            for (final Object[] row : table) {
                System.out.format("%-15s%-15s%-15s\n", row);
            }
        }



    }

    public void addLanguage(Language la) {
        languages.put(la.getLanguageLabel(), la);
        la.calculateCharDistribution();
        la.calculateTripletCharDistribution();
        la.calculatefirstCharDistribution();

    }

    public void guessLanguage(Language la) {
        la.calculateCharDistribution();
        la.calculateTripletCharDistribution();
        la.calculatefirstCharDistribution();

        Set<String> langKeys = languages.keySet();
        int ite = 1;
        System.out.println();
        System.out.println("Analys resultat:");
        System.out.println("Språk " + "    Analys1 " + "Analys2 " + "Analys3 " + "Kombinerat ");
        for (String language : langKeys) {

            ArrayList<String> chars = new ArrayList<>();
            ArrayList<String> FirstChars = new ArrayList<>();
            double charDisDifTotal = 0;
            double charDisDifTripleTotal = 0;
            double charDisDifFirstTotal = 0;
            double total = 0;
            double charDisDif = 0;
            double charDisDifFirst = 0;
            double charDisDifTriple = 0;

            for (int i = 0; i < la.getContent2().length(); i++) {
                if (!chars.contains(String.valueOf(la.getContent2().charAt(i)))) {
                    if (languages.get(language).charDistribution.get(String.valueOf(la.getContent2().charAt(i))) != null) {
                        charDisDif = Math.pow(la.charDistribution.get(String.valueOf(la.getContent2().charAt(i))) -
                                (languages.get(language).charDistribution.get(String.valueOf(la.getContent2().charAt(i)))), 2);
                        chars.add(String.valueOf(la.getContent2().charAt(i)));

                    }else{
                       charDisDif = la.charDistribution.get(String.valueOf(la.getContent2().charAt(i)));

                    }
                    charDisDifTotal += charDisDif;
                }
            }

            String[] myName = la.getContent().toLowerCase().trim().replaceAll(" +", " ").split(" ");

            for (int k = 0; k < myName.length; k++) {

                if (!FirstChars.contains((String.valueOf(myName[k].charAt(0))))) {
                    if (languages.get(language).firstCharDistribution.get(myName[k].charAt(0)) != null) {
                        charDisDifFirst = Math.pow(la.firstCharDistribution.get(myName[k].charAt(0)) -
                                languages.get(language).firstCharDistribution.get((myName[k].charAt(0))), 2);
                        FirstChars.add(String.valueOf(myName[k].charAt(0)));

                    }else{
                        charDisDifFirst = la.firstCharDistribution.get(myName[k].charAt(0));
                    }
                }

                charDisDifFirstTotal += charDisDifFirst;
            }
            ArrayList<String> valueList = new ArrayList<String>(la.tripleCharDistribution.keySet());
            for (int j = 0; j < valueList.size(); j++) {

                if (languages.get(language).tripleCharDistribution.containsKey(valueList.get(j))) {

                    charDisDifTriple = Math.pow(la.tripleCharDistribution.get(valueList.get(j)) -
                            languages.get(language).tripleCharDistribution.get(String.valueOf(valueList.get(j))), 2);

                }else{
                    charDisDifTriple = la.tripleCharDistribution.get(valueList.get(j));
                }

                charDisDifTripleTotal += charDisDifTriple;

                total = (charDisDifTotal + charDisDifTripleTotal + charDisDifFirstTotal) / 3;
            }

            System.out.println(language + "   " + Math.round(charDisDifTotal * 1000.0) / 1000.0 + "   " + Math.round(charDisDifTripleTotal * 1000.0) / 1000.0 + "    " + Math.round(charDisDifFirstTotal * 1000.0) / 1000.0 + "       " + Math.round(total * 1000.0) / 1000.0);

            results1.put(charDisDifTotal, language);
            results2.put(charDisDifTripleTotal, language);
            results3.put(charDisDifFirstTotal, language);
            totalResults.put(total, language);


        }
        printTotalResults(totalResults);
    }

}

