package com.ledinh.lastepochparser.parser.gameversion.fileparser;

import com.ledinh.lastepochparser.parser.objects.Affix;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

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
        List<String> linesMultiAffixes = new ArrayList<>();
        List<List<String>> linesAffixesGroup = new ArrayList<>();
        linesAffixesGroup.add(linesSingleAffixes);
        linesAffixesGroup.add(linesMultiAffixes);

        int i = 0;
        while (!lines[i].contains("singleAffixes")) {
            i++;
        }
        i++;

        while (!lines[i].contains("multiAffixes")) {
            linesSingleAffixes.add(lines[i]);
            i++;
        }
        i++;

        while (!lines[i].contains("filteredSingleAffixes")) {
            linesMultiAffixes.add(lines[i]);
            i++;
        }

        for (List<String> linesAffix: linesAffixesGroup) {
            Map<String, String> affixAttributes = null;
            List<Affix.Tier> affixTiers = null;

            for (int k = 0; k < linesAffix.size(); k++) {
                String line = linesAffix.get(k);

                if (line.contains("- affixName")) {
                    if (affixAttributes != null) {
                        Affix affix = buildAffix(affixAttributes, affixTiers);
                        affixes.add(affix);
                    }

                    affixAttributes = new HashMap<>();
                    affixTiers = new ArrayList<>();
                    String[] attribute = getAttributeValue(line);
                    affixAttributes.put(attribute[0], attribute[1]);
                }
                else if (line.contains("specificRerollChances")) {
                    boolean skippingSpecificRerollChances = true;

                    while(skippingSpecificRerollChances) {
                        skippingSpecificRerollChances = !line.contains("tiers");
                        k++;
                        line = linesAffix.get(k);
                    }
                }
                else if (line.contains("tiers")) {
                    boolean parseTiers = true;

                    while(parseTiers) {
                        if (line.contains("requiredLevel")) {
                            Affix.Tier tier = new Affix.Tier();
                            k++;
                            line = linesAffix.get(k);
                            String[] attribute = getAttributeValue(line);
                            tier.setMinRoll(Float.parseFloat(attribute[1]));

                            k++;
                            line = linesAffix.get(k);
                            attribute = getAttributeValue(line);
                            tier.setMaxRoll(Float.parseFloat(attribute[1]));

                            k++;
                            line = linesAffix.get(k);
                            attribute = getAttributeValue(line);

                            if (attribute[1] == null) {
                                k++;
                                line = linesAffix.get(k);
                                attribute = getAttributeValue(line);
                                tier.setExtraRollMinRoll(Float.parseFloat(attribute[1]));

                                k++;
                                line = linesAffix.get(k);
                                attribute = getAttributeValue(line);
                                tier.setExtraRollMaxRoll(Float.parseFloat(attribute[1]));
                            }

                            if (affixTiers != null) {
                                affixTiers.add(tier);
                            }
                        }

                        parseTiers = !line.contains("property");
                        k++;
                        line = linesAffix.get(k);
                    }
                }
                else {
                    if (affixAttributes != null) {
                        String[] attribute = getAttributeValue(line);
                        affixAttributes.put(attribute[0], attribute[1]);
                    }
                }
            }
            if (affixTiers != null) {
                Affix affix = buildAffix(affixAttributes, affixTiers);
                affixes.add(affix);
            }

        }

        return affixes;
    }

    private Affix buildAffix(Map<String, String> affixAttributes, List<Affix.Tier> affixTiers) {
        Affix affix = new Affix();

        for (Affix.Tier tier: affixTiers) {
            affix.addTier(tier);
        }

        affix.setAffixName(affixAttributes.get("- affixName"));
        affix.setAffixDisplayName(affixAttributes.get("affixDisplayName"));
        affix.setAffixTitle(affixAttributes.get("affixTitle"));
        affix.setAffixId(Integer.parseInt(affixAttributes.get("affixId")));
        affix.setLevelRequirement(Integer.parseInt(affixAttributes.get("levelRequirement")));
        affix.setRollsOn(Integer.parseInt(affixAttributes.get("rollsOn")));
        affix.setClassSpecificity(Integer.parseInt(affixAttributes.get("classSpecificity")));
        affix.setType(Integer.parseInt(affixAttributes.get("type")));
        affix.setStandardAffixEffectModifier(Float.parseFloat(affixAttributes.get("standardAffixEffectModifier")));
        affix.setRerollChance(Float.parseFloat(affixAttributes.get("rerollChance")));
        affix.setWeaponEffect(Integer.parseInt(affixAttributes.get("weaponEffect")));
        affix.setGroup(Integer.parseInt(affixAttributes.get("group")));
        affix.setShardHueShift(Float.parseFloat(affixAttributes.get("shardHueShift")));
        affix.setShardSaturationModifier(Float.parseFloat(affixAttributes.get("shardSaturationModifier")));
        affix.setCanRollOn(affixAttributes.get("canRollOn"));
        affix.setProperty(Integer.parseInt(affixAttributes.get("property")));
        affix.setSpecialTag(Integer.parseInt(affixAttributes.get("specialTag")));
        affix.setTags(Integer.parseInt(affixAttributes.get("tags")));
        affix.setModifierType(Integer.parseInt(affixAttributes.get("modifierType")));
        affix.setSetProperty(Integer.parseInt(affixAttributes.get("setProperty")));

        return affix;
    }
}
