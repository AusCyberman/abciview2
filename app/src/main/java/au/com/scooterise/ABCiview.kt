package au.com.scooterise

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User

lateinit var taskApp: App

// global Kotlin extension that resolves to the short version
// of the name of the current class. Used for labelling logs.
inline fun <reified T> T.TAG(): String = T::class.java.simpleName

/*
* TaskTracker: Sets up the taskApp Realm App and enables Realm-specific logging in debug mode.
*/
class ABCiview : Application() {
    var user: User? = null
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        taskApp = App(
            AppConfiguration.Builder("abciview-tswhr")
                .build())

        // Enable more logging in debug mode
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }

        Log.v(TAG(), "Initialized the Realm App configuration for: ${taskApp.configuration.appId}")
        val anonymousCredentials: Credentials = Credentials.anonymous()


        taskApp.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {
                Log.v(TAG(), "Successfully authenticated anonymously.")
                user = taskApp.currentUser()
            } else {
                Log.e(TAG(), it.error.toString())
            }
        }
    }
}