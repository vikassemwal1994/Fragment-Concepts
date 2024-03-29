package com.fragment.allconcept.fragment_config_change;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fragment.allconcept.R;

import java.lang.ref.WeakReference;


public class CountriesFragment extends Fragment {

    private static final String COMMON_TAG = "OrintationChange";
    private static final String FRAGMENT_NAME = CountriesFragment.class.getSimpleName();

    View rootView;

    ListView listViewCountries;
    ArrayAdapter<String> countryNamesAdapter;
    Context context;
    String [] countries;

    FragmentActionListener fragmentActionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_countries,container,false);
        initUI();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(COMMON_TAG,"CountriesFragment onSaveInstanceState");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name)+"->Select Country");
    }

    public void setFragmentActionListener(FragmentActionListener fragmentActionListener){
        this.fragmentActionListener = fragmentActionListener;
    }

    private void initUI(){
        context  = getContext();
        countries = getResources().getStringArray(R.array.countries);
        listViewCountries = (ListView)rootView.findViewById(R.id.listViewCountries);

        countryNamesAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,countries);
        listViewCountries.setAdapter(countryNamesAdapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fragmentActionListener!=null){
                    fragmentActionListener.onCountrySelected(countries[i]);
                }
            }
        });
    }
}
