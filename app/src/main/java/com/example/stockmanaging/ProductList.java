package com.example.stockmanaging;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductList extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    Products products;
    public static ArrayList<Products> productsArrayList=new ArrayList<>();
    String showLink = "https://stockmanaging56.000webhostapp.com/phpApi/show.php";
    String addLink = "https://stockmanaging56.000webhostapp.com/phpApi/add.php";
    String deleteLink = "https://stockmanaging56.000webhostapp.com/phpApi/delete.php";
    // String link="https://zara-business.herokuapp.com/flexi-load/read";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        listView=findViewById(R.id.listId);
        adapter=new MyAdapter(this,productsArrayList);
        showData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder=new  AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog=new ProgressDialog(view.getContext());
                CharSequence[] dialogItem={"View Data","Edit Data","Delete Data"};
                builder.setTitle(productsArrayList.get(position).getPname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),Details.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),EditProduct.class)
                                        .putExtra("position",position));
                                break;
                            case 2:
                                deleteData(productsArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
    public void showData(){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,showLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    productsArrayList.clear();
                    JSONObject jo=new JSONObject(response);
                    String success=jo.getString("Success");
                    JSONArray jsonArray=jo.getJSONArray("data");
                    if(success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("productName");
                            String code = jsonObject.getString("productCode");
                            String price = jsonObject.getString("sellPrice");
                            String details = jsonObject.getString("productDetails");
                            products= new Products(id,name,code,price,details);
                            productsArrayList.add(products);
                            adapter.notifyDataSetChanged();
                        }
                        listView.setAdapter(adapter);
                    }
                    else {
                    Toast.makeText(getApplicationContext(),"Sorry,No data in Database.",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        }
        );
        requestQueue.add(stringRequest);
    }

    public void addData(String name,String code,String price,String details){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,addLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                showData();
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
        };
        requestQueue.add(stringRequest);
    }
    public void deleteData(String id){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,deleteLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Data deleted successfully.",Toast.LENGTH_SHORT).show();
                showData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Data delete failed",Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        }
        ){
            protected Map<String,String> getParams(){
                Map<String,String> par=new HashMap<String,String>();
                par.put("id",id);
                return par;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void productbtn1(View view) {
        ExampleDialogue exampleDialogue=new ExampleDialogue();
        exampleDialogue.show(getSupportFragmentManager(),"example dialog");
    }


}
