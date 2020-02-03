package com.vivek.ecommersdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter
{
    private ArrayList<CategoryData> categoryData;
    Context context;

    public CategoryAdapter(ArrayList<CategoryData> categoryData, Context context)
    {
        this.categoryData = categoryData;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return categoryData.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder
    {
        TextView textView_Category;
        String stringCategoryID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;

        if (convertView == null)
        {
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.category_list,null);

            holder = new ViewHolder();
            holder.textView_Category = convertView.findViewById(R.id.textView_Category);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textView_Category.setText(categoryData.get(position).getCategory_name());
        holder.stringCategoryID = String.valueOf(categoryData.get(position).getCategory_id());

        final ViewHolder finalHolder = holder;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,ProductOnCategory.class);
                intent.putExtra("CategoryID",finalHolder.stringCategoryID);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
