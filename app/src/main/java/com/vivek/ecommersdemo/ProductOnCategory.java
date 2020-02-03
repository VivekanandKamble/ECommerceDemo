package com.vivek.ecommersdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductOnCategory extends AppCompatActivity
{
    ListView listView_Product;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_on_category);

        listView_Product= findViewById(R.id.listView_Product);
        String str = getIntent().getStringExtra("CategoryID");
        System.out.println("Category ID is : "+ str);

        ArrayList<ProductData> productData = databaseHandler.getAllProductThroughCategory(Integer.parseInt(str));
        if (productData.size() == 0)
        {
            Toast.makeText(this, "No Any Product in this Categories", Toast.LENGTH_SHORT).show();
        }
        ProductAdapter productAdapter =new ProductAdapter(productData,this);
        listView_Product.setAdapter(productAdapter);

    }
}
