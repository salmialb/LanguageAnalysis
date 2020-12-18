package com.company;

import java.util.Scanner;

public class AddGuessLanguage {

    public static void InputText() {
        System.out.println("Enter the text to be analyzed: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        addLanguages(input);
    }

    public static void addLanguages(String input) {
        LanguageStats stats = new LanguageStats();
        stats.addLanguage(new Language(FileInput.readFile("English.txt"), "English"));
        stats.addLanguage(new Language(FileInput.readFile("Deutch.txt"), "German"));
        stats.addLanguage(new Language(FileInput.readFile("Suomi.txt"), "Finnish"));
        stats.addLanguage(new Language(FileInput.readFile("Svenska.txt"), "Swedish"));
        stats.addLanguage(new Language(FileInput.readFile("Eesti.txt"), "Estonian"));
        stats.addLanguage(new Language(FileInput.readFile("Francais.txt"), "French"));
        stats.addLanguage(new Language(FileInput.readFile("Norsk.txt"), "Norwegian"));
        stats.addLanguage(new Language(FileInput.readFile("Italiano.txt"), "Italian"));
        stats.guessLanguage(new Language(input, "unknown"));
    }
}
