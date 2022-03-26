package com.example.foodappadvanced.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.example.foodappadvanced.Halper.ManagementCart;
import com.example.foodappadvanced.R;
import com.example.foodappadvanced.adapter.CategoriesAdapter;
import com.example.foodappadvanced.adapter.PopularAdapter;
import com.example.foodappadvanced.databinding.ActivityMainBinding;
import com.example.foodappadvanced.domain.FoodDmain;
import com.example.foodappadvanced.domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    CategoriesAdapter categoriesAdapter;

    PopularAdapter popularAdapter;
    List<FoodDomain> foodDomainList;
    List<FoodDmain> categoriesModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bottomNavigation();
        recyclerViewCategory();
        recyclerViewPopular();

    }
    private void bottomNavigation() {
      binding.btnCartMain.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,CardListActivity.class);
              startActivity(intent);
          }
      });
      binding.homeBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,CardListActivity.class);
              startActivity(intent);

          }
      });
    }



    private void recyclerViewPopular() {
        foodDomainList=new ArrayList<>();
        foodDomainList.add(new FoodDomain("Pepperoni pizza","pizza1","pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients, baked quickly—usually, in a commercial setting, using a wood-fired oven heated to a very high temperature—and served hot.",30));
        foodDomainList.add(new FoodDomain("Cheese Burger","burger","Pizza capricciosa (Italian pronunciation: [ˈpittsa kapritˈtʃoːza]) is a style of pizza in Italian cuisine prepared with mozzarella cheese, Italian baked ham, mushroom, artichoke and tomato.[1][2][3] Types of edible mushrooms used may include cremini (white mushrooms)[3][4] and others. Some versions may also use prosciutto (a dry-cured ham)",23));
        foodDomainList.add(new FoodDomain("Vegetable Pizza","pizza2","dish of Italian origin consisting of a flattened disk of bread dough topped with some com",78));
        popularAdapter=new PopularAdapter(foodDomainList,this);
        binding.popularRv.setAdapter(popularAdapter);


    }

    private void recyclerViewCategory() {
        categoriesModelList = new ArrayList<>();
        categoriesModelList.add(new FoodDmain(getString(R.string.pizza), "cat_1"));
        categoriesModelList.add(new FoodDmain(getString(R.string.burger), "cat_2"));
        categoriesModelList.add(new FoodDmain(getString(R.string.hotDog), "cat_3"));
        categoriesModelList.add(new FoodDmain(getString(R.string.drink), "cat_4"));
        categoriesModelList.add(new FoodDmain(getString(R.string.donut), "cat_5"));
        categoriesAdapter = new CategoriesAdapter(categoriesModelList, this);
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }
}