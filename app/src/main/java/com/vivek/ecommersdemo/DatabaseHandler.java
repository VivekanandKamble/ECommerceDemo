package com.vivek.ecommersdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ecommercs";

    //category table
    private static final String TABLE_CATEGORY="category";
    private static final String CATEGORY_ID="category_id";
    private static final String CATEGORY_NAME="category_name";

    //subcategory table
    private static final String TABLE_SUBCATEGORY="subcategory";
    private static final String SUBCATEGORY_ID="subcategory_id";
    private static final String FK_CATEGORY_ID="fk_category_id";
    private static final String SUBCATEGORY="subcategory";

    //product table
    private static final String TABLE_PRODUCT="product";
    private static final String PRODUCT_ID="product_id";
    private static final String FK_PRODUCT_CATEGORY_ID="fk_category_id";
    private static final String PRODUCT_NAME="product_name";
    private static final String TAX_NAME="tax_name";
    private static final String TAX_AMOUNT="tax_amount";

    //product variant
    private static final String TABLE_VARIANT="variant";
    private static final String VARIANT_ID="variant_id";
    private static final String FK_PRODUCT_ID="fk_product_id";
    private static final String COLOR="color";
    private static final String SIZE="size";
    private static final String PRICE="price";

    //product most view product
    private static final String TABLE_MOST_VIEW="most_view";
    private static final String MOST_VIEW_ID="most_view_id";
    private static final String FK_MOST_VIEW_PRODUCT_ID="fk_product_id";
    private static final String MOST_VIEW_COUNT_="view_count";

    //product most order product
    private static final String TABLE_MOST_ORDER="most_order";
    private static final String MOST_ORDER_ID="most_order_id";
    private static final String FK_MOST_ORDER_PRODUCT_ID="fk_product_id";
    private static final String MOST_ORDER_COUNT_="order_count";

    //product most share product
    private static final String TABLE_MOST_SHARE="most_share";
    private static final String MOST_SHARE_ID="most_share_id";
    private static final String FK_MOST_SHARE_PRODUCT_ID="fk_product_id";
    private static final String MOST_SHARE_COUNT_="share_count";


    public DatabaseHandler(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE_CATEGORY="CREATE TABLE "+TABLE_CATEGORY+"("+CATEGORY_ID+" INTEGER PRIMARY KEY,"
                +CATEGORY_NAME+" TEXT"+")";
        db.execSQL(CREATE_TABLE_CATEGORY);
        System.out.println("Table Category is Created");

        String CREATE_TABLE_SUBCATEGORY="CREATE TABLE "+TABLE_SUBCATEGORY+"("+SUBCATEGORY_ID+" INTEGER PRIMARY KEY, "
                +SUBCATEGORY+" TEXT, "+FK_CATEGORY_ID+" INTEGER, "+" FOREIGN KEY ("+FK_CATEGORY_ID+") REFERENCES "+TABLE_CATEGORY+"("+CATEGORY_ID+"))";
        db.execSQL(CREATE_TABLE_SUBCATEGORY);
        System.out.println("Table Sub Category is Created");

        String CREATE_TABLE_PRODUCT="CREATE TABLE "+TABLE_PRODUCT+"("+PRODUCT_ID+" INTEGER PRIMARY KEY,"
                +PRODUCT_NAME+" TEXT,"+TAX_NAME+" TEXT,"+TAX_AMOUNT+" TEXT,"
                +FK_PRODUCT_CATEGORY_ID+" INTEGER,"+"FOREIGN KEY("+FK_PRODUCT_CATEGORY_ID+") REFERENCES "+TABLE_CATEGORY+"("+CATEGORY_ID+"))";
        db.execSQL(CREATE_TABLE_PRODUCT);
        System.out.println("Table Product is Created");

        String CREATE_TABLE_VARIANT="CREATE TABLE "+TABLE_VARIANT+"("+VARIANT_ID+" INTEGER PRIMARY KEY,"
                +COLOR+" TEXT,"+SIZE+" TEXT,"+PRICE+" TEXT,"
                +FK_PRODUCT_ID+" INTEGER,"+"FOREIGN KEY("+FK_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+"("+PRODUCT_ID+"))";
        db.execSQL(CREATE_TABLE_VARIANT);
        System.out.println("Table Variant is Created");

        String CREATE_TABLE_MOST_VIEW="CREATE TABLE "+TABLE_MOST_VIEW+"("+MOST_VIEW_ID+" INTEGER PRIMARY KEY,"
                +MOST_VIEW_COUNT_+" TEXT,"+FK_MOST_VIEW_PRODUCT_ID+" INTEGER,"+"FOREIGN KEY("+FK_MOST_VIEW_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+"("+PRODUCT_ID+"))";
        db.execSQL(CREATE_TABLE_MOST_VIEW);
        System.out.println("Table Most View is Created");

        String CREATE_TABLE_MOST_ORDER="CREATE TABLE "+TABLE_MOST_ORDER+"("+MOST_ORDER_ID+" INTEGER PRIMARY KEY,"
                +MOST_ORDER_COUNT_+" TEXT,"+FK_MOST_ORDER_PRODUCT_ID+" INTEGER,"+"FOREIGN KEY("+FK_MOST_ORDER_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+"("+PRODUCT_ID+"))";
        db.execSQL(CREATE_TABLE_MOST_ORDER);
        System.out.println("Table Most Order is Created");

        String CREATE_TABLE_MOST_SHARE="CREATE TABLE "+TABLE_MOST_SHARE+"("+MOST_SHARE_ID+" INTEGER PRIMARY KEY,"
                +MOST_SHARE_COUNT_+" TEXT,"+FK_MOST_SHARE_PRODUCT_ID+" INTEGER,"+"FOREIGN KEY("+FK_MOST_SHARE_PRODUCT_ID+") REFERENCES "+TABLE_PRODUCT+"("+PRODUCT_ID+"))";
        db.execSQL(CREATE_TABLE_MOST_SHARE);
        System.out.println("Table Most Share is Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SUBCATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_VARIANT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOST_VIEW);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOST_ORDER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MOST_SHARE);

    }

    public void addCategory(int id,String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_ID,id);
        contentValues.put(CATEGORY_NAME,name);
        db.insertOrThrow(TABLE_CATEGORY,null,contentValues);
        db.close();
    }

    public void addSubCategory(String subcategory,int category_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVSubCategory = new ContentValues();
        CVSubCategory.put(SUBCATEGORY,subcategory);
        CVSubCategory.put(FK_CATEGORY_ID,category_id);
        db.insertOrThrow(TABLE_SUBCATEGORY,null,CVSubCategory);
        db.close();
    }

    public void addProduct(int product_id,int category_id,String product_name,String tax_name,String tax_amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVProduct = new ContentValues();
        CVProduct.put(PRODUCT_ID,product_id);
        CVProduct.put(FK_PRODUCT_CATEGORY_ID,category_id);
        CVProduct.put(PRODUCT_NAME,product_name);
        CVProduct.put(TAX_NAME,tax_name);
        CVProduct.put(TAX_AMOUNT,tax_amount);
        db.insertOrThrow(TABLE_PRODUCT,null,CVProduct);
        db.close();
    }

    public void addVariant(int variant_id,int product_id,String variant_color,String variant_size,String variant_price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVVariant = new ContentValues();
        CVVariant.put(VARIANT_ID,variant_id);
        CVVariant.put(FK_PRODUCT_ID,product_id);
        CVVariant.put(COLOR,variant_color);
        CVVariant.put(SIZE,variant_size);
        CVVariant.put(PRICE,variant_price);
        db.insertOrThrow(TABLE_VARIANT,null,CVVariant);
        db.close();
    }

    public void addView(int view_id,int product_id,String view_count)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVMostView = new ContentValues();
        CVMostView.put(MOST_VIEW_ID,view_id);
        CVMostView.put(FK_MOST_VIEW_PRODUCT_ID,product_id);
        CVMostView.put(MOST_VIEW_COUNT_,view_count);
        db.insertOrThrow(TABLE_MOST_VIEW,null,CVMostView);
        db.close();
    }

    public void addOrder(int order_id,int product_id,String order_count)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVMostOrder = new ContentValues();
        CVMostOrder.put(MOST_ORDER_ID,order_id);
        CVMostOrder.put(FK_MOST_ORDER_PRODUCT_ID,product_id);
        CVMostOrder.put(MOST_ORDER_COUNT_,order_count);
        db.insertOrThrow(TABLE_MOST_ORDER,null,CVMostOrder);
        db.close();
    }


    public void addShare(int share_id,int product_id,String share_count)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues CVMostShare = new ContentValues();
        CVMostShare.put(MOST_SHARE_ID,share_id);
        CVMostShare.put(FK_MOST_SHARE_PRODUCT_ID,product_id);
        CVMostShare.put(MOST_SHARE_COUNT_,share_count);
        db.insertOrThrow(TABLE_MOST_SHARE,null,CVMostShare);
        db.close();
    }

    public List<MostShare> getAllMostShare()
    {
        List<MostShare> mostShareDataList = new ArrayList<MostShare>();
        String mostShareQuery = "SELECT * FROM "+ TABLE_MOST_SHARE;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(mostShareQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                MostShare mostShare = new MostShare();
                mostShare.setMs_id(cursor.getInt(0));
                mostShare.setMs_count(cursor.getString(1));
                mostShare.setMs_product_id(cursor.getInt(2));
                mostShareDataList.add(mostShare);
            }while (cursor.moveToNext());
        }
        return  mostShareDataList;
    }

    public List<MostOrderData> getAllMostOrder()
    {
        List<MostOrderData> mostOrderDataList = new ArrayList<MostOrderData>();
        String mostOrderQuery = "SELECT * FROM "+ TABLE_MOST_ORDER;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(mostOrderQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                MostOrderData mostOrderData = new MostOrderData();
                mostOrderData.setMo_id(cursor.getInt(0));
                mostOrderData.setMo_count(cursor.getString(1));
                mostOrderData.setMo_product_id(cursor.getInt(2));
                mostOrderDataList.add(mostOrderData);
            }while (cursor.moveToNext());
        }
        return  mostOrderDataList;
    }

    public List<MostViewData> getAllMostView()
    {
        List<MostViewData> mostViewDataList = new ArrayList<MostViewData>();
        String mostViewQuery = "SELECT * FROM "+ TABLE_MOST_VIEW;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(mostViewQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                MostViewData mostViewData = new MostViewData();
                mostViewData.setMv_id(cursor.getInt(0));
                mostViewData.setMv_count(cursor.getString(1));
                mostViewData.setMv_product_id(cursor.getInt(2));
                mostViewDataList.add(mostViewData);
            }while (cursor.moveToNext());
        }
        return  mostViewDataList;
    }


    public List<VariantData> getAllVariant()
    {
        List<VariantData> variantDataList = new ArrayList<VariantData>();
        String variantQuery = "SELECT * FROM "+ TABLE_VARIANT;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(variantQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                VariantData variantData = new VariantData();
                variantData.setVariant_id(cursor.getInt(0));
                variantData.setVariant_color(cursor.getString(1));
                variantData.setVariant_size(cursor.getString(2));
                variantData.setVariant_price(cursor.getString(3));
                variantData.setProduct_id(cursor.getInt(4));
                variantDataList.add(variantData);
            }while (cursor.moveToNext());
        }
        return  variantDataList;
    }


    public ArrayList<ProductData> getAllProduct()
    {
        ArrayList<ProductData> productDataList = new ArrayList<ProductData>();
        String productQuery = "SELECT * FROM "+ TABLE_PRODUCT;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(productQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                ProductData productData = new ProductData();
                productData.setProduct_id(cursor.getInt(0));
                productData.setProduct_name(cursor.getString(1));
                productData.setTax_name(cursor.getString(2));
                productData.setTax_amount(cursor.getString(3));
                productData.setCategory_id(cursor.getInt(4));
                productDataList.add(productData);
            }while (cursor.moveToNext());
        }
        return  productDataList;
    }

    public ArrayList<ProductData> getAllProductThroughCategory(int CategoryID)
    {
        ArrayList<ProductData> productDataList = new ArrayList<ProductData>();
        String productQuery = "SELECT * FROM "+ TABLE_PRODUCT +" WHERE fk_category_id = "+CategoryID;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(productQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                ProductData productData = new ProductData();
                productData.setProduct_id(cursor.getInt(0));
                productData.setProduct_name(cursor.getString(1));
                productData.setTax_name(cursor.getString(2));
                productData.setTax_amount(cursor.getString(3));
                productData.setCategory_id(cursor.getInt(4));
                productDataList.add(productData);
            }while (cursor.moveToNext());
        }
        return  productDataList;
    }


    public ArrayList<CategoryData> getAllCategory()
    {
       ArrayList<CategoryData> categoryDataList = new ArrayList<CategoryData>();
       String selectQuery = "SELECT * FROM "+ TABLE_CATEGORY;

       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);

       if (cursor.moveToFirst())
       {
           do {
               CategoryData categoryData= new CategoryData();
               categoryData.setCategory_id(cursor.getInt(0));
               categoryData.setCategory_name(cursor.getString(1));
               categoryDataList.add(categoryData);
           }while (cursor.moveToNext());
       }
       return  categoryDataList;
    }

    public List<SubCategoryData> getAllSubCategory()
    {
        List<SubCategoryData> subCategoryDataList = new ArrayList<SubCategoryData>();
        String selectSubCategory = "SELECT * FROM "+ TABLE_SUBCATEGORY;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursorSubCategory = sqLiteDatabase.rawQuery(selectSubCategory,null);

        if (cursorSubCategory.moveToFirst())
        {
            do {
                SubCategoryData subCategoryData= new SubCategoryData();
                subCategoryData.setSubcategory_id(cursorSubCategory.getInt(0));
                subCategoryData.setCategory_id(cursorSubCategory.getInt(2));
                subCategoryData.setSubcategory(cursorSubCategory.getString(1));
                subCategoryDataList.add(subCategoryData);
            }while (cursorSubCategory.moveToNext());
        }
        return  subCategoryDataList;
    }

}
