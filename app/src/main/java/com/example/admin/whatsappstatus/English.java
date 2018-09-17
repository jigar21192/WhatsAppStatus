package com.example.admin.whatsappstatus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 05-09-2018.
 */
public class English extends Fragment {
    String url="https://www.sabkuchhbechde.ga/android/Whatsapp_Status/Select_Category.php";
    List<Category_Model> cmlist=new ArrayList<>();
    Category_Adapter adapter;
    FrameLayout fra;
    RecyclerView crv;
    String c_id,category;


//    EnglishDataSenderInterFace send;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.english_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fra=(FrameLayout) view.findViewById(R.id.frameLayout);
        crv=(RecyclerView)view.findViewById(R.id.rv_status_category);

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);

                    for (int i = 0; i <array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        c_id=object1.getString("c_id");
                        category=object1.getString("category");

                        final Category_Model cm=new Category_Model(c_id,category);
                        cmlist.add(cm);


                    }

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Server Busy...", Toast.LENGTH_SHORT).show();
                }


                adapter = new Category_Adapter(cmlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                crv.setLayoutManager(mLayoutManager);
                crv.setItemAnimator(new DefaultItemAnimator());
                crv.setAdapter(adapter);


                onclick();




            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);


    }

    private void onclick() {
        crv.addOnItemTouchListener(new Category_RecyclerTouchListener(getActivity(),
                crv, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String value= cmlist.get(position).getC_id();

                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                StatusShow_Activity f=new StatusShow_Activity();
                Bundle b=new Bundle();
                b.putString("id",value);
                f.setArguments(b);
                fragmentTransaction.replace(R.id.frameLayout,f,null);
                fragmentTransaction.addToBackStack(null).commit();


//                send.EnglishsendData(value);

                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(),cmlist.get(position).getCategory().toString(),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
    }

//    public interface EnglishDataSenderInterFace {
//        void EnglishsendData(String msg);
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            send = (EnglishDataSenderInterFace) getActivity();
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Error in retrieving data. Please try again");
//        }
//    }
}
