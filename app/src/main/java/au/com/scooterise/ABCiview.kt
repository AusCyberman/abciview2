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



// global Kotlin extension that resolves to the short version
// of the name of the current class. Used for labelling logs.
inline fun <reified T> T.TAG(): String = T::class.java.simpleName


class ABCiview : Application() {
    companion object {
        lateinit var taskApp: App

    }
    var user: User? = null
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        taskApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .build())

        // Enable more logging in debug mode
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }

        Log.v(TAG(), "Initialized the Realm App configuration for: ${taskApp.configuration.appId}")
    }
}