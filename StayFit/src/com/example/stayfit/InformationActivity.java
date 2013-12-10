package com.example.stayfit;

import java.util.Locale;

//import com.example.stayfit.MainActivity.GeschiedenisSectionFragment;
//import com.example.stayfit.MainActivity.TrainingSectionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
//import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.TextView;

public class InformationActivity extends FragmentActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informatie_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

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
            	
                Fragment fragment = new GeneralSectionFragment();
                Bundle args = new Bundle();
                fragment.setArguments(args);
                return fragment;

              case 1:
                Fragment fragment1 = new StartToRunSectionFragment();
                Bundle args1 = new Bundle();
                fragment1.setArguments(args1);
                return fragment1;


            default:
                return null;
            }
        }

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section3).toUpperCase(l);
			case 1:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}


	/*
	 * 
	 * These sections determine which layout has to be schow for each slider tab
	 * 
	 */
	
	public static class GeneralSectionFragment extends Fragment {

		

		public GeneralSectionFragment() {
		}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
			View rootView = inflater.inflate(R.layout.informatie_main, container, false);
			return rootView;
        
			
		}
	}
	
	public static class StartToRunSectionFragment extends Fragment {

		

		public StartToRunSectionFragment() {
		}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
			View rootView = inflater.inflate(R.layout.informatie_main, container, false);
			return rootView;
        
			
		}
	}

}
