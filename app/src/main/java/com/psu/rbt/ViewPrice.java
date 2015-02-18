package com.psu.rbt;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ViewPrice extends Fragment {
	private Button cal_price_btn;
	private WebView webView;
	
	private TextView priceWater ;
	private TextView priceSheet ;
	private TextView priceLump ;
	private TextView priceScrap ;
	private String price_Water,price_Sheet,price_Lump,price_Scrap,pWater,pSheet,pLump,pScrap,ID,date;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activitie_viewprice, container, false);
			
			if (android.os.Build.VERSION.SDK_INT > 9) {
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy);
	        }
			
			priceWater = (TextView) rootView.findViewById(R.id.price_water);
			priceSheet = (TextView) rootView.findViewById(R.id.price_sheet);
			priceLump = (TextView) rootView.findViewById(R.id.price_lump);
			priceScrap = (TextView) rootView.findViewById(R.id.price_scrap);
			
			
			String url = "http://www.anurakam.comoj.com/getJSON.php";
			try {
			JSONArray data = new JSONArray(getJSONUrl(url));
			
			final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			
			for(int i = 0; i < data.length(); i++){
               JSONObject c = data.getJSONObject(i);
               
   			map = new HashMap<String, String>();
   			map.put("ID", c.getString("ID"));
   			map.put("date", c.getString("date"));
   			map.put("pWater", c.getString("pWater"));
   			map.put("pSheet", c.getString("pSheet"));
   			map.put("pLump", c.getString("pLump"));
   			map.put("pScrap", c.getString("pScrap"));
   			MyArrList.add(map);
			}
			ID = MyArrList.get(0).get("ID") 
					.toString();
			date = MyArrList.get(0).get("date") 
					.toString();
			pWater = MyArrList.get(0).get("pWater") 
					.toString();
			pSheet = MyArrList.get(0).get("pSheet")
					.toString();
			pLump = MyArrList.get(0).get("pLump")
					.toString();
			pScrap = MyArrList.get(0).get("pScrap")
					.toString();
			
			priceWater.setText(pWater);
			priceSheet.setText(pSheet);
			priceLump.setText(pLump);
			priceScrap.setText(pScrap);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			price_Water = priceWater.getText().toString();
			price_Sheet = priceSheet.getText().toString();
			price_Lump = priceLump.getText().toString();
			price_Scrap = priceScrap.getText().toString();
         cal_price_btn = (Button) rootView.findViewById(R.id.Cal_Price_btn);
         cal_price_btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(getActivity(), CalculatePrice.class);
					 intent.putExtra("price_Water", price_Water);
					 intent.putExtra("price_Sheet", price_Sheet);
					 intent.putExtra("price_Lump", price_Lump);
					 intent.putExtra("price_Scrap", price_Scrap);
					 startActivity(intent);

						
				}});
         webView = (WebView) rootView.findViewById(R.id.webView1);
         webView.setInitialScale(1);
         webView.getSettings().setJavaScriptEnabled(true);
         webView.getSettings().setLoadWithOverviewMode(true);
         webView.getSettings().setUseWideViewPort(true);
         webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
         webView.setScrollbarFadingEnabled(false);
         // โหลดเว็บเพจ
         webView.loadUrl("http://www.rubber.co.th/ewtadmin/ewt/rubber_eng/rubber_price_today/rubber_price_movement.php");

        return rootView;
        
		    }

	public String getJSONUrl(String url) {
		StringBuilder str = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		try {
			//httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) { // Download OK
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}
			} else {
				Log.e("Log", "Failed to download file..");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
        
}


