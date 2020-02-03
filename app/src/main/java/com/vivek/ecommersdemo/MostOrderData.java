package com.vivek.ecommersdemo;

public class MostOrderData
{
    int mo_id,mo_product_id;
    String mo_count;

    public MostOrderData() {
    }

    public MostOrderData(int mo_id, String mo_count, int mo_product_id) {
        this.mo_id = mo_id;
        this.mo_product_id = mo_product_id;
        this.mo_count = mo_count;
    }

    public int getMo_id() {
        return mo_id;
    }

    public void setMo_id(int mo_id) {
        this.mo_id = mo_id;
    }

    public int getMo_product_id() {
        return mo_product_id;
    }

    public void setMo_product_id(int mo_product_id) {
        this.mo_product_id = mo_product_id;
    }

    public String getMo_count() {
        return mo_count;
    }

    public void setMo_count(String mo_count) {
        this.mo_count = mo_count;
    }
}
