package com.example.stockmanaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
    ArrayList<String> arrayList;
    String showLink = "https://stockmanaging56.000webhostapp.com/phpApi/show.php";
    String addLink = "https://stockmanaging56.000webhostapp.com/addApi.php";
//    String url="https://stockmanaging56.000webhostapp.com/addApi.php";
    // String link="https://zara-business.herokuapp.com/flexi-load/read";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        listView=findViewById(R.id.listId);
        showData();
    }
    public void showData(){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,showLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    arrayList=new ArrayList<>();
                    ArrayAdapter adapter = new ArrayAdapter(ProductList.this,android.R.layout.simple_list_item_1,arrayList);
                    arrayList.clear();
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("response").equals("success")){
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo=jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("productName");
                            String code = jsonObject.getString("productCode");
                            String price = jsonObject.getString("sellPrice");
                            String details = jsonObject.getString("productDetails");
                            arrayList.add(id+"\n"+name+"\n"+code+"\n"+price+"\n"+details);
                        }
                        listView.setAdapter(adapter);
                    }
                    else {
                    Toast.makeText(getApplicationContext(),"Sorry,No data in Database.",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              //  Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Data add failed",Toast.LENGTH_SHORT).show();
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

    public void productbtn1(View view) {
        ExampleDialogue exampleDialogue=new ExampleDialogue();
        exampleDialogue.show(getSupportFragmentManager(),"example dialog");
    }


}
