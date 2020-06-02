package com.ledinh.lastepochparser.parser;

public class Property {
    private String propertyName;
    private String defaultAltText;
    private int roundAddedToInt;
    private int displayAddedAsPercentage;
    private int displayAddedAsTenthOfValue;
    private int displayAsPercentageOf;
    private int displayAsAddedTo;
    private int dontDisplayPlus;
    private int lessIsBetter;
    private int property;
    private int specialTag;
    private int tags;
    private int modType;

    public int getRoundAddedToInt() {
        return roundAddedToInt;
    }

    public void setRoundAddedToInt(int roundAddedToInt) {
        this.roundAddedToInt = roundAddedToInt;
    }

    public int getDisplayAddedAsPercentage() {
        return displayAddedAsPercentage;
    }

    public void setDisplayAddedAsPercentage(int displayAddedAsPercentage) {
        this.displayAddedAsPercentage = displayAddedAsPercentage;
    }

    public int getDisplayAddedAsTenthOfValue() {
        return displayAddedAsTenthOfValue;
    }

    public void setDisplayAddedAsTenthOfValue(int displayAddedAsTenthOfValue) {
        this.displayAddedAsTenthOfValue = displayAddedAsTenthOfValue;
    }

    public int getDisplayAsPercentageOf() {
        return displayAsPercentageOf;
    }

    public void setDisplayAsPercentageOf(int displayAsPercentageOf) {
        this.displayAsPercentageOf = displayAsPercentageOf;
    }

    public int getDisplayAsAddedTo() {
        return displayAsAddedTo;
    }

    public void setDisplayAsAddedTo(int displayAsAddedTo) {
        this.displayAsAddedTo = displayAsAddedTo;
    }

    public int getDontDisplayPlus() {
        return dontDisplayPlus;
    }

    public void setDontDisplayPlus(int dontDisplayPlus) {
        this.dontDisplayPlus = dontDisplayPlus;
    }

    public int getLessIsBetter() {
        return lessIsBetter;
    }

    public void setLessIsBetter(int lessIsBetter) {
        this.lessIsBetter = lessIsBetter;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getSpecialTag() {
        return specialTag;
    }

    public void setSpecialTag(int specialTag) {
        this.specialTag = specialTag;
    }

    public int getTags() {
        return tags;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }

    public int getModType() {
        return modType;
    }

    public void setModType(int modType) {
        this.modType = modType;
    }

    public String getDefaultAltText() {
        return defaultAltText;
    }

    public void setDefaultAltText(String defaultAltText) {
        this.defaultAltText = defaultAltText;
    }
}
