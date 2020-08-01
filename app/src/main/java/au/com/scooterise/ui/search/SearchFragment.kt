package au.com.scooterise.ui.search

import android.content.Context
import androidx.fragment.app.Fragment
import au.com.scooterise.MainActivity

class SearchFragment : Fragment() {
    override fun onStart() {
        super.onStart()
        MainActivity.currentFragment= MainActivity.FragmentID.SearchFragment
    }
}