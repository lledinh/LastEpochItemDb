package com.ledinh.lastepochparser.conf;

public class AssetsPath {
    private String assetsFolder;
    private String itemsListPath;
    private String uniqueItemsListPath;
    private String propertyListPath;
    private String affixListPath;

    public AssetsPath() {

    }

    public String getAssetsFolder() {
        return assetsFolder;
    }

    public void setAssetsFolder(String assetsFolder) {
        this.assetsFolder = assetsFolder;
    }

    public String getItemsListPath() {
        return assetsFolder + itemsListPath;
    }

    public void setItemsListPath(String itemsListPath) {
        this.itemsListPath = itemsListPath;
    }

    public String getUniqueItemsListPath() {
        return assetsFolder + uniqueItemsListPath;
    }

    public void setUniqueItemsListPath(String uniqueItemsListPath) {
        this.uniqueItemsListPath = uniqueItemsListPath;
    }

    public String getPropertyListPath() {
        return assetsFolder + propertyListPath;
    }

    public void setPropertyListPath(String propertyListPath) {
        this.propertyListPath = propertyListPath;
    }

    public String getAffixListPath() {
        return assetsFolder + affixListPath;
    }

    public void setAffixListPath(String affixListPath) {
        this.affixListPath = affixListPath;
    }

    @Override
    public String toString() {
        return "AssetsPath{" +
                "assetsFolder='" + assetsFolder + '\'' +
                ", itemsListPath='" + itemsListPath + '\'' +
                ", uniqueItemsListPath='" + uniqueItemsListPath + '\'' +
                ", propertyListPath='" + propertyListPath + '\'' +
                ", affixListPath='" + affixListPath + '\'' +
                '}';
    }
}
