package com.ledinh.lastepochparser.parser.objects;


import java.util.ArrayList;
import java.util.List;

public class Affix {
    public static class Tier {
        private int requiredLevel;
        private float minRoll;
        private float maxRoll;
        private float extraRollMinRoll;
        private float extraRollMaxRoll;

        public Tier() {

        }

        public int getRequiredLevel() {
            return requiredLevel;
        }

        public void setRequiredLevel(int requiredLevel) {
            this.requiredLevel = requiredLevel;
        }

        public float getMinRoll() {
            return minRoll;
        }

        public void setMinRoll(float minRoll) {
            this.minRoll = minRoll;
        }

        public float getMaxRoll() {
            return maxRoll;
        }

        public void setMaxRoll(float maxRoll) {
            this.maxRoll = maxRoll;
        }

        public float getExtraRollMinRoll() {
            return extraRollMinRoll;
        }

        public void setExtraRollMinRoll(float extraRollMinRoll) {
            this.extraRollMinRoll = extraRollMinRoll;
        }

        public float getExtraRollMaxRoll() {
            return extraRollMaxRoll;
        }

        public void setExtraRollMaxRoll(float extraRollMaxRoll) {
            this.extraRollMaxRoll = extraRollMaxRoll;
        }
    }

    private String affixName;
    private String affixDisplayName;
    private String affixTitle;
    private int affixId;
    private int levelRequirement;
    private int rollsOn;
    private int classSpecificity;
    private int type;
    private float standardAffixEffectModifier;
    private float rerollChance;
    private int weaponEffect;
    private int group;
    private float shardHueShift;
    private float shardSaturationModifier;
    private String canRollOn;
    private List<Tier> tiers;
    private int property;
    private int specialTag;
    private int tags;
    private int modifierType;
    private int setProperty;

    public Affix() {
        tiers = new ArrayList<>();
    }

    public String getAffixName() {
        return affixName;
    }

    public void setAffixName(String affixName) {
        this.affixName = affixName;
    }

    public String getAffixDisplayName() {
        return affixDisplayName;
    }

    public void setAffixDisplayName(String affixDisplayName) {
        this.affixDisplayName = affixDisplayName;
    }

    public String getAffixTitle() {
        return affixTitle;
    }

    public void setAffixTitle(String affixTitle) {
        this.affixTitle = affixTitle;
    }

    public int getAffixId() {
        return affixId;
    }

    public void setAffixId(int affixId) {
        this.affixId = affixId;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public int getRollsOn() {
        return rollsOn;
    }

    public void setRollsOn(int rollsOn) {
        this.rollsOn = rollsOn;
    }

    public int getClassSpecificity() {
        return classSpecificity;
    }

    public void setClassSpecificity(int classSpecificity) {
        this.classSpecificity = classSpecificity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getStandardAffixEffectModifier() {
        return standardAffixEffectModifier;
    }

    public void setStandardAffixEffectModifier(float standardAffixEffectModifier) {
        this.standardAffixEffectModifier = standardAffixEffectModifier;
    }

    public float getRerollChance() {
        return rerollChance;
    }

    public void setRerollChance(float rerollChance) {
        this.rerollChance = rerollChance;
    }

    public int getWeaponEffect() {
        return weaponEffect;
    }

    public void setWeaponEffect(int weaponEffect) {
        this.weaponEffect = weaponEffect;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public float getShardHueShift() {
        return shardHueShift;
    }

    public void setShardHueShift(float shardHueShift) {
        this.shardHueShift = shardHueShift;
    }

    public float getShardSaturationModifier() {
        return shardSaturationModifier;
    }

    public void setShardSaturationModifier(float shardSaturationModifier) {
        this.shardSaturationModifier = shardSaturationModifier;
    }

    public String getCanRollOn() {
        return canRollOn;
    }

    public void setCanRollOn(String canRollOn) {
        this.canRollOn = canRollOn;
    }

    public List<Tier> getTiers() {
        return tiers;
    }

    public void addTier(Tier tier) {
        tiers.add(tier);
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

    public int getModifierType() {
        return modifierType;
    }

    public void setModifierType(int modifierType) {
        this.modifierType = modifierType;
    }

    public int getSetProperty() {
        return setProperty;
    }

    public void setSetProperty(int setProperty) {
        this.setProperty = setProperty;
    }
}
