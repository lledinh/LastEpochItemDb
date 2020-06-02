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
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        GameDataExtractor gameDataExtractor = new GameDataExtractor();
        gameDataExtractor.extract();

        List<Item> items = gameDataExtractor.getItems();

        Map<String, Integer> occ = new HashMap<>();
        Map<String, Integer> occ2 = new HashMap<>();

        Set<String> listItemType = new HashSet<>();
        for (Item item: items) {
            listItemType.add(item.getBaseTypeName());
        }

        System.out.println("---------- listItemType ----------");
        System.out.println("listItemType size = " + listItemType.size());
        for (String itemType: listItemType) {
            System.out.println(itemType);
        }
        System.out.println("----------");

        for (String itemType: listItemType) {
            occ.put(itemType, 0);
            occ2.put(itemType, 0);
        }

        System.out.println("---------- subItems ----------");
        System.out.println("items size = " + items.size());
        for (Item item: items) {
            System.out.println(item.getName() + " ---- " + item.getDisplayName());
        }
        System.out.println("----------");

        for (Item item: items) {
            Integer nb = occ.get(item.getBaseTypeName());
            occ.put(item.getBaseTypeName(), nb + 1);

            Integer nb2 = occ2.get(item.getBaseTypeName());
            if (item.getCannotDrop() == 0) {
                occ2.put(item.getBaseTypeName(), nb2 + 1);
            }
        }

        System.out.println("---------- occ ----------");
        for (Map.Entry<String, Integer> entry: occ.entrySet()) {
            System.out.println(entry.getKey() +" => " + entry.getValue());
        }
        System.out.println("----------");

        System.out.println("---------- occ cannotdrop----------");
        for (Map.Entry<String, Integer> entry: occ2.entrySet()) {
            System.out.println(entry.getKey() +" => " + entry.getValue());
        }
        System.out.println("----------");
    }
}
