package com.bok.onnal.fragment;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.bok.onnal.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
