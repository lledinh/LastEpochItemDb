package com.ledinh.lastepochparser.parser.objects;

import java.util.ArrayList;
import java.util.List;

/*
- BaseTypeName: Helmets
    displayName: Helmet
    baseTypeID: 0
    maximumAffixes: 4
    maxSockets: 0
    affixEffectModifier: 0
    gridSize:
      x: 2
      y: 2
    type: 0
    isWeapon: 0
    goldCostModifier: 0
    minimumDropLevel: 0
    rerollChance: 0
    weaponSwingSound: 0
    subTypeClassSpecificity: 1
    subItems:
    - name: Mercenary Helmet
      displayName: Refuge Helmet
      subTypeID: 0
      levelRequirement: 0
      cannotDrop: 1
      itemTags: 0
      implicits:
      - property: 10
        specialTag: 0
        tags: 0
        type: 0
        implicitValue: 14
        implicitMaxValue: 14
      classRequirement: 0
      subClassRequirement: 0
      uniquesList: []
      hitSoundType: 0
      uiItemSoundType: 0
      addedWeaponRange: 0
      attackRate: 1
      IMSetTier: 0

     - name: Leather Helmet
      displayName: Jewelled Circlet
      subTypeID: 1
      levelRequirement: 7
      cannotDrop: 1
      itemTags: 0
      implicits:
      - property: 10
        specialTag: 0
        tags: 0
        type: 0
        implicitValue: 5
        implicitMaxValue: 5
      - property: 8
        specialTag: 0
        tags: 0
        type: 0
        implicitValue: 10
        implicitMaxValue: 35
      - property: 0
        specialTag: 0
        tags: 256
        type: 1
        implicitValue: 0.05
        implicitMaxValue: 0.25
      classRequirement: 0
      subClassRequirement: 0
      uniquesList:
      - name: Calamity
        displayName:
        overrideLevelRequirement: 0
        levelRequirement: 0
        uniqueId: 1
        setId: 0
        rerollChance: 0
        noWorldDrops: 0
        isFractureUnique: 0
        isSetUnique: 0
        isLegendary: 0
        basicMods:
        - value: 1.5
          property: 1
          specialTag: 1
          tags: 8
          type: 0
          hideStatMod: 1
        - value: 0.4
          property: 0
          specialTag: 0
          tags: 8
          type: 1
          hideStatMod: 0
        descriptionParts:
        - uniqueDescription: 150% chance to ignite on hit with fire skills
          altText:
          setPart: 0
          setItemRequirement: 0
        - uniqueDescription: When you kill an enemy with a fire skill you take 4 fire damage over the next 2 seconds
          altText:
          setPart: 0
          setItemRequirement: 0
        loreText: Set the world ablaze and burn along with it
      - name: Fractured Crown
        displayName:
        overrideLevelRequirement: 0
        levelRequirement: 0
        uniqueId: 2
        setId: 0
        rerollChance: 0
        noWorldDrops: 1
        isFractureUnique: 1
        isSetUnique: 0
        isLegendary: 0
        basicMods:
        - value: -0.1
          property: 6
          specialTag: 0
          tags: 0
          type: 2
          hideStatMod: 0
        - value: 1
          property: 5
          specialTag: 0
          tags: 256
          type: 0
          hideStatMod: 0
        - value: 0.2
          property: 69
          specialTag: 0
          tags: 256
          type: 0
          hideStatMod: 0
        descriptionParts:
        - uniqueDescription: You have no ward
          altText:
          setPart: 0
          setItemRequirement: 0
        loreText: Few goals have cost more lives than the quest for power
      hitSoundType: 0
      uiItemSoundType: 1
      addedWeaponRange: 0
      attackRate: 1
      IMSetTier: 1

 */
public class Item {
    public static class Property {
        private int property;
        private int specialTag;
        private int tags;
        private int type;
        private float implicitValue;
        private float implicitMaxValue;

        public Property() {

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public float getImplicitValue() {
            return implicitValue;
        }

        public void setImplicitValue(float implicitValue) {
            this.implicitValue = implicitValue;
        }

        public float getImplicitMaxValue() {
            return implicitMaxValue;
        }

        public void setImplicitMaxValue(float implicitMaxValue) {
            this.implicitMaxValue = implicitMaxValue;
        }
    }

    private String baseTypeName;
    private String baseDisplayName;
    private int baseTypeID;
    private int maximumAffixes;
    private int maxSockets;
    private float affixEffectModifier;
    private int gridSizeX;
    private int gridSizeY;
    private int type;
    private int isWeapon;
    private int minimumDropLevel;
    private String name;
    private String displayName;
    private int subTypeID;
    private int levelRequirement;
    private int cannotDrop;
    private int itemTags;
    private List<Property> itemProperties;
    private int classRequirement;
    private int subClassRequirement;
    private float attackRate;

    public Item() {
        itemProperties = new ArrayList<>();
    }

    public List<Property> getItemProperties() {
        return itemProperties;
    }

    public float getAffixEffectModifier() {
        return affixEffectModifier;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAffixEffectModifier(float affixEffectModifier) {
        this.affixEffectModifier = affixEffectModifier;
    }

    public void addAttribute(Property itemProperty) {
        itemProperties.add(itemProperty);
    }

    public String getBaseTypeName() {
        return baseTypeName;
    }

    public void setBaseTypeName(String baseTypeName) {
        this.baseTypeName = baseTypeName;
    }

    public String getBaseDisplayName() {
        return baseDisplayName;
    }

    public void setBaseDisplayName(String baseDisplayName) {
        this.baseDisplayName = baseDisplayName;
    }

    public int getBaseTypeID() {
        return baseTypeID;
    }

    public void setBaseTypeID(int baseTypeID) {
        this.baseTypeID = baseTypeID;
    }

    public int getMaximumAffixes() {
        return maximumAffixes;
    }

    public void setMaximumAffixes(int maximumAffixes) {
        this.maximumAffixes = maximumAffixes;
    }

    public int getMaxSockets() {
        return maxSockets;
    }

    public void setMaxSockets(int maxSockets) {
        this.maxSockets = maxSockets;
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public void setGridSizeX(int gridSizeX) {
        this.gridSizeX = gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public void setGridSizeY(int gridSizeY) {
        this.gridSizeY = gridSizeY;
    }

    public int getIsWeapon() {
        return isWeapon;
    }

    public void setIsWeapon(int isWeapon) {
        this.isWeapon = isWeapon;
    }

    public int getMinimumDropLevel() {
        return minimumDropLevel;
    }

    public void setMinimumDropLevel(int minimumDropLevel) {
        this.minimumDropLevel = minimumDropLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSubTypeID() {
        return subTypeID;
    }

    public void setSubTypeID(int subTypeID) {
        this.subTypeID = subTypeID;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public int getCannotDrop() {
        return cannotDrop;
    }

    public void setCannotDrop(int cannotDrop) {
        this.cannotDrop = cannotDrop;
    }

    public int getItemTags() {
        return itemTags;
    }

    public void setItemTags(int itemTags) {
        this.itemTags = itemTags;
    }

    public int getClassRequirement() {
        return classRequirement;
    }

    public void setClassRequirement(int classRequirement) {
        this.classRequirement = classRequirement;
    }

    public int getSubClassRequirement() {
        return subClassRequirement;
    }

    public void setSubClassRequirement(int subClassRequirement) {
        this.subClassRequirement = subClassRequirement;
    }

    public float getAttackRate() {
        return attackRate;
    }

    public void setAttackRate(float attackRate) {
        this.attackRate = attackRate;
    }
}
