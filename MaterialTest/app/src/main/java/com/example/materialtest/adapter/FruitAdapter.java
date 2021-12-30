package com.example.materialtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.materialtest.R;
import com.example.materialtest.activity.FruitActivity;
import com.example.materialtest.data.Fruit;

import java.util.List;

/**
 * Description
 * <p>
 * RecyclerView的适配器，继承自RecyclerView.Adapter,泛型指定为FruitAdapter.ViewHolder
 * @author qricis on 2020/9/2 10:07
 * @version 1.0.0
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> mFruitList;

    /**
     * 寻找定义fruit_item的控件
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private ImageView fruitImage;
        private TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    /**
     * 返回一个ViewHolder(fruit_item)，将recyclerView的视图返回
     * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
  
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);

        //给CardView注册一个点击事件监听器
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());

        //传入一个context/fragment/activity参数，然后加载图片，可以是URL地址/本地路径/资源id，然后将图片设置到某一个imageView中
        //不需要担心内存溢出
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
