package com.vivek.ecommersdemo;

public class MostViewData
{
    int mv_id,mv_product_id;
    String mv_count;

    public MostViewData() {
    }

    public MostViewData(int mv_id, String mv_count, int mv_product_id)
    {
        this.mv_id = mv_id;
        this.mv_product_id = mv_product_id;
        this.mv_count = mv_count;
    }

    public int getMv_id() {
        return mv_id;
    }

    public void setMv_id(int mv_id) {
        this.mv_id = mv_id;
    }

    public int getMv_product_id() {
        return mv_product_id;
    }

    public void setMv_product_id(int mv_product_id) {
        this.mv_product_id = mv_product_id;
    }

    public String getMv_count() {
        return mv_count;
    }

    public void setMv_count(String mv_count) {
        this.mv_count = mv_count;
    }
}
