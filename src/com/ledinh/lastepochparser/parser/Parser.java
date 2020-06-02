package com.ledinh.lastepochparser.parser;

import com.ledinh.lastepochparser.parser.objects.Item;

import java.util.*;

public class Parser {

    /* Parser for patch 0.7.8 of the game */
    public static class Parser078 implements IParser {
        private static final String TAG_EQUIPPABLE_ITEMS = "EquippableItems";
        private static final String TAG_NON_EQUIPPABLE_ITEMS = "nonEquippableItems";
        private static final String TAG_BASE_TYPE_NAME = "- BaseTypeName";
        private static final String TAG_NAME = "- name";
        private static final String TAG_SUB_ITEMS = "subItems";
        private static final String TAG_UNIQUE_LIST = "uniquesList";
        private static final String TAG_GRID_SIZE = "gridSize";
        private static final String TAG_HIT_SOUND_TYPE = "hitSoundType";
        private static final String TAG_IMPLICITS = "implicits";
        private static final String TAG_CLASS_REQUIREMENT = "classRequirement";

        public List<Item> parseItems(String data) {

            String[] lines = data.split("\n");
            List<String> linesEquippableItems = new ArrayList<>();
            List<String> linesNonEquippableItems = new ArrayList<>();

            int i = 0;
            while (!lines[i].contains(TAG_EQUIPPABLE_ITEMS)) {
                i++;
            }

            int j = i;
            while (!lines[j].contains(TAG_NON_EQUIPPABLE_ITEMS)) {
                if (lines[j].contains(TAG_EQUIPPABLE_ITEMS)) {
                    j++;
                    continue;
                }
                linesEquippableItems.add(lines[j]);
                j++;
            }

            Map<String, Map<String, String>> baseTypeItem = new HashMap<>();
//            Map<String, Map<String, String>> subItems = new HashMap<>();
            List<Item> items = new ArrayList<>();

            Map<String, String> baseTypeItemAttributes = null;
            boolean limitReached = false;

            for (int k = 0; k < linesEquippableItems.size() && !limitReached; k++) {
                String line = linesEquippableItems.get(k);

                if (line.contains(TAG_BASE_TYPE_NAME)) {
                    if (baseTypeItemAttributes != null) {
                        baseTypeItem.put(baseTypeItemAttributes.get(TAG_BASE_TYPE_NAME), baseTypeItemAttributes);
                    }

                    baseTypeItemAttributes = new HashMap<>();
                    String[] attribute = getAttributeValue(line);
                    baseTypeItemAttributes.put(attribute[0], attribute[1]);
                } else if (line.contains(TAG_GRID_SIZE)) {
                    String l = TAG_GRID_SIZE;
                    k++;
                    String lineX = linesEquippableItems.get(k);
                    k++;
                    String lineY = linesEquippableItems.get(k);

                    String x = getAttributeValue(lineX)[1];
                    String y = getAttributeValue(lineY)[1];

                    baseTypeItemAttributes.put(l + "X", x);
                    baseTypeItemAttributes.put(l + "Y", y);
                } else if (line.contains(TAG_SUB_ITEMS)) {
                    String[] attributeSubItem = getAttributeValue(line);

                    boolean parsingSubItems = (attributeSubItem[1] == null);
                    boolean skippingUnique;

                    if (parsingSubItems) {
                        k++;
                        line = linesEquippableItems.get(k);
                    }
                    Map<String, String> subItem = null;
                    while (parsingSubItems) {
                        if (line.contains(TAG_NAME)) {
                            if (subItem != null && baseTypeItemAttributes != null) {
                                Item item = buildItem(baseTypeItemAttributes, subItem);
                                items.add(item);
                            }

                            subItem = new HashMap<>();
                            String[] attribute = getAttributeValue(line);
                            subItem.put(attribute[0], attribute[1]);
                        } else if (line.contains(TAG_UNIQUE_LIST)) {
                            skippingUnique = true;
                            while (skippingUnique) {
                                line = linesEquippableItems.get(k + 1);
                                skippingUnique = !line.contains(TAG_HIT_SOUND_TYPE);
                                k++;
                            }

                            String[] attribute = getAttributeValue(line);
                            if (subItem != null) {
                                subItem.put(attribute[0], attribute[1]);
                            }
                        } else if (line.contains(TAG_IMPLICITS)) {
                            boolean skippingImplicits = true;
                            while (skippingImplicits) {
                                line = linesEquippableItems.get(k + 1);
                                skippingImplicits = !line.contains(TAG_CLASS_REQUIREMENT);
                                k++;
                            }
                            String[] attribute = getAttributeValue(line);
                            if (subItem != null) {
                                subItem.put(attribute[0], attribute[1]);
                            }
                        } else if (subItem != null) {
                            String[] attribute = getAttributeValue(line);
                            subItem.put(attribute[0], attribute[1]);
                        }

                        k++;
                        limitReached = (k >= linesEquippableItems.size());
                        String nextLine = null;
                        if (!limitReached) {
                            line = linesEquippableItems.get(k);

                            if (k + 1 < linesEquippableItems.size()) {
                                nextLine = linesEquippableItems.get(k + 1);
                            }
                        }

                        // We stop when we have no more line to parse or when we reached the ending line of subItems attribute.
                        parsingSubItems = !limitReached && !(nextLine != null && nextLine.contains(TAG_BASE_TYPE_NAME));

                        // We parsed each line of subItems or we have no more line to parse.
                        // Add the current item to the item list
                        if (!parsingSubItems) {
                            if (baseTypeItemAttributes != null) {
                                Item item = buildItem(baseTypeItemAttributes, subItem);
                                items.add(item);
                            }
                        }
                        // We have no more line to parse.
                        // Add the base item to the base item list
                        if (limitReached) {
                            if (baseTypeItemAttributes != null && !baseTypeItem.containsKey(baseTypeItemAttributes.get(TAG_BASE_TYPE_NAME))) {
                                baseTypeItem.put(baseTypeItemAttributes.get(TAG_BASE_TYPE_NAME), baseTypeItemAttributes);
                            }
                        }
                    }
                } else {
                    String[] attribute = getAttributeValue(line);
                    baseTypeItemAttributes.put(attribute[0], attribute[1]);
                }
            }

            return items;
        }

