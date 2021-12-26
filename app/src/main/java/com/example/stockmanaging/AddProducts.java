package com.example.stockmanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    ArrayList<String> arrayList;
    Button addbtn,backbtn;
    EditText pName,pCode,pPrice,pDetails;
    String url="https://stockmanaging56.000webhostapp.com/addApi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        addbtn=findViewById(R.id.addbutton);
        backbtn=findViewById(R.id.backbutton);
        pName=findViewById(R.id.pnameid);
        pCode=findViewById(R.id.pcodeid);
        pPrice=findViewById(R.id.ppriceid);
        pDetails=findViewById(R.id.pdetailsid);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=pName.getText().toString();
                String pcode=pCode.getText().toString();
                String pprice=pPrice.getText().toString();
                String pdetails=pDetails.getText().toString();
                addData(name,pcode,pprice,pdetails);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddProducts.this,ProductList.class);
                startActivity(intent);
            }
        });
    }
    public void addData(String name,String code,String price,String details){
       RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Data add failed",Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        }
        ){
            protected Map<String,String> getParams(){
                Map<String,String> par=new HashMap<String,String>();
                par.put("pname",name);
                par.put("pcode",code);
                par.put("sellprice",price);
                par.put("pdetails",details);
                return par;
            }
        } ;
        requestQueue.add(stringRequest);
    }
}