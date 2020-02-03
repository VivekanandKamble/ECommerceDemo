package com.vivek.ecommersdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AllProduct extends AppCompatActivity
{
    ListView listView_AllProduct;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);

        listView_AllProduct=findViewById(R.id.listView_AllProduct);
        ArrayList<ProductData> productData = databaseHandler.getAllProduct();
        ProductAdapter productAdapter =new ProductAdapter(productData,this);
        listView_AllProduct.setAdapter(productAdapter);
    }
}
