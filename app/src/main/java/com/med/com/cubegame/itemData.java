package com.med.com.cubegame;

import android.graphics.Bitmap;

public class itemData {

    public String item_title;
    public String item_desc;

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }



    public itemData(String item_title,String item_desc){

        this.item_title = item_title;
        this.item_desc = item_desc;
    }

    public String getitem_title() {
        return item_title;
    }

    public void setitem_title(String item_title) {
        this.item_title = item_title;
    }

}

