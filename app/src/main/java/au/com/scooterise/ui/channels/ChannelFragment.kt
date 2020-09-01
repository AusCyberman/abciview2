package au.com.scooterise.ui.channels

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
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


class ChannelFragment : Fragment() {

    val args: ChannelFragmentArgs by navArgs()
    lateinit var realms : Realm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_channel, container, false)
        when (args.channel) {
            0 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abc)
            1 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abccomedy)
            2 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abcme)
            3 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abckids)
            4 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abcnews)
            5 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_abcarts)
            6 -> root.findViewById<ImageView>(R.id.channelImage).setImageResource(R.drawable.ic_iviewpresents)
        }



        realms= Realm.getDefaultInstance()
        val adapterData : LinkedHashMap<String?, RealmResults<Content>> = LinkedHashMap()

        var categoriesIntpre : ArrayList<Long> = ArrayList()
        realms.where<Content>().equalTo("channel",args.channel).findAll().forEach {
            if(!categoriesIntpre.contains(it.category)){
                it.category?.let { it1 -> categoriesIntpre.add(it1) }}
        }

        var categories : RealmResults<Category>? = realms.where<Category>().`in`("id",categoriesIntpre.toTypedArray() ).findAll().sort("id")
        categories?.forEach {
            adapterData.put(it.Name,realms.where<Content>().equalTo("channel",args.channel).equalTo("category",it.id).findAll())
        }

        val adapter = ContentSectionAdapter(adapterData)
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        root.findViewById<ImageView>(R.id.channel_back).apply {  setColorFilter(Color.WHITE)
            setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
        var recycler : RecyclerView = root.findViewById<RecyclerView>(R.id.channel_content_recycler).apply {
            this.adapter=adapter
            this.layoutManager=layoutManager

        }
        return root
    }

    override fun onStart() {
        super.onStart()
        MainActivity.currentFragment= MainActivity.FragmentID.ChannelFragment
    }


}