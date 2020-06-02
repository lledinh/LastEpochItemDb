Tool for retrieving items data from the video game "Last Epoch".


Last Epoch use Unity3D game engine, so I choosed uTinyRipper (Unity3D assets extractor) to extract raw game data.
This tool parse the raw files extracted by uTinyRipper.

## Configure files
```html
ASSETS_FOLDER=PATH_TO_UTINYRIPPER_EXTRACTED_FILES # Replace
ITEMS_LIST=Resources\MasterItemsList.asset
UNIQUE_ITEMS_LIST=Resources\UniqueList.asset
PROPERTY_LIST=Resources\MasterPropertyList.asset
AFFIX_LIST=Resources\MasterAffixesList.asset
```
## Usage
------ WIP ------
```java
public class Program {
    public static void main(String[] args) throws IOException {
        GameDataExtractor gameDataExtractor = new GameDataExtractor();
        gameDataExtractor.extract();

        List<Item> items = gameDataExtractor.getItems();
    }
}
```



