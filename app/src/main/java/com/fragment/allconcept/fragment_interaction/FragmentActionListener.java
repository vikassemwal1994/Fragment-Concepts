package com.fragment.allconcept.fragment_interaction;

import android.os.Bundle;

public interface FragmentActionListener {

    String KEY_SELECTED_COUNTRY="KEY_SELECTED_COUNTRY";

    void onCountrySelected(String country);
}
