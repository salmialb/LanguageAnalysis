package com.company;


import java.lang.reflect.Array;
import java.util.*;

public class Language {
    HashMap<String, Double> charDistribution = new HashMap<String, Double>();
    HashMap<String, Double> tripleCharDistribution = new HashMap<String, Double>();
    HashMap<Character, Double> firstCharDistribution = new HashMap<>();
    private String content;
    private String languageLabel;

    public Language(String con, String languageL) {
        content = con;
        languageLabel = languageL;

    }

    public String getContent() {
        content = content.replaceAll("[-+.^:,{}()\"0123456789]", "").toLowerCase();
        return content;
    }

    public String getContent2(){
        content = content.replaceAll("[-+.^:,{}()\"0123456789\\s]", "").toLowerCase();
        return content;
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public HashMap<String, Double> calculateCharDistribution() {
        String text = getContent().replaceAll("\\s", "");

        for (int i = 0; i < text.length(); i++) {
            double numOfChar = 1;
            String c = String.valueOf(text.charAt(i));
            Double val = charDistribution.get(c);
            if (charDistribution.containsKey(c)) {
                charDistribution.put(c, charDistribution.get(c) + 1);

            } else {
                charDistribution.put(c, numOfChar);
            }

        }
        charDistribution.replaceAll((k, v) -> v = v / text.length());

        return charDistribution;
    }

    public HashMap<String, Double> calculateTripletCharDistribution() {

        String stripped = getContent().replaceAll(" +", "");

        for (int i = 0; i < stripped.length(); i++) {
            double numOfTriple = 1;
            try {
                String triple = stripped.substring(i, i + 3);

                if (tripleCharDistribution.containsKey(triple)) {
                    tripleCharDistribution.put(triple, tripleCharDistribution.get(triple) + 1);

                } else {
                    tripleCharDistribution.put(triple, numOfTriple);
                }
            } catch (StringIndexOutOfBoundsException ex) {
            }

        }
        tripleCharDistribution.replaceAll((k, v) -> v = v / tripleCharDistribution.size());

        return tripleCharDistribution;
    }

    public HashMap<Character, Double> calculatefirstCharDistribution() {
        String text = content.toLowerCase().trim().replaceAll(" +", " ");

        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++) {
            double numOfFirstChar = 1;
            String s = words[i];
            char firstLetter = s.charAt(0);

            if (firstCharDistribution.containsKey(firstLetter)) {
                firstCharDistribution.put(firstLetter, firstCharDistribution.get(firstLetter) + 1);

            } else {
                firstCharDistribution.put(firstLetter, numOfFirstChar);
            }
        }

        firstCharDistribution.replaceAll((k, v) -> v = v / words.length);
        return firstCharDistribution;
    }
}




