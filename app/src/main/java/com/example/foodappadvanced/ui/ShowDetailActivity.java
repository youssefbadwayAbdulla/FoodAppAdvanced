package com.example.foodappadvanced.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.foodappadvanced.Halper.ManagementCart;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.databinding.ActivityShowDetailBinding;
import com.example.foodappadvanced.domain.FoodDomain;

public class ShowDetailActivity extends AppCompatActivity {
    ActivityShowDetailBinding binding;
    FoodDomain object;
    private int numberOrder = 0;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        managementCart = new ManagementCart(this);
        getBungle();
    }

    private void getBungle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourseId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourseId).into(binding.ivShowDetailPizza);

        binding.tvShowDetailTitle.setText(object.getTitle());
        binding.tvShowDetailPriceText.setText(object.getPrice() + "EGP");
        binding.tvShowDetailDescription.setText(object.getDescription());
        binding.tvShowDetailZero.setText(String.valueOf(numberOrder));
        binding.btnShowDetailAdd.setOnClickListener(view -> {
            numberOrder = numberOrder + 1;
            binding.tvShowDetailZero.setText(String.valueOf(numberOrder));
        });
        binding.btnShowDetailRemove.setOnClickListener(view -> {
            if (numberOrder > 0) {
                numberOrder = numberOrder - 1;
            }
            binding.tvShowDetailZero.setText(String.valueOf(numberOrder));
        });
        binding.btnShowDetailAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCard(numberOrder);
                managementCart.insertFood(object);
                Intent intent=new Intent(ShowDetailActivity.this,CardListActivity.class);
                startActivity(intent);

            }
        });

    }
}