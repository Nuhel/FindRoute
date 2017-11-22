package com.example.nuhel.findroute;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nuhel on 11/22/2017.
 */

public class MapHttpConnection {

    private Context context;

    public MapHttpConnection(Context context) {
        this.context = context;
    }


    public String readUr(String mapsApiDirectionsUrl) throws IOException {
        String data = "";
        InputStream istream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mapsApiDirectionsUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            istream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(istream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Toast.makeText(context, "" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            istream.close();
            urlConnection.disconnect();
        }
        return data;

    }

}
