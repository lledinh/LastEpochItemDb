package com.ledinh.lastepochparser.parser;

import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.List;

public interface IParser {
    public List<Item> parseItems(String data);
    public void parseProperties(String data);
    public void parseAffixes(String data);
    public void parseUniques(String data);
}
