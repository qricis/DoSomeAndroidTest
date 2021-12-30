package com.example.recyclerviewtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.data.Fruit;

import java.util.List;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/13 16:51
 * @version 1.0.0
 */
public class FruitAdapterClick extends RecyclerView.Adapter<FruitAdapterClick.ViewHolder> {

    private List<Fruit> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);

            fruitView = view;
            mImageView = view.findViewById(R.id.image_fruit_waterfall);
            mTextView = view.findViewById(R.id.text_fruit_name_waterfall);

        }
    }

    public FruitAdapterClick(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_waterfall,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(),"you clicked view " + fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(view.getContext(),"you clicked image " + fruit.getImageId(),Toast.LENGTH_SHORT).show();
            }
        });

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
