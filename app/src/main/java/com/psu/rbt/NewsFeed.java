package com.psu.rbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

 
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class NewsFeed extends Fragment {
	
	
 
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.activitie_newsfeed, container, false);
       if (savedInstanceState == null) {
            addRssFragment();
       }
        
         
        return rootView;
    }
   private void addRssFragment() {
       FragmentManager manager = getActivity().getSupportFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();
       RssFragment fragment = new RssFragment();
       transaction.add(R.id.fragment_container, fragment);
       transaction.commit();
   }

   @Override
	public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
       outState.putBoolean("fragment_added", true);
   }
   


}
