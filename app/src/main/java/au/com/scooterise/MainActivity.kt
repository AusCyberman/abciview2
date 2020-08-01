package au.com.scooterise

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.ui.channels.Channel
import au.com.scooterise.ui.channels.ChannelsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.realm.Realm
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.Document

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