        @Override
        public void parseProperties(String data) {

        }

        @Override
        public void parseAffixes(String data) {

        }

        @Override
        public void parseUniques(String data) {

        }

        private Item buildItem(Map<String, String> baseTypeItemAttributes, Map<String, String> subItem) {
            Item item = new Item();
            item.setBaseTypeName(baseTypeItemAttributes.get(TAG_BASE_TYPE_NAME));
            item.setBaseDisplayName(baseTypeItemAttributes.get("displayName"));
            item.setBaseTypeID(Integer.parseInt(baseTypeItemAttributes.get("baseTypeID")));
            item.setMaximumAffixes(Integer.parseInt(baseTypeItemAttributes.get("maximumAffixes")));
            item.setMaxSockets(Integer.parseInt(baseTypeItemAttributes.get("maxSockets")));
            item.setAffixEffectModifier(Float.parseFloat(baseTypeItemAttributes.get("affixEffectModifier")));
            item.setGridSizeX(Integer.parseInt(baseTypeItemAttributes.get("gridSizeX")));
            item.setGridSizeY(Integer.parseInt(baseTypeItemAttributes.get("gridSizeY")));
            item.setType(Integer.parseInt(baseTypeItemAttributes.get("type")));
            item.setIsWeapon(Integer.parseInt(baseTypeItemAttributes.get("isWeapon")));
            item.setMinimumDropLevel(Integer.parseInt(baseTypeItemAttributes.get("minimumDropLevel")));
            item.setName(subItem.get(TAG_NAME));
            item.setDisplayName(subItem.get("displayName"));
            item.setSubTypeID(Integer.parseInt(subItem.get("subTypeID")));
            item.setLevelRequirement(Integer.parseInt(subItem.get("levelRequirement")));
            item.setCannotDrop(Integer.parseInt(subItem.get("cannotDrop")));
            item.setItemTags(Integer.parseInt(subItem.get("itemTags")));
            item.setClassRequirement(Integer.parseInt(subItem.get("classRequirement")));
            item.setSubClassRequirement(Integer.parseInt(subItem.get("subClassRequirement")));
            item.setAttackRate(Float.parseFloat(subItem.get("attackRate")));

            return item;
        }

        private String[] getAttributeValue(String line) {
            String[] attribute = new String[2];
            line = line.trim();
            String[] tokens = line.split(":");
            attribute[0] = tokens[0];
            attribute[1] = tokens.length >= 2 ? tokens[1].trim() : null;

            return attribute;
        }
    }
}
