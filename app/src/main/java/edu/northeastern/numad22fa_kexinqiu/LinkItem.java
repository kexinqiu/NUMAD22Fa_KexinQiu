package edu.northeastern.numad22fa_kexinqiu;

public class LinkItem {

    private final String itemName;
    private final String itemURL;

    //Constructor
    public LinkItem(String itemName, String itemDesc) {
        this.itemName = itemName;
        this.itemURL = itemDesc;
    }

    //Getters for the imageSource, itemName and itemDesc
    public String getItemURl() {
        return itemURL;
    }

    public String getItemName() {
        return itemName;
    }
}