package com.ledinh.lastepochparser.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AssetsConfReader {

    public static AssetsPath read() throws IOException {
        String confFile = "conf/assets.conf";
        Map<String, String> map = ConfReader.read(confFile);

        AssetsPath assetsPath = new AssetsPath();

        assetsPath.setAssetsFolder(map.get("ASSETS_FOLDER"));
        assetsPath.setItemsListPath(map.get("ITEMS_LIST"));
        assetsPath.setUniqueItemsListPath(map.get("UNIQUE_ITEMS_LIST"));
        assetsPath.setPropertyListPath(map.get("PROPERTY_LIST"));
        assetsPath.setAffixListPath(map.get("AFFIX_LIST"));

        return assetsPath;
    }
}
