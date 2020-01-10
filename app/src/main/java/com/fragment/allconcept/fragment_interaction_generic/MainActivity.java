package com.fragment.allconcept.fragment_interaction_generic;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fragment.allconcept.R;


public class MainActivity extends AppCompatActivity implements FragmentActionListener{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();
    }

    private void addCountriesFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountriesFragment countryListFragment=new CountriesFragment();
        countryListFragment.setFragmentActionListener(this);

        fragmentTransaction.add(R.id.fragmentContainer,countryListFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onActionPerformed(Bundle bundle) {
        int actionPerformed = bundle.getInt(FragmentActionListener.ACTION_KEY);
        switch (actionPerformed){
            case FragmentActionListener.ACTION_VALUE_COUNTRY_SELECTED: addCountryDescriptionFragment(bundle);
            break;
        }
    }


    private void addCountryDescriptionFragment(Bundle bundle){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment=new CountryDescriptionFragment();
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
