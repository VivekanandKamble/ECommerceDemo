package com.vivek.ecommersdemo;

public class MostShare
{
    int ms_id,ms_product_id;
    String ms_count;

    public MostShare() {
    }

    public MostShare(int ms_id, String ms_count, int ms_product_id) {
        this.ms_id = ms_id;
        this.ms_product_id = ms_product_id;
        this.ms_count = ms_count;
    }

    public int getMs_id() {
        return ms_id;
    }

    public void setMs_id(int ms_id) {
        this.ms_id = ms_id;
    }

    public int getMs_product_id() {
        return ms_product_id;
    }

    public void setMs_product_id(int ms_product_id) {
        this.ms_product_id = ms_product_id;
    }

    public String getMs_count() {
        return ms_count;
    }

    public void setMs_count(String ms_count) {
        this.ms_count = ms_count;
    }
}
