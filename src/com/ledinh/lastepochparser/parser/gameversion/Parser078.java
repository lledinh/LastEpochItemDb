package com.ledinh.lastepochparser.parser.gameversion;

import com.ledinh.lastepochparser.parser.IParser;
import com.ledinh.lastepochparser.parser.gameversion.fileparser.*;
import com.ledinh.lastepochparser.parser.objects.Affix;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.util.List;

/* Parser for patch 0.7.8 of the game */
public class Parser078 implements IParser {
    ParserItemsList parserItemsList;
    ParserPropertyList parserPropertyList;
    ParserAffixesList parserAffixesList;

    public Parser078() {
        parserItemsList = new Parser078ItemsList();
        parserPropertyList = new Parser078PropertyList();
        parserAffixesList = new Parser078AffixesList();
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
    public List<Affix> parseAffixes(String data) {
        return parserAffixesList.parseAffixes(data);
    }

    @Override
    public List<Item> parseUniques(String data) {
        return null;
    }

}
