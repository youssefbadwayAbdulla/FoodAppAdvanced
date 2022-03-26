package com.example.foodappadvanced.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodappadvanced.ChangeNumberItemsListener;
import com.example.foodappadvanced.Halper.ManagementCart;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.adapter.CardListAdapter;
import com.example.foodappadvanced.databinding.ActivityCardListBinding;
import com.example.foodappadvanced.domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity {
    ActivityCardListBinding binding;
    private ManagementCart managementCart;
    private int Tax;
    ArrayList<FoodDomain> foodDomainList;
    CardListAdapter cardListAdapter;
    private static final String TAG = "rrrr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_list);
        managementCart = new ManagementCart(this);
        initList();
        bottomNavigation();
        calculateCard();
        Log.d(TAG, "change: "+managementCart.getListCart().get(0).getTitle());

    }

    private void bottomNavigation() {
        //    FloatingActionButton floatingActionButton = findViewById(R.id.btn_cart_main);
        //   LinearLayout homeBtn = findViewById(R.id.homeBtn);


    }

    private void initList() {
        cardListAdapter = new CardListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCard();

                binding.rvListAa.setAdapter(cardListAdapter);
                Log.d(TAG, "change: "+managementCart.getListCart().get(0).getTitle());
            }
        });

        if (managementCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollView.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollView.setVisibility(View.VISIBLE);
        }


    }

    public void calculateCard() {
        double percentTax = 0.02;
        double delivery = 10;
        Tax = (int) (Math.round((managementCart.getTotalPrice() * percentTax) * 100.0) / 100.0);
        double total = Math.round((managementCart.getTotalPrice() + Tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round((managementCart.getTotalPrice() * 100.0) / 100.0);
        binding.tvPriceItemTotal.setText(itemTotal + "EGP");
        binding.tvPriceTax.setText(Tax + "EGP");
        binding.tvPriceDelivery.setText(delivery + "EGP");
        binding.tvPriceTotal.setText(total + "GEP");

    }

}