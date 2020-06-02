package com.ledinh.lastepochparser.parser;

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
        private static final String TAG_CLASS_REQUIREMENT= "classRequirement";

        public void parseItems(String data) {

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
            Map<String, Map<String, String>> subItems = new HashMap<>();

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
                }
                else if (line.contains(TAG_GRID_SIZE)) {
                    String l = line.trim();
                    k++;
                    String lineX = linesEquippableItems.get(k);
                    k++;
                    String lineY = linesEquippableItems.get(k);

                    String x = getAttributeValue(lineX)[1];
                    String y = getAttributeValue(lineY)[1];

                    baseTypeItemAttributes.put(l, x + " " + y);
                } else if (line.contains(TAG_SUB_ITEMS)) {
                    boolean parsingSubItems = true;
                    boolean skippingUnique;
                    Map<String, String> subItem = null;
                    k++;
                    line = linesEquippableItems.get(k);
                    while (parsingSubItems) {
                        if (line.contains(TAG_NAME)) {
                            if (subItem != null) {
                                subItems.put(subItem.get(TAG_NAME), subItem);
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
                        }
                        else if (line.contains(TAG_IMPLICITS)) {
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
                        }
                        else if (subItem != null) {
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

                        parsingSubItems = !limitReached && !(nextLine != null && nextLine.contains(TAG_BASE_TYPE_NAME));

                        if (!parsingSubItems) {
                            if (subItem != null) {
                                subItems.put(subItem.get(TAG_NAME), subItem);
                            }
                        }
                    }
                } else {
                    String[] attribute = getAttributeValue(line);
                    baseTypeItemAttributes.put(attribute[0], attribute[1]);
                }
            }

            System.out.println("---------- baseTypeItem ----------");
            System.out.println("baseTypeItem size = " + baseTypeItem.size());
            for (Map.Entry<String, Map<String, String>> entry : baseTypeItem.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("----------");

            System.out.println("---------- subItems ----------");
            System.out.println("subItems size = " + subItems.size());
            for (Map.Entry<String, Map<String, String>> entry : subItems.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("----------");


            System.out.println("---------- Amulet ----------");
            for (Map.Entry<String, Map<String, String>> entry : subItems.entrySet()) {
                if (entry.getKey().contains("Amulet")) {
                    System.out.println(entry.getKey());
                }
            }
            System.out.println("----------");


            System.out.println("---------- Ring ----------");
            for (Map.Entry<String, Map<String, String>> entry : subItems.entrySet()) {
                if (entry.getKey().contains("Ring")) {
                    System.out.println(entry.getKey());
                }
            }
            System.out.println("----------");

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

        private String[] getAttributeValue(String line) {
            String[] attribute = new String[2];
            line = line.trim();
            String[] tokens = line.split(":");
            attribute[0] = tokens[0];
            attribute[1] = tokens.length >= 2 ? tokens[1] : null;

            return attribute;
        }
    }
}
