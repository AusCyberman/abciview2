package au.com.scooterise.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import au.com.scooterise.MainActivity
import au.com.scooterise.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.currentFragment=MainActivity.FragmentID.SettingsFragment
    }
}