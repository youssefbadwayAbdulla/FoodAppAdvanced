package com.example.foodappadvanced.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.ui.ShowDetailActivity;
import com.example.foodappadvanced.databinding.CategoryPopularBinding;
import com.example.foodappadvanced.domain.FoodDomain;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularHolder> {
    List<FoodDomain> foodDomainLast;
    Context context;

    public PopularAdapter(List<FoodDomain> foodDomainLast, Context context) {
        this.foodDomainLast = foodDomainLast;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_popular, parent, false));
    }

    public void onBindViewHolder(@NonNull PopularHolder holder, int position) {
        FoodDomain foodDomain = foodDomainLast.get(position);
        holder.binding.tvPopularTitle.setText(foodDomain.getTitle());
        holder.binding.tvPopularPrice.setText(String.valueOf(foodDomain.getPrice()));


        int drawableResourseId = holder.itemView.getContext().getResources().getIdentifier(foodDomain.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourseId).into(holder.binding.ivPopularPizza);

        holder.binding.btnPopularAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object",foodDomain);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomainLast.size();
    }

    public class PopularHolder extends RecyclerView.ViewHolder {
        CategoryPopularBinding binding;

        public PopularHolder(@NonNull CategoryPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
