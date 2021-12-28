package com.example.stockmanaging;

import static android.R.layout.simple_list_item_1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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

public class ExampleDialogue extends AppCompatDialogFragment {
     EditText pName,pCode,pPrice,pDetails;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialogue,null);

        builder.setView(view)
                .setTitle("Add Product")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=pName.getText().toString();
                        String pcode=pCode.getText().toString();
                        String pprice=pPrice.getText().toString();
                        String pdetails=pDetails.getText().toString();
//                        ProductList productList=new ProductList();
//                        productList.addData(name,pcode,pprice,pdetails);
                    ((ProductList)getActivity()).addData(name,pcode,pprice,pdetails);
                    //   ((ProductList)getActivity()).showData();
              //calling subclass
//                        ProductList productList=new ProductList();
//                        ProductList.myData productListdata=productList.new myData();
//                        productListdata.execute();

                    }
                });
        pName=view.findViewById(R.id.pnameid);
        pCode=view.findViewById(R.id.pcodeid);
        pPrice=view.findViewById(R.id.ppriceid);
        pDetails=view.findViewById(R.id.pdetailsid);
        return builder.create();
    }



}
