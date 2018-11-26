package com.example.siwar.fitwoman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign_up extends AppCompatActivity {
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnLogin = (Button) findViewById(R.id.email_sign_up_button);
        toIMC();
    }
    void toIMC(){


        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.siwar.fitwoman.IMC");

                        startActivity(intent);
                    }
                }
        );



    }
}
