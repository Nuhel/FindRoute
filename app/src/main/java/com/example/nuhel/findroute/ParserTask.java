package com.example.nuhel.findroute;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nuhel on 11/22/2017.
 */

public class ParserTask extends AsyncTask<Object, Integer, List<List<HashMap<String, String>>>> {

    private GoogleMap googleMap;

    @Override
    protected List<List<HashMap<String, String>>> doInBackground(
            Object... obj) {

        googleMap = (GoogleMap) obj[1];
        // TODO Auto-generated method stub
        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;
        try {
            jObject = new JSONObject((String) obj[0]);
            PathJSONParser parser = new PathJSONParser();
            routes = parser.parse(jObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
        ArrayList<LatLng> points = null;
        PolylineOptions polyLineOptions = null;

        // traversing through routes
        for (int i = 0; i < routes.size(); i++) {
            points = new ArrayList<LatLng>();
            polyLineOptions = new PolylineOptions();
            List<HashMap<String, String>> path = routes.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            polyLineOptions.addAll(points);
            polyLineOptions.width(4);
            polyLineOptions.color(Color.BLUE);
        }

        googleMap.addPolyline(polyLineOptions);

    }
}