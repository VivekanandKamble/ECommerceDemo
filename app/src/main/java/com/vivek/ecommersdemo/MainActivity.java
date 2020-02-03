package com.vivek.ecommersdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    String [] category_name,product_name,subcategory_name,variant_color,variant_size,variant_price,rank_title,view_count,order_count,share_count;
    int [] category_id,product_id,variant_id,view_id,order_id,share_id;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    ListView listView_Category;
    Button button_AllProduct;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean("firstTime",false))
        {
            getDataFromLocalJSON();
        }

        button_AllProduct=findViewById(R.id.button_AllProduct);
        listView_Category = findViewById(R.id.listView_Category);

        ArrayList<CategoryData> category = databaseHandler.getAllCategory();
        CategoryAdapter categoryAdapter = new CategoryAdapter(category,this);
        listView_Category.setAdapter(categoryAdapter);

        button_AllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllProduct.class);
                startActivity(intent);
            }
        });


//        for (CategoryData cd : category)
//        {
//            String getdata = "ID : "+ cd.getCategory_id()+" Name : "+cd.getCategory_name();
//            System.out.println("Category Data is : "+ getdata);
//        }
//
//        List<SubCategoryData> subcategory = databaseHandler.getAllSubCategory();
//        for (SubCategoryData scd : subcategory)
//        {
//            String getSubCategoryData = "ID : "+ scd.getSubcategory_id()+" Category ID : "+scd.getCategory_id()+" Sub Category : "+scd.getSubcategory();
//            System.out.println("Sub Category Data is : "+ getSubCategoryData);
//        }



//        List<VariantData> variantData = databaseHandler.getAllVariant();
//        for (VariantData variants : variantData)
//        {
//            String getVariantData = "ID : "+ variants.getVariant_id()+" Product ID : "+variants.getProduct_id()+" Color : "+variants.getVariant_color()
//                    +" Size : "+variants.getVariant_size()+" Price : "+variants.getVariant_price();
//            System.out.println("Variant Data is : "+ getVariantData);
//        }
//
//        List<MostViewData> mostViewData = databaseHandler.getAllMostView();
//        for (MostViewData viewData : mostViewData)
//        {
//            String getMostViewData = "ID : "+ viewData.getMv_id()+" Product ID : "+viewData.getMv_product_id()+" View Count: "+viewData.getMv_count();
//            System.out.println("Most View Data is : "+ getMostViewData);
//        }
//
//        List<MostOrderData> mostOrderData = databaseHandler.getAllMostOrder();
//        for (MostOrderData orderData : mostOrderData)
//        {
//            String getMostOrderData = "ID : "+ orderData.getMo_id()+" Product ID : "+orderData.getMo_product_id()+" Order Count: "+orderData.getMo_count();
//            System.out.println("Most Order Data is : "+ getMostOrderData);
//        }
//
//        List<MostShare> mostShareData = databaseHandler.getAllMostShare();
//        for (MostShare mostShare : mostShareData)
//        {
//            String getMostShareData = "ID : "+ mostShare.getMs_id()+" Product ID : "+mostShare.getMs_product_id()+" Order Count: "+mostShare.getMs_count();
//            System.out.println("Most Share Data is : "+ getMostShareData);
//        }



    }

    private void getDataFromLocalJSON()
    {
        String string_json=null;
        try
        {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            string_json = new String(buffer,"UTF-8");
            JSONObject jsonObject = new JSONObject(string_json);

            JSONArray jsonArray = jsonObject.getJSONArray("categories");
            System.out.println("JSON Array : "+ jsonArray );
            category_id = new int[jsonArray.length()];
            category_name = new String[jsonArray.length()];

            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);
                category_id[i]=obj.getInt("id");
                category_name[i]=obj.getString("name");
                System.out.println("Category Names : "+category_id[i]+" : "+ category_name[i]);

                databaseHandler.addCategory(category_id[i],category_name[i]);

                JSONArray jsonArrayProduct = obj.getJSONArray("products");
                System.out.println("Product Array : "+ jsonArrayProduct.length());

                if (jsonArrayProduct.length()>0)
                {
                    product_id = new int[jsonArrayProduct.length()];
                    product_name = new String[jsonArrayProduct.length()];
                    for (int k=0;k<jsonArrayProduct.length();k++)
                    {
                        JSONObject objProduct = jsonArrayProduct.getJSONObject(k);
                        product_id[k]=objProduct.getInt("id");
                        product_name[k]=objProduct.getString("name");
                        JSONObject jsonObjectTax = objProduct.getJSONObject("tax");
                        JSONArray jsonArrayVariant =objProduct.getJSONArray("variants");
                        System.out.println("Category Product Names : "+product_id[k]+" : "+ product_name[k] + " : "+jsonObjectTax.get("name")+" : "+jsonObjectTax.get("value"));

                        databaseHandler.addProduct(product_id[k],category_id[i],product_name[k],jsonObjectTax.get("name").toString(),jsonObjectTax.get("value").toString());

                        variant_id = new int[jsonArrayVariant.length()];
                        variant_color = new String[jsonArrayVariant.length()];
                        variant_size = new String[jsonArrayVariant.length()];
                        variant_price = new String[jsonArrayVariant.length()];
                        for(int a=0;a<jsonArrayVariant.length();a++)
                        {
                            JSONObject objVariant = jsonArrayVariant.getJSONObject(a);
                            variant_id[a]=objVariant.getInt("id");
                            variant_color[a]=objVariant.getString("color");
                            variant_size[a]=objVariant.getString("size");
                            variant_price[a]=objVariant.getString("price");
                            System.out.println("Product Variant : "+ variant_id[a]+" : "+variant_color[a]+" : "+variant_size[a]+" : "+variant_price[a]);
                            databaseHandler.addVariant(variant_id[a],product_id[k],variant_color[a],variant_size[a],variant_price[a]);
                        }
                    }
                }

                JSONArray jsonArrayChildCategory = obj.getJSONArray("child_categories");
                if (jsonArrayChildCategory.length()>0)
                {
                    subcategory_name = new String[jsonArrayChildCategory.length()];
                    for (int j=0;j<jsonArrayChildCategory.length();j++)
                    {
                        System.out.println("Child Categories Name : "+ category_id[i]+" : "+jsonArrayChildCategory.get(j));
                        databaseHandler.addSubCategory(String.valueOf(jsonArrayChildCategory.get(j)),category_id[i]);
                    }
                }
            }

            JSONArray jsonArrayRank = jsonObject.getJSONArray("rankings");
            System.out.println("JSON ArrayRank : "+ jsonArrayRank );

            rank_title=new String[jsonArrayRank.length()];

            for (int b=0;b<jsonArrayRank.length();b++)
            {
                JSONObject jsonObjectRank = jsonArrayRank.getJSONObject(b);
                rank_title[b]=jsonObjectRank.getString("ranking");

                System.out.println("Ranking Title is : "+ rank_title[b]);

                if (rank_title[b].matches("Most Viewed Products"))
                {
                    JSONArray jsonArrayProductRank = jsonObjectRank.getJSONArray("products");
                    System.out.println("JSON Array Product Rank Length : "+ jsonArrayProductRank.length());

                    view_id = new int[jsonArrayProductRank.length()];
                    view_count = new String[jsonArrayProductRank.length()];

                    for (int c=0;c<jsonArrayProductRank.length();c++)
                    {
                        JSONObject jsonRankObject = jsonArrayProductRank.getJSONObject(c);
                        view_id[c] = jsonRankObject.getInt("id");
                        view_count[c] = jsonRankObject.getString("view_count");
                        databaseHandler.addView(c+1,view_id[c],view_count[c]);
                    }
                }
                else if (rank_title[b].matches("Most OrdeRed Products"))
                {
                    JSONArray jsonArrayOrder = jsonObjectRank.getJSONArray("products");
                    System.out.println("JSON Array Product Order Length : "+ jsonArrayOrder.length());

                    order_id = new int[jsonArrayOrder.length()];
                    order_count = new String[jsonArrayOrder.length()];

                    for (int c=0;c<jsonArrayOrder.length();c++)
                    {
                        JSONObject jsonOrderObject = jsonArrayOrder.getJSONObject(c);
                        order_id[c] = jsonOrderObject.getInt("id");
                        order_count[c] = jsonOrderObject.getString("order_count");
                        databaseHandler.addOrder(c+1,order_id[c],order_count[c]);
                    }
                }
                else if (rank_title[b].matches("Most ShaRed Products"))
                {
                    JSONArray jsonArrayShared = jsonObjectRank.getJSONArray("products");
                    System.out.println("JSON Array Product Share Length : "+ jsonArrayShared.length());

                    share_id = new int[jsonArrayShared.length()];
                    share_count = new String[jsonArrayShared.length()];

                    for (int c=0;c<jsonArrayShared.length();c++)
                    {
                        JSONObject jsonShareObject = jsonArrayShared.getJSONObject(c);
                        share_id[c] = jsonShareObject.getInt("id");
                        share_count[c] = jsonShareObject.getString("shares");
                        databaseHandler.addShare(c+1,share_id[c],share_count[c]);
                    }
                }
                else
                {

                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("firstTime",true);
        editor.commit();

        System.out.println("All Data Store in DataBase and Create SharedPreferences");
    }

}
