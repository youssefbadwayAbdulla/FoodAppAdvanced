package com.example.foodappadvanced.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.databinding.CategoresIteamBinding;
import com.example.foodappadvanced.domain.FoodDmain;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder> {
    List<FoodDmain> categoriesModelList;
    Context context;

    public CategoriesAdapter(List<FoodDmain> categoriesModelList, Context context) {
        this.categoriesModelList = categoriesModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.categores_iteam, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
        FoodDmain categoriesModel = categoriesModelList.get(position);
        holder.binding.tvTitle.setText(categoriesModel.getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="cat_1";
                holder.binding.cardPizza.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
                break;
            }
            case 1:{
                picUrl="cat_2";
                holder.binding.cardPizza.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background2));
                break;
            }
            case 2:{
                picUrl="cat_3";
                holder.binding.cardPizza.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background3));
                break;
            }
            case 3:{
                picUrl="cat_4";
                holder.binding.cardPizza.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background4));
                break;
            }
            case 4:{
                picUrl="cat_5";
                holder.binding.cardPizza.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background5));
                break;
            }
        }
        int drawableResourseId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourseId).into(holder.binding.cardPizza);
    }

    @Override
    public int getItemCount() {
        return categoriesModelList.size();
    }

    public class CategoriesHolder extends RecyclerView.ViewHolder {
        CategoresIteamBinding binding;

        public CategoriesHolder(@NonNull CategoresIteamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
