package com.example.nuhel.findroute;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Nuhel on 11/22/2017.
 */

public class ReadTask extends AsyncTask<Object, Object, String> {


    private String url;
    private Context context;
    private GoogleMap googleMap;

    @Override
    protected String doInBackground(Object... obj) {
        // TODO Auto-generated method stub
        String data = "";

        url = (String) obj[0];
        context = (Context) obj[1];

        googleMap = (GoogleMap) obj[2];

        try {
            MapHttpConnection http = new MapHttpConnection(context);
            data = http.readUr(url);


        } catch (Exception e) {
            // TODO: handle exception
            Log.d("Background Task", e.toString());
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();

        Object obj[] = new Object[2];

        obj[0] = result;
        obj[1] = googleMap;

        new ParserTask().execute(obj);
    }

}