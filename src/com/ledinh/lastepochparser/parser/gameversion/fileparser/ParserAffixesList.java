package com.ledinh.lastepochparser.parser.gameversion.fileparser;

import com.ledinh.lastepochparser.parser.objects.Affix;

import java.util.List;

public interface ParserAffixesList {
    List<Affix> parseAffixes(String data);
}
