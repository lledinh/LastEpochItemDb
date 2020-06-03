package com.ledinh.lastepochparser.parser;

import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.util.List;

public interface IParser {
    List<Item> parseItems(String data);
    List<Property> parseProperties(String data);
    void parseAffixes(String data);
    void parseUniques(String data);
}
