package com.example.foodappadvanced.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappadvanced.ChangeNumberItemsListener;
import com.example.foodappadvanced.Halper.ManagementCart;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.databinding.ItemCardBinding;
import com.example.foodappadvanced.domain.FoodDomain;

import java.util.ArrayList;
import java.util.List;


public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardListHolder> {
    ArrayList<FoodDomain> foodDomainList;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;


    public CardListAdapter(ArrayList<FoodDomain> foodDomainList, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomainList = foodDomainList;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CardListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardListHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardListHolder holder, int position) {
        FoodDomain foodDomain = foodDomainList.get(position);
        holder.binding.title2Txt.setText(foodDomain.getTitle());
        holder.binding.priceEachItem.setText(String.valueOf(foodDomain.getPrice()));
        holder.binding.totalEachItem.setText(Math.round(foodDomain.getNumberInCard() * foodDomain.getPrice() * 100 / 100));
        holder.binding.numberItemTxt.setText(String.valueOf(foodDomain.getNumberInCard()));

        int drawableResourceId = holder.itemView.getContext().getResources().
                getIdentifier(foodDomainList.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.binding.ivPicCard);

        holder.binding.plusCardBtn.setOnClickListener(view -> {
            managementCart.piusNumberFood(foodDomainList, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });
        });
        holder.binding.minusCardBtn.setOnClickListener(view -> {
        managementCart.MinusNumberFood(foodDomainList, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        });

        });

    }

    @Override
    public int getItemCount() {
        return foodDomainList.size();
    }

    public class CardListHolder extends RecyclerView.ViewHolder {
        ItemCardBinding binding;

        public CardListHolder(@NonNull ItemCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
