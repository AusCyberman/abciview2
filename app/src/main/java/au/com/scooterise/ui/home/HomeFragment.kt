package au.com.scooterise.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.MainActivity
import au.com.scooterise.R
import au.com.scooterise.ui.channels.Channel
import au.com.scooterise.ui.content.Category
import au.com.scooterise.ui.content.Content
import au.com.scooterise.ui.content.ContentSectionAdapter
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class HomeFragment : Fragment() {
    lateinit var realms : Realm
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : ConstraintLayout = inflater.inflate(R.layout.fragment_home, container, false) as ConstraintLayout
        realms= Realm.getDefaultInstance()
        val adapterData : LinkedHashMap<String?,RealmResults<Content>> = LinkedHashMap()
        var categories : RealmResults<Category>? = realms.where<Category>().findAll().sort("id")

        categories?.forEach {
            adapterData.put(it.Name,realms.where<Content>().equalTo("category",it.id).findAll())
        }

        val adapter : ContentSectionAdapter = ContentSectionAdapter(adapterData)
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.content_category_recycler).apply {
            this.adapter=adapter
            this.layoutManager=layoutManager

        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if(realms != null) {
            realms.close();

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        if(realms != null) {
            realms.close();

        }
    }


}
