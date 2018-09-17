package com.example.admin.whatsappstatus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 05-09-2018.
 */
public class Gujrati extends Fragment {
    String url="http://gohilsandipsinhji00.000webhostapp.com/selectdataguj.php";
    List<Category_Model> cmlist=new ArrayList<>();
    Category_Adapter adapter;
    FrameLayout fra;
    RecyclerView crv;
    String c_id,category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gujati_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        TextView textView = (TextView)view.findViewById(R.id.textView4);
            fra=(FrameLayout)view.findViewById(R.id.gujframeLayout);
            crv=(RecyclerView)view.findViewById(R.id.gujrv_status_category);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);

                    for (int i = 0; i <array.length(); i++) {

                        JSONObject object1 = array.getJSONObject(i);

                        c_id=object1.getString("c_id");
                        category=object1.getString("c_name");

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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(stringRequest);
    }

    private void onclick() {
        crv.addOnItemTouchListener(new Category_RecyclerTouchListener(getActivity(),
                crv, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String value= cmlist.get(position).getC_id();



                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                Guj_StatusShow_Activity f1=new Guj_StatusShow_Activity();
                Bundle b=new Bundle();
                b.putString("id",value);
                f1.setArguments(b);
                fragmentTransaction.replace(R.id.gujframeLayout,f1,null);
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


}
