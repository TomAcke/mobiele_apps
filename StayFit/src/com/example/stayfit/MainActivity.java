package com.example.stayfit;

import java.util.Locale;


import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
//import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
import android.widget.Chronometer;
//import android.app.Activity;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    
    long timeWhenStopped = 0;
    long timeWhenPaused = 0;
    long timeRun = 0;
    boolean paused = false;
    boolean cancel = true;

    //@SuppressWarnings("null")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
                
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void startExercise(View view) {
    	
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    	
        if(paused==false){
        
    	if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
    		Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
    		cancel=false;
    		}
    	else{
    		showGPSDisabledAlertToUser();
    		}
    	
        }
        
    	if (cancel == false){

        	findViewById(R.id.ButtonStop).setVisibility(View.VISIBLE);
        	findViewById(R.id.ButtonStart).setVisibility(View.INVISIBLE);
        	findViewById(R.id.ButtonPause).setVisibility(View.VISIBLE);
        	findViewById(R.id.ButtonResume).setVisibility(View.INVISIBLE);
    		
    		
	       if(paused == false)
	       {
	    	((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime()
	                +timeWhenStopped);
	       }
	       else
	       {
	    	   ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime()
	                   +timeWhenPaused);
	    	   
	    	   paused = false;
	    	   
	       }
       
       
       
       
	       ((Chronometer) findViewById(R.id.chronometer)).start();
    	}
    	
   	
    	
    }
    
    public void stopExercise(View view) {
    	
    	
    	
       
    	findViewById(R.id.ButtonStart).setVisibility(View.VISIBLE);
    	findViewById(R.id.ButtonStop).setVisibility(View.INVISIBLE);
    	findViewById(R.id.ButtonPause).setVisibility(View.INVISIBLE);


    	
    	timeWhenStopped = ((Chronometer) findViewById(R.id.chronometer)).getBase() - SystemClock.elapsedRealtime();
    	((Chronometer) findViewById(R.id.chronometer)).stop();
    	timeRun = timeWhenStopped;
    	timeWhenStopped = 0;
    	cancel = true;
    	
   	
    	
    }
    
    public void pauseExercise(View view){
    	
    	findViewById(R.id.ButtonStop).setVisibility(View.INVISIBLE);
    	findViewById(R.id.ButtonPause).setVisibility(View.INVISIBLE);
    	findViewById(R.id.ButtonResume).setVisibility(View.VISIBLE);

    	
    	timeWhenPaused = ((Chronometer) findViewById(R.id.chronometer)).getBase() - SystemClock.elapsedRealtime();
    	((Chronometer) findViewById(R.id.chronometer)).stop();
    	
    	paused = true;
    	
    }
    
    public void showList(View view){
    	findViewById(R.id.ButtonShowList).setVisibility(View.INVISIBLE);
    	Intent i = new Intent(getApplicationContext(), AllTrainingsActivity.class);
    	startActivity(i);
    }
    
    /**
     * Method to check if the gps is enabled, if not the user gets prompted to enable
     */
    
    private void showGPSDisabledAlertToUser(){
    	
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    	alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
    	.setCancelable(false)
    	.setPositiveButton("Enable GPS",
    	new DialogInterface.OnClickListener(){
    		
	    	public void onClick(DialogInterface dialog, int id){
		    	cancel = false;
		    	Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		    	startActivity(callGPSSettingIntent);
	    	
	    	}
    	
    	});
    	
    	alertDialogBuilder.setNegativeButton("Cancel",
	    new DialogInterface.OnClickListener(){
	    	public void onClick(DialogInterface dialog, int id){
		    	cancel = true;
		    	dialog.cancel();
		    	
		    	
		    	
	    	}
    	
    	});
    	
    	AlertDialog alert = alertDialogBuilder.create();
    	alert.show();
    	}

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a SectionFragment (defined as a static inner class
            // below) with a matching layout.
        switch (position) {
            case 0:
            	
                Fragment fragment = new TrainingSectionFragment();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;

              case 1:
                Fragment fragment1 = new GeschiedenisSectionFragment();
                Bundle args1 = new Bundle();
                fragment1.setArguments(args1);
                return fragment1;

              /*case 2:

            	  Fragment fragment3 = new InformatieSectionFragment();
                  Bundle args3 = new Bundle();
                  fragment3.setArguments(args3);
                  return fragment3;
                 
              /*case 3:

                  Fragment fragment3 = new InformatieSectionFragment();
                  Bundle args3 = new Bundle();
                  fragment3.setArguments(args3);
                  return fragment3;*/


            default:
                return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total tabs.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                /*case 2:
                    return getString(R.string.title_section3).toUpperCase(l);*/
                /*case 2:
                    return getString(R.string.title_section4).toUpperCase(l);*/
            }
            return null;
        }
    }

    
    public static class TrainingSectionFragment extends Fragment {
    	
        public TrainingSectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.training_main, container, false);
            return rootView;
        }
    }
    
    public static class GeschiedenisSectionFragment extends Fragment {

        public GeschiedenisSectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	//laad de juiste activity zodra geschiendenis geladen wordt //getApplicationContext
//        	Intent i = new Intent(getActivity(), AllTrainingsActivity.class);
//            startActivity(i);
        	
            //laad de juiste layout zodra geschiedenis geladen wordt
            View rootView = inflater.inflate(R.layout.geschiedenis_main, container, false);
            return rootView;
            
        }
    }
    
    /*public static class KaartSectionFragment extends Fragment {

        public KaartSectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.kaart_main, container, false);
            return rootView;
        }
    }
    
    public static class InformatieSectionFragment extends Fragment {

        public InformatieSectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.informatie_main, container, false);
            return rootView;
        }
    }*/

}
