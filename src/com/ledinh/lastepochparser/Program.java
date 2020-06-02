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

public class Program {
    public static void main(String[] args) throws IOException {
        GameDataExtractor gameDataExtractor = new GameDataExtractor();
        gameDataExtractor.extract();

        List<Item> items = gameDataExtractor.getItems();
    }
}
