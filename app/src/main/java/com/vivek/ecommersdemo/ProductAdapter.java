package com.vivek.ecommersdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter
{

    private ArrayList<ProductData> productData;
    Context context;

    public ProductAdapter(ArrayList<ProductData> productData, Context context)
    {
        this.productData = productData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productData.size();
    }

    @Override
    public Object getItem(int position) {
        return productData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder
    {
        TextView textViewProductName,textViewTaxName,textViewTaxAmount;
        String stringCatID,stringProdID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ProductAdapter.ViewHolder holder = null;

        if (convertView == null)
        {
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.product_adapter,null);

            holder = new ProductAdapter.ViewHolder();
            holder.textViewProductName = convertView.findViewById(R.id.textViewProductName);
            holder.textViewTaxName = convertView.findViewById(R.id.textViewTaxName);
            holder.textViewTaxAmount = convertView.findViewById(R.id.textViewTaxAmount);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ProductAdapter.ViewHolder)convertView.getTag();
        }

        holder.textViewProductName.setText(productData.get(position).getProduct_name());
        holder.textViewTaxName.setText("Tax Name : "+productData.get(position).getTax_name());
        holder.textViewTaxAmount.setText("Tax Amount : "+productData.get(position).getTax_amount());

        holder.stringCatID = String.valueOf(productData.get(position).getCategory_id());
        holder.stringProdID = String.valueOf(productData.get(position).getProduct_id());

        return convertView;
    }
}
