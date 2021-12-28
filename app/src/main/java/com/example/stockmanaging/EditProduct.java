package com.example.stockmanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditProduct extends AppCompatActivity {
EditText edid,edname,edcode,edprice,eddetails;

    String updateLink ="https://stockmanaging56.000webhostapp.com/phpApi/update.php";
private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        edid=findViewById(R.id.editproids);
        edname=findViewById(R.id.editpronameids);
        edcode=findViewById(R.id.editprocodeids);
        edprice=findViewById(R.id.editpropriceids);
        eddetails=findViewById(R.id.editprodetailsids);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        edid.setText(ProductList.productsArrayList.get(position).getId());
        edname.setText(ProductList.productsArrayList.get(position).getPname());
        edcode.setText(ProductList.productsArrayList.get(position).getPcode());
        edprice.setText(ProductList.productsArrayList.get(position).getPprice());
        eddetails.setText(ProductList.productsArrayList.get(position).getPdetails());

    }
    public void updatebtn(View view) {
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
        updateData();


    }
    public void updateData(){
        final String id,name,code,price,details;
        id=edid.getText().toString();
        name=edname.getText().toString();
        code=edcode.getText().toString();
        price=edprice.getText().toString();
        details=eddetails.getText().toString();

        RequestQueue requestQueue= Volley.newRequestQueue(EditProduct.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,updateLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditProduct.this,response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProduct.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        }
        ){
            protected Map<String,String> getParams(){
                Map<String,String> par=new HashMap<String,String>();
                par.put("id",id);
                par.put("pname",name);
                par.put("pcode",code);
                par.put("sellprice",price);
                par.put("pdetails",details);
                return par;
            }
        };
        requestQueue.add(stringRequest);
    }
}