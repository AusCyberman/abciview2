package au.com.scooterise.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.MainActivity
import au.com.scooterise.R
import au.com.scooterise.ui.content.Category
import au.com.scooterise.ui.content.Content
import au.com.scooterise.ui.content.ContentSectionAdapter
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class HomeFragment : Fragment() {
    var scrollX : Int? = null
    lateinit var view : NestedScrollView
    lateinit var realms : Realm
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false) as NestedScrollView

        realms= Realm.getDefaultInstance()
        val adapterData : LinkedHashMap<String?,RealmResults<Content>> = LinkedHashMap()

        var categoriesIntpre : ArrayList<Long> = ArrayList()
        realms.where<Content>().findAll().forEach {
            if(!categoriesIntpre.contains(it.category)){
            it.category?.let { it1 -> categoriesIntpre.add(it1) }}
        }


        var categories : RealmResults<Category>? = realms.where<Category>().`in`("id",categoriesIntpre.toTypedArray() ).findAll().sort("id")
        categories?.forEach {
            adapterData.put(it.Name,realms.where<Content>().equalTo("category",it.id).findAll())
        }

        val adapter : ContentSectionAdapter = ContentSectionAdapter(adapterData)
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        var recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.content_category_recycler).apply {
            this.adapter=adapter
            this.layoutManager=layoutManager

        }
        return view
    }

    override fun onStart() {
        super.onStart()
        MainActivity.currentFragment=MainActivity.FragmentID.HomeFragment
    }

    override fun onResume() {
        super.onResume()
        if(scrollX!=null&& this::view.isInitialized) view.scrollX= this.scrollX!!
    }
    override fun onStop() {
        super.onStop()
        scrollX=view.scrollX
    }

}
