package com.ledinh.lastepochparser;

import com.ledinh.lastepochparser.conf.AssetsConfReader;
import com.ledinh.lastepochparser.conf.AssetsPath;
import com.ledinh.lastepochparser.parser.Parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) throws IOException {
        AssetsPath assetsPath = AssetsConfReader.read();
        System.out.println(assetsPath.toString());

        String s = readFile(assetsPath.getItemsListPath(), Charset.defaultCharset());

        Parser.IParser parser = new Parser.Parser078();

        parser.parseItems(s);
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
