package com.example.siwar.fitwoman;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siwar.fitwoman.adapter.ActivityAdapter;
import com.example.siwar.fitwoman.model.MActivity;
import com.example.siwar.fitwoman.service.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MarwaAdd extends AppCompatActivity {
Button btnAddActivity;
EditText Aname , Aday , Aduration , Adesc;
Integer idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marwa_add);


       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Activity");

        String userName = this.getIntent().getStringExtra("userName");
        String userEmail = this.getIntent().getStringExtra("userEmail");

        //find user by email

        Aname = findViewById(R.id.MeditText5);
        Aday = findViewById(R.id.MeditText6);
        Aduration = findViewById(R.id.MeditText7);
        Adesc = findViewById(R.id.MeditText8);
        btnAddActivity = findViewById(R.id.Mbutton2);

        BtnToAddActivity();



    }
    public void BtnToAddActivity(){
        btnAddActivity.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WSAddActivity();


                    }
                }
        );
    }
public void getUserIdByEmail(){
    final String   URL = "http://192.168.64.2/FitWomanServices/getUserByEmail.php";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject(i);

                            //adding the product to product list


                               idUser = product.getInt("id");


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

    //adding our stringrequest to queue
    Volley.newRequestQueue(this).add(stringRequest);

}
public void WSAddActivity(){

    final String   URL = "http://192.168.64.2/FitWomanServices/MaddActivity.php";





    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if(response.contains("success")) {
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"failed to login",Toast.LENGTH_SHORT).show();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            params.put("Content-Type","application/x-www-form-urlencoded");
            params.put("name", Aname.getText().toString());
            params.put("day", date);
            params.put("duration", Aduration.getText().toString());
            params.put("description", Adesc.getText().toString());
            params.put("idUser", String.valueOf(idUser));

            return params;
        }
    };

    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


}
}
