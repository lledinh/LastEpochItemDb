package com.ledinh.lastepochparser.parser.gameversion;

import com.ledinh.lastepochparser.parser.IParser;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.ParserItemsList;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.Parser078ItemsList;
import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.List;

/* Parser for patch 0.7.8 of the game */
public class Parser078 implements IParser {
    ParserItemsList parserItemsList;

    public Parser078() {
        parserItemsList = new Parser078ItemsList();
    }

    @Override
    public List<Item> parseItems(String data) {
        return parserItemsList.parseItems(data);
    }

    @Override
    public void parseProperties(String data) {

    }

    @Override
    public void parseAffixes(String data) {

    }

    @Override
    public void parseUniques(String data) {

    }

}
