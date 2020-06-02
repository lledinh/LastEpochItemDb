package com.ledinh.lastepochparser.parser;

import com.ledinh.lastepochparser.GameVersion;
import com.ledinh.lastepochparser.parser.gameversion.Parser078;
import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.*;

public class AvailableParser {
    public static Map<GameVersion, IParser> parserList;

    static {
        parserList = new HashMap<>();
        IParser parser078 = new Parser078();
        parserList.put(GameVersion.v0_7_8, parser078);
    }

    public static IParser get(GameVersion gameVersion) {
        return parserList.get(gameVersion);
    }
}
