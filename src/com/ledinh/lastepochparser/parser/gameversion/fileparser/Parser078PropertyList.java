package com.ledinh.lastepochparser.parser.gameversion.fileparser;

import com.ledinh.lastepochparser.parser.ParserUtils;
import com.ledinh.lastepochparser.parser.objects.Item;
import com.ledinh.lastepochparser.parser.objects.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ledinh.lastepochparser.parser.ParserUtils.getAttributeValue;

public class Parser078PropertyList implements ParserPropertyList {
    public static String TAG_PROPERTY_INFO_LIST = "propertyInfoList";
    public static String TAG_ROUND_ADDED_TO_INT = "roundAddedToInt";
    public static String TAG_ALT_TEXT_OVERRIDE = "altTextOverrides";
    public static String TAG_PROPERTY = "property";

    @Override
    public List<Property> parsePropertyList(String data) {
        List<Property> propertyList = new ArrayList<>();

        String[] lines = data.split("\n");

        int i = 0;
        while (!lines[i].contains(TAG_PROPERTY_INFO_LIST)) {
            i++;
        }

        boolean limitReached = false;
        Map<String, String> basePropertyAttributes = null;
        Map<String, String> propertyVariantAttributes = null;
        for (int k = i + 1; k < lines.length && !limitReached; k++) {
            String line = lines[k];
            if (line.contains(TAG_ROUND_ADDED_TO_INT)) {
                basePropertyAttributes = new HashMap<>();
                String[] attribute = getAttributeValue(line);
                basePropertyAttributes.put(attribute[0], attribute[1]);
            }
            else if(line.contains(TAG_ALT_TEXT_OVERRIDE)) {
                if (basePropertyAttributes != null) {
                    Property baseProperty = buildProperty(basePropertyAttributes, null);
                    propertyList.add(baseProperty);
                }

                String[] attributeAltText = getAttributeValue(line);
                if (attributeAltText[1] == null) {
                    boolean parsePropertyVariants = true;

                    while(parsePropertyVariants && !limitReached) {
                        k++;

                        if (k >= lines.length) {
                            limitReached = true;
                        }
                        else {
                            line = lines[k];

                            if (line.contains(TAG_ROUND_ADDED_TO_INT)) {
                                parsePropertyVariants = false;
                            }
                            else if (line.contains(TAG_PROPERTY)) {
                                if (propertyVariantAttributes != null && basePropertyAttributes != null) {
                                    Property propertyVariant = buildProperty(basePropertyAttributes, propertyVariantAttributes);
                                    propertyList.add(propertyVariant);
                                }

                                propertyVariantAttributes = new HashMap<>();
                            }
                            else if (propertyVariantAttributes != null) {
                                String[] attribute = getAttributeValue(line);
                                propertyVariantAttributes.put(attribute[0], attribute[1]);
                            }
                        }

                        if (!parsePropertyVariants|| limitReached) {
                            if (propertyVariantAttributes != null && basePropertyAttributes != null) {
                                Property propertyVariant = buildProperty(basePropertyAttributes, propertyVariantAttributes);
                                propertyList.add(propertyVariant);
                            }
                        }
                    }
                }
            }
            else {
                if (basePropertyAttributes != null) {
                    String[] attribute = getAttributeValue(line);
                    basePropertyAttributes.put(attribute[0], attribute[1]);
                }
            }
        }

        return propertyList;
    }

    private Property buildProperty(Map<String, String> basePropertyAttributes, Map<String, String> propertyVariantAttributes) {
        Property property = new Property();

        property.setRoundAddedToInt(Integer.parseInt(basePropertyAttributes.get("- roundAddedToInt")));
        property.setDisplayAddedAsPercentage(Integer.parseInt(basePropertyAttributes.get("displayAddedAsPercentage")));
        property.setDisplayAddedAsTenthOfValue(Integer.parseInt(basePropertyAttributes.get("displayAddedAsTenthOfValue")));
        property.setDisplayAsPercentageOf(Integer.parseInt(basePropertyAttributes.get("displayAsPercentageOf")));
        property.setDontDisplayPlus(Integer.parseInt(basePropertyAttributes.get("dontDisplayPlus")));
        property.setLessIsBetter(Integer.parseInt(basePropertyAttributes.get("lessIsBetter")));
        property.setPropertyName(basePropertyAttributes.get("propertyName"));
        property.setProperty(Integer.parseInt(basePropertyAttributes.get("property")));
        property.setDefaultAltText(basePropertyAttributes.get("defaultAltText"));

        if (propertyVariantAttributes != null) {
            property.setModType(Integer.parseInt(propertyVariantAttributes.get("modType")));
            property.setSpecialTag(Integer.parseInt(propertyVariantAttributes.get("specialTag")));
            property.setTags(Integer.parseInt(propertyVariantAttributes.get("tags")));
            property.setOverrideAltText(propertyVariantAttributes.get("overrideAltText"));
        }
        else {
            property.setModType(0);
            property.setSpecialTag(0);
            property.setTags(0);
            property.setOverrideAltText(null);
        }

        return property;
    }
}
