package com.ledinh.lastepochparser.parser;

public interface IParser {
    public void parseItems(String data);
    public void parseProperties(String data);
    public void parseAffixes(String data);
    public void parseUniques(String data);
}
