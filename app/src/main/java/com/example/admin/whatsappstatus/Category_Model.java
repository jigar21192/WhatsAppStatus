package com.example.admin.whatsappstatus;

/**
 * Created by admin on 06-09-2018.
 */

class Category_Model {
    String c_id,category;


    public Category_Model(String c_id, String category) {
        this.c_id = c_id;
        this.category = category;
    }
    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
