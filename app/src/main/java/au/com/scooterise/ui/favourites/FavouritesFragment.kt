package au.com.scooterise.ui.favourites

import android.content.Context
import androidx.fragment.app.Fragment
import au.com.scooterise.MainActivity

class FavouritesFragment : Fragment(){
    override fun onStart() {
        super.onStart()
        MainActivity.currentFragment= MainActivity.FragmentID.FavouritesFragment
    }
}