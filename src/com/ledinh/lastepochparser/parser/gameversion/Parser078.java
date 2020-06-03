package com.ledinh.lastepochparser.parser.gameversion;

import com.ledinh.lastepochparser.parser.IParser;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.Parser078PropertyList;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.ParserItemsList;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.Parser078ItemsList;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.ParserPropertyList;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.util.List;

/* Parser for patch 0.7.8 of the game */
public class Parser078 implements IParser {
    ParserItemsList parserItemsList;
    ParserPropertyList parserPropertyList;

    public Parser078() {
        parserItemsList = new Parser078ItemsList();
        parserPropertyList = new Parser078PropertyList();
    }

    @Override
    public List<Item> parseItems(String data) {
        return parserItemsList.parseItems(data);
    }

    @Override
    public List<Property> parseProperties(String data) {
        return parserPropertyList.parsePropertyList(data);
    }

    @Override
    public void parseAffixes(String data) {

    }

    @Override
    public void parseUniques(String data) {

    }

}
