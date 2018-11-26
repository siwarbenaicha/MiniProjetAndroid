package com.example.siwar.fitwoman;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.siwar.fitwoman.model.MActivity;
import com.example.siwar.fitwoman.service.MySingleton;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
 EditText txtEmail, txtPwd;
 Button btnLogin;
 TextView txtSignUp;


private LoginButton loginButton;
private CallbackManager callbackManager;
     String FBEmail = "email";
    String FBName = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////
        callbackManager = CallbackManager.Factory.create();
        if(AccessToken.getCurrentAccessToken() != null){
            Intent intent = new Intent("com.example.siwar.fitwoman.Home");

            startActivity(intent);
        }
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(FBEmail));
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ///////////////
                MarwaFBLogin(loginResult);

            }

            @Override
            public void onCancel() {
               Toast.makeText(getApplicationContext(),"u canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"fixe ur error before", Toast.LENGTH_SHORT).show();
            }


        });
        ///////////////
        //Solving thread problem
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////
        txtEmail= findViewById(R.id.MName);
        txtPwd= findViewById(R.id.MEmail);
        btnLogin =  findViewById(R.id.button);
        txtSignUp = findViewById(R.id.signUpTextView);





        LoginAndGoToIMC();
        goToSignUp();
    }

    //For FB Login
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    void  goToSignUp(){
        txtSignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Sign_up.class);

                        startActivity(intent);
                    }
                }
        );

    }


    void LoginAndGoToIMC(){
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        InsertUserInDB(txtEmail.getText().toString(), txtPwd.getText().toString() , "AndroidApp");
                    }
                }
        );

    }


    public void MarwaFBLogin(LoginResult loginResult){
        AccessToken accessToken = loginResult.getAccessToken();
        Profile profile = Profile.getCurrentProfile();

        // Facebook Email address
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Log.v("LoginActivity Response ", response.toString());

                        try {
                            FBName = object.getString("name");

                            FBEmail = object.getString("email");
                            Log.v("Email = ", " " + FBEmail);
                            InsertUserInDB(FBName , FBEmail , "FB");

                            //Toast.makeText(getApplicationContext(), "Name " + FBName+ " email "+ FBEmail, Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();

    }


    public void InsertUserInDB(final String UserName, final String UserEmail , final String loginType){
        final String   URL = "http://192.168.64.2/FitWomanServices/addUser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("success")) {
                    // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    ////////////////////////////////////
                    Intent intent = new Intent("com.example.siwar.fitwoman.IMC");
                    intent.putExtra("userName", UserName); //this 2 lines for pass the values to the next activity
                    intent.putExtra("userEmail", UserEmail);
                    startActivity(intent);
                    ////////////////////
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
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("name", UserName);
                params.put("email",UserEmail);
                params.put("logintype", loginType);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
