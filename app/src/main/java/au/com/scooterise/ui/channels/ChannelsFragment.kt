package au.com.scooterise.ui.channels

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.ABCiview
import au.com.scooterise.MainActivity
import au.com.scooterise.R
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.kotlin.where
import io.realm.mongodb.sync.SyncConfiguration

class ChannelsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var realms : Realm
    private lateinit var recycler : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        realms = Realm.getDefaultInstance()

        val root = inflater.inflate(R.layout.fragment_channels, container, false) as ScrollView
        val channels = realms.where<Channel>().findAll().sort("id")
        Log.e("ChannelsFragment",channels.asJSON().toString())
        val recycler : RecyclerView = root.findViewById<RecyclerView>(R.id.channelsRecyclerview).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
x
            adapter = ChannelAdapter(channels)
            Log.d("ChannelsFragment",channels.size.toString())
        }






        return root
    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    internal class ChannelAdapter(data: OrderedRealmCollection<Channel>) : RealmRecyclerViewAdapter<Channel, ChannelAdapter.ChannelViewHolder?>(data, true) {

        internal inner class ChannelViewHolder(view: View,context : Context) : RecyclerView.ViewHolder(view) {
            var image: ImageView = view as ImageView
            val context=context

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
            return ChannelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_channels_recycler, parent, false),parent.context)
        }

        override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
            val obj: Channel? = getItem(position)
            holder.image.setOnClickListener{
                Toast.makeText(holder.context, obj?.name,200)
                System.out.println(obj?.name)
            }

            when (position) {
                0 -> {
                    holder.image.setImageResource(R.drawable.ic_abc)
                }
                1 -> {
                    holder.image.setImageResource(R.drawable.ic_abccomedy)
                }
                2 -> {
                    holder.image.setImageResource(R.drawable.ic_abcme)
                }
                3 -> {
                    holder.image.setImageResource(R.drawable.ic_abckids)
                }
                4 -> {
                    holder.image.setImageResource(R.drawable.ic_abcnews)
                }
                5 -> {
                    holder.image.setImageResource(R.drawable.ic_abcarts)
                }
                6 -> {
                    holder.image.setImageResource(R.drawable.ic_iviewpresents)
                }
                else -> { // Note the block
                    print("x is neither 1 nor 2")
                }
            }

        }
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