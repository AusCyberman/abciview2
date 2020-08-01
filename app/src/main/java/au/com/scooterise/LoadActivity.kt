package au.com.scooterise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import au.com.scooterise.ABCiview.Companion.taskApp

import io.realm.Realm
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

class LoadActivity : AppCompatActivity() {

        lateinit var realm : Realm
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        val anonymousCredentials: Credentials = Credentials.anonymous()


        taskApp.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {
                Log.v("Logging in", "Successfully authenticated anonymously.")
                user = taskApp.currentUser()
                val config = SyncConfiguration.Builder(user, "partition")
                    .waitForInitialRemoteData()
                    .build()

                // save this configuration as the default for this entire app so other activities and threads can open their own realm instances
                Realm.setDefaultConfiguration(config)

                // Sync all realm changes via a new instance, and when that instance has been successfully created connect it to an on-screen list (a recycler view)
                Realm.getInstanceAsync(config, object: Realm.Callback() {
                    override fun onSuccess(realm: Realm) {
                        // since this realm should live exactly as long as this activity, assign the realm to a member variable
                        this@LoadActivity.realm = realm

                        startActivity(Intent(baseContext,MainActivity::class.java))
                    }
                })
            } else {
                Log.e("Logging in", it.error.toString())
            }
        }

    }
}