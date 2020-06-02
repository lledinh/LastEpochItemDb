package com.ledinh.lastepochparser.parser.gameversion.fileparser;

import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.List;

public interface ParserItemsList {
    List<Item> parseItems(String data);
}
