package com.example.recyclerviewtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.data.Fruit;

import java.util.List;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/13 13:51
 * @version 1.0.0
 */
public class FruitAdapterHorizontal extends RecyclerView.Adapter<FruitAdapterHorizontal.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);

            mImageView = view.findViewById(R.id.image_fruit_horizontal);
            mTextView = view.findViewById(R.id.text_fruit_name_horizontal);

        }
    }

    public FruitAdapterHorizontal(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_horizontal,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.mImageView.setImageResource(fruit.getImageId());
        holder.mTextView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
