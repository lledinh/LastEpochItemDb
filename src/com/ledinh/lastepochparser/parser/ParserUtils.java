package com.ledinh.lastepochparser.parser;

public class ParserUtils {

    public static String[] getAttributeValue(String line) {
        String[] attribute = new String[2];
        line = line.trim();
        String[] tokens = line.split(":");
        attribute[0] = tokens[0];
        attribute[1] = tokens.length >= 2 ? tokens[1].trim() : null;

        return attribute;
    }
}
