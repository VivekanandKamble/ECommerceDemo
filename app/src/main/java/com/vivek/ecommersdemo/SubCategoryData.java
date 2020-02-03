package com.vivek.ecommersdemo;

public class SubCategoryData
{
    int subcategory_id,category_id;
    String subcategory;

    public SubCategoryData() {
    }

    public SubCategoryData(int subcategory_id, int category_id, String subcategory)
    {
        this.subcategory_id = subcategory_id;
        this.category_id = category_id;
        this.subcategory = subcategory;
    }

    public int getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(int subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
