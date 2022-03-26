package com.example.foodappadvanced.Halper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodappadvanced.ChangeNumberItemsListener;
import com.example.foodappadvanced.domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> foodDomainArrayList = getListCart();
        boolean existAlready = false;
        int number = 0;
        for (int i = 0; i < foodDomainArrayList.size(); i++) {
            if (foodDomainArrayList.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                number = i;
                break;
            }
        }
        if (existAlready) {
            foodDomainArrayList.get(number).setNumberInCard(item.getNumberInCard());
        } else {
            foodDomainArrayList.add(item);
        }
        tinyDB.putListObject("cartList", foodDomainArrayList);
        Toast.makeText(context, "Added To your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("cartList");
    }

    public void piusNumberFood(ArrayList<FoodDomain> foodDomainArrayList, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        foodDomainArrayList.get(position).setNumberInCard(foodDomainArrayList.get(position).getNumberInCard() + 1);
        tinyDB.putListObject("cartList", foodDomainArrayList);
        changeNumberItemsListener.change();
    }

    public void MinusNumberFood(ArrayList<FoodDomain> foodDomainArrayList, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (foodDomainArrayList.get(position).getNumberInCard() == 1) {
            foodDomainArrayList.remove(position);
        }else{
            foodDomainArrayList.get(position).setNumberInCard(foodDomainArrayList.get(position).getNumberInCard() -1);
        }
        tinyDB.putListObject("cartList",foodDomainArrayList);
        changeNumberItemsListener.change();
    }
    public int getTotalPrice(){
        ArrayList<FoodDomain>foodDomainArrayList=getListCart();
        int price=0;
        for (int i=0;i<foodDomainArrayList.size();i++){
                price=price+(foodDomainArrayList.get(i).getPrice()*foodDomainArrayList.get(i).getNumberInCard());
        }
        return price;
    }
}
