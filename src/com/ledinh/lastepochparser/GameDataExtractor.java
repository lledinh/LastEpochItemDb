package com.ledinh.lastepochparser;

import com.ledinh.lastepochparser.conf.AssetsConfReader;
import com.ledinh.lastepochparser.conf.AssetsPath;
import com.ledinh.lastepochparser.parser.IParser;
import com.ledinh.lastepochparser.parser.Parser;
import com.ledinh.lastepochparser.parser.objects.Item;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GameDataExtractor {
    private List<Item> items;

    public void extract() throws IOException {
        AssetsPath assetsPath = AssetsConfReader.read();
        String s = readFile(assetsPath.getItemsListPath(), Charset.defaultCharset());

        IParser parser = new Parser.Parser078();
        items = parser.parseItems(s);
    }

    public String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public List<Item> getItems() {
        return items;
    }
}
