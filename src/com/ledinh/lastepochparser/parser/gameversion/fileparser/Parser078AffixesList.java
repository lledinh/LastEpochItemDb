package com.ledinh.lastepochparser.parser.gameversion.fileparser;

import com.ledinh.lastepochparser.parser.objects.Affix;
import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ledinh.lastepochparser.parser.ParserUtils.getAttributeValue;

public class Parser078AffixesList implements ParserAffixesList {
    @Override
    public List<Affix> parseAffixes(String data) {
        List<Affix> affixes = new ArrayList<>();

        String[] lines = data.split("\n");
        List<String> linesSingleAffixes = new ArrayList<>();

        int i = 0;
        while (!lines[i].contains("singleAffixes")) {
            i++;
        }
        i++;

        int j = i;
        while (!lines[j].contains("multiAffixes")) {
            linesSingleAffixes.add(lines[j]);
            j++;
        }

        Map<String, String> affixAttributes = null;
        for (int k = 0; k < linesSingleAffixes.size(); k++) {
            String line = linesSingleAffixes.get(k);

            if (line.contains("- affixName")) {
                if (affixAttributes != null) {

                }

                affixAttributes = new HashMap<>();
            }
            else if (line.contains("specificRerollChances")) {

            }
            else if (line.contains("tiers")) {

            }
            else {
                if (affixAttributes != null) {
                    String[] attribute = getAttributeValue(line);
                    affixAttributes.put(attribute[0], attribute[1]);
                }
            }
        }

        return affixes;
    }
}
