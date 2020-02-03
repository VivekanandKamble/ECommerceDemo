package com.vivek.ecommersdemo;

public class VariantData
{
    int variant_id,product_id;
    String variant_color,variant_size,variant_price;

    public VariantData() {
    }

    public VariantData(int variant_id, String variant_color, String variant_size, String variant_price, int product_id)
    {
        this.variant_id = variant_id;
        this.product_id = product_id;
        this.variant_color = variant_color;
        this.variant_size = variant_size;
        this.variant_price = variant_price;
    }

    public int getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(int variant_id) {
        this.variant_id = variant_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getVariant_color() {
        return variant_color;
    }

    public void setVariant_color(String variant_color) {
        this.variant_color = variant_color;
    }

    public String getVariant_size() {
        return variant_size;
    }

    public void setVariant_size(String variant_size) {
        this.variant_size = variant_size;
    }

    public String getVariant_price() {
        return variant_price;
    }

    public void setVariant_price(String variant_price) {
        this.variant_price = variant_price;
    }
}
