package com.example.admin.whatsappstatus;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 12-09-2018.
 */
public class Guj_StatusShow_Activity extends Fragment {

    String SelectStatus_URL = "http://gohilsandipsinhji00.000webhostapp.com/selectdatagujstatus.php";
    RecyclerView rv_status;
    Context context;
    FrameLayout fra;
    Status_Adapter adapter;
    List<Status_Model> list = new ArrayList<>();
    String status;
    TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.status_display_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_status=(RecyclerView)view.findViewById(R.id.rv_status);
        fra=(FrameLayout) view.findViewById(R.id.frameLayout2);

        final String id = getArguments().getString("id");
        Toast.makeText(getActivity(), "id="+id, Toast.LENGTH_SHORT).show();

        StringRequest sr=new StringRequest(Request.Method.POST, SelectStatus_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray arr = new JSONArray(response);
                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject obj = arr.getJSONObject(i);
                        status = obj.getString("status");
                        Log.e("Res", ">>>>>" + status);
                        final Status_Model sm=new Status_Model(status);
                        list.add(sm);


                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Server Busy...", Toast.LENGTH_SHORT).show();

                }

                adapter = new Status_Adapter(list);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                rv_status.setLayoutManager(mLayoutManager);
                rv_status.setItemAnimator(new DefaultItemAnimator());
                rv_status.setAdapter(adapter);

                onclick();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        sr.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(sr);




    }

    private void onclick() {
        rv_status.addOnItemTouchListener(new Status_RecyclerTouchListener(context,
                rv_status, new SOnClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String value= list.get(position).getStatus();
//
//                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
//                StatustextShow_Activity f=new StatustextShow_Activity();
//                Bundle b=new Bundle();
//                b.putString("status",value);
//                f.setArguments(b);
//                fragmentTransaction.replace(R.id.frameLayout2,f,null);
//                fragmentTransaction.addToBackStack(null).commit();

                Toast.makeText(getContext(),list.get(position).getStatus(),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(context, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
    }
}