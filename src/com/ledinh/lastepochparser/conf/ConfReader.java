package com.ledinh.lastepochparser.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ConfReader {
    public static Map<String, String> read(String filePath) throws IOException {
        Map<String, String> map = new HashMap<>();
        Stream<String> stream = Files.lines(Paths.get(filePath));

        stream.forEach(s -> {
            String[] tokens = s.split("=");
            map.put(tokens[0], tokens[1]);
        });

        return map;
    }
}
