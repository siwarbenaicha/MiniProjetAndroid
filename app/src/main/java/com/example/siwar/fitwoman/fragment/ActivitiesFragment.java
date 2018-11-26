package com.example.siwar.fitwoman.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.siwar.fitwoman.MarwaAdd;
import com.example.siwar.fitwoman.R;
import com.example.siwar.fitwoman.adapter.ActivityAdapter;

import com.example.siwar.fitwoman.model.MActivity;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class ActivitiesFragment extends Fragment {
    Button btnAdd;
ImageView imgAdd;
    ListView lv;
    List<MActivity> lstcc;
    private static final String URL_Activities = "http://192.168.64.2/FitWomanServices/MgetActivityTest.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activities, container, false);


        lv =  v.findViewById(R.id.MlistView1);

        lstcc = new ArrayList<>();
        loadActivities();
        //final ActivityAdapter adapter = new ActivityAdapter(getContext() ,R.layout.activities_row,lstcc );

        //lv.setAdapter(adapter);


       imgAdd = v.findViewById(R.id.MimageView7);
       imgAdd.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(getContext(), MarwaAdd.class);
                       i.putExtra("userName", "test1");
                       i.putExtra("userEmail", "test1");

                       startActivity(i);
                   }
               }
       );

        return v;
    }

    protected FragmentActivity getActivityNonNull() {
        if (super.getActivity() != null) {
            return super.getActivity();
        } else {
            throw new RuntimeException("null returned from getActivity()");
        }
    }

    private void loadActivities() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Activities,
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
                                lstcc.add(new MActivity(

                                        product.getString("name"),

                                        product.getString("duration"),
                                        product.getInt("burnedCalories")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ActivityAdapter adapter = new ActivityAdapter(getContext() ,R.layout.activities_row,lstcc );
                            lv.setAdapter(adapter);
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
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

}