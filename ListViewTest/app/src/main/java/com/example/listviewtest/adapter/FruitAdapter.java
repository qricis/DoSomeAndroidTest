package com.example.listviewtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewtest.R;
import com.example.listviewtest.data.Fruit;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(@NonNull Context context, int resourceId, @NonNull List<Fruit> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }

    /**
     * 普通
     * */
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        //获取当前项的fruit实例
//        Fruit fruit = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        ImageView imageView = view.findViewById(R.id.image_fruit);
//        TextView textView = view.findViewById(R.id.text_fruit_name);
//
//        imageView.setImageResource(fruit.getImageId());
//        textView.setText(fruit.getName());
//
//        return view;
//
//    }

    /**
     * 优化版本
     * */
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        //获取当前项的fruit实例
//        Fruit fruit = getItem(position);
//
//        View view;
//        if(convertView == null){
//            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        }else {
//            view = convertView;
//        }
//
//        ImageView imageView = view.findViewById(R.id.image_fruit);
//        TextView textView = view.findViewById(R.id.text_fruit_name);
//
//        imageView.setImageResource(fruit.getImageId());
//        textView.setText(fruit.getName());
//
//        return view;
//
//    }

    /**
     * 更加优化版本
     * */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //获取当前项的fruit实例
        Fruit fruit = getItem(position);

        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.imageView = view.findViewById(R.id.image_fruit);
            viewHolder.textView = view.findViewById(R.id.text_fruit_name);

            //将viewHolder储存在View中
            view.setTag(viewHolder);
        }else {
            view = convertView;

            //重新获取viewHolder
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());

        return view;

    }

    class ViewHolder{

        ImageView imageView;
        TextView textView;

    }

}
