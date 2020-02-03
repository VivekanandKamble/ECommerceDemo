package com.vivek.ecommersdemo;

public class ProductData
{
    int product_id,category_id;
    String product_name,tax_name,tax_amount;

    public ProductData() {
    }

    public ProductData(int product_id, String product_name, String tax_name, String tax_amount, int category_id)
    {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.tax_name = tax_name;
        this.tax_amount = tax_amount;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }
}
