package au.com.scooterise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        // configure realm to use the current user and the partition corresponding to "My Project"

    }

    companion object{
        var currentFragment :FragmentID = FragmentID.HomeFragment
    }
enum class FragmentID {
    HomeFragment,ChannelFragment,SearchFragment,FavouritesFragment,SettingsFragment,ContentFragment,ChannelsFragment
}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

    }

}
