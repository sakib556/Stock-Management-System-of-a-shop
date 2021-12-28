package com.example.stockmanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView tvid,tvname,tvcode,tvprice,tvdetails;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvid=findViewById(R.id.proids);
        tvname=findViewById(R.id.pronameids);
        tvcode=findViewById(R.id.procodeids);
        tvprice=findViewById(R.id.propriceids);
        tvdetails=findViewById(R.id.prodetailsids);
        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");
        tvid.setText("ID : "+ProductList.productsArrayList.get(position).getId());
        tvname.setText("Product Name : "+ProductList.productsArrayList.get(position).getPname());
        tvcode.setText("Code : "+ProductList.productsArrayList.get(position).getPcode());
        tvprice.setText("Price : "+ProductList.productsArrayList.get(position).getPprice());
        tvdetails.setText("Details : "+ProductList.productsArrayList.get(position).getPdetails());
    }
}