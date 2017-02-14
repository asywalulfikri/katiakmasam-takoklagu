package asywalul.tebaklaguband.android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView username,url,type,repo,producer,production,create_date,milesstone,name,description,signalfrom;
    private String api = "https://api.duckduckgo.com/q=senin&format=json&pretty=1?ia=web";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username    = (TextView)findViewById(R.id.username);
        url         = (TextView)findViewById(R.id.url);
        repo        = (TextView)findViewById(R.id.repo);
        type        = (TextView)findViewById(R.id.type);
        producer    = (TextView)findViewById(R.id.producer);
        production  = (TextView)findViewById(R.id.production);
        create_date = (TextView)findViewById(R.id.create_date);
        milesstone  = (TextView)findViewById(R.id.milesstone);
        name        = (TextView)findViewById(R.id.name);
        description = (TextView)findViewById(R.id.description);
        signalfrom  = (TextView)findViewById(R.id.signal_from);


        loadTask();

    }


    private void loadTask(){
        showDialog();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.e("respon",String.valueOf(response));
                try {

                    JSONObject object = response.getJSONObject("meta");
                    String production_state = object.getString("production_state");
                    String signal_from = object.getString("signal_from");
                    String repo = object.getString("repo");

                    JSONArray jsonArray = object.getJSONArray("developer");
                    JSONObject itemJson = jsonArray.getJSONObject(0);

                    String username = itemJson.getString("name");
                    String urlgithub= itemJson.getString("url");
                    String type     = itemJson.getString("type");

                    String producer = object.getString("producer");
                    String milesstone = object.getString("dev_milestone");
                    String name     = object.getString("name");
                    String create   = object.getString("created_date");
                    String descrip   = object.getString("description");










                    hideDialog();

                    displayView(production_state,signal_from,repo,username,urlgithub,type,producer,milesstone,name,create,descrip);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               // mAdapter.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.e("ListItem", "Error" + error.getMessage());
//                handleVolleyError(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    private void showDialog() {
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Mengambil data");
        mDialog.setCancelable(false);
        mDialog.show();
    }

    private void hideDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog.cancel();
        }
    }

    public void displayView(String ps,String sf,String repos,String usernam,String urlgit,String typ,String produc,String miles,String nama,String crea,String des){
        production.setText("production_state  = "+ps);
        signalfrom.setText("signal_from = "+sf);
        repo.setText("repo = " +repos);
        username.setText("name = "+usernam);
        url.setText("url = "+urlgit);
        type.setText("type = "+typ);
        producer.setText("producer = "+produc);
        milesstone.setText("miles_stone = "+miles);
        name.setText("name ="+nama);
        description.setText("description =" +des );
        create_date.setText("create = " +crea);

    }

}
