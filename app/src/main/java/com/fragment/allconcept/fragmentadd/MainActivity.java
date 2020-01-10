package com.fragment.allconcept.fragmentadd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.fragment.allconcept.R;


public class MainActivity extends AppCompatActivity {

    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = MainActivity.class.getSimpleName();
    private static final String TAG = ACTIVITY_NAME;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private Button buttonAddFragment;
    private TextView textViewFragmentCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddFragment = (Button)findViewById(R.id.buttonAddFragment);
        textViewFragmentCount = (TextView)findViewById(R.id.textViewFragmentCount);

        fragmentManager=getSupportFragmentManager();

        textViewFragmentCount.setText("Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textViewFragmentCount.setText("Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());
            }
        });

        Log.i(TAG,"Initial BackStackEntryCount: "+fragmentManager.getBackStackEntryCount());


        buttonAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addFragment(){
        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()){
            case 0: fragment = new SampleFragment(); break;
            case 1: fragment = new FragmentTwo();break;
            case 2: fragment = new FragmentThree(); break;
            default: fragment = new SampleFragment(); break;
        }
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment,"demofragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
