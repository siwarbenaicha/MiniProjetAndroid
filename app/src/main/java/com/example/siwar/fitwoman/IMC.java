package com.example.siwar.fitwoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.siwar.fitwoman.service.MySingleton;

import java.util.HashMap;
import java.util.Map;

public class IMC extends AppCompatActivity {
    TextView txtUserName;
    EditText txtWeight, txtHeight ,txtAge ;
    ImageView imgNext;
    String userName;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        txtUserName = findViewById(R.id.MtextView);
        txtWeight =findViewById(R.id.MeditText);
        txtHeight =findViewById(R.id.MeditText2);
        txtAge =findViewById(R.id.MeditText3);
        imgNext = findViewById(R.id.MimageView);

       userName = this.getIntent().getStringExtra("userName");
        userEmail = this.getIntent().getStringExtra("userEmail");
        goToImcResult();

        txtUserName.setText(userName);

    }
    void goToImcResult(){
        imgNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String   URL = "http://192.168.64.2/FitWomanServices/updateUser.php";





                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.contains("success")) {
                                    Intent intent = new Intent("com.example.siwar.fitwoman.Home");
                                    intent.putExtra("userName", userName); //this 2 lines for pass the values to the next activity
                                    intent.putExtra("userEmail", userEmail);
                                    startActivity(intent);

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"failed to update",Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("Content-Type","application/x-www-form-urlencoded");

                                params.put("email", userEmail);

                                params.put("lastWeight", txtWeight.getText().toString());
                                params.put("height", txtHeight.getText().toString());
                                params.put("age", txtAge.getText().toString());
                                params.put("mesureUnit", "metric");
                                return params;
                            }
                        };

                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



                    }
                }
        );
    }
}
