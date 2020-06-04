package com.ledinh.lastepochparser;

import com.ledinh.lastepochparser.conf.AssetsConfReader;
import com.ledinh.lastepochparser.conf.AssetsPath;
import com.ledinh.lastepochparser.parser.AvailableParser;
import com.ledinh.lastepochparser.parser.IParser;
import com.ledinh.lastepochparser.parser.objects.Affix;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameDataExtractor {
    private List<Item> items;
    private List<Property> properties;
    private List<Affix> affixes;

    public void extract() throws IOException {
        AssetsPath assetsPath = AssetsConfReader.read();
        String s = readFile(assetsPath.getItemsListPath(), Charset.defaultCharset());

        IParser parser = AvailableParser.get(GameVersion.v0_7_8);
        items = parser.parseItems(s);

        s = readFile(assetsPath.getPropertyListPath(), Charset.defaultCharset());
        properties = parser.parseProperties(s);

        s = readFile(assetsPath.getAffixListPath(), Charset.defaultCharset());
        affixes = parser.parseAffixes(s);
    }

    public String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Affix> getAffixes() {
        return affixes;
    }
}
