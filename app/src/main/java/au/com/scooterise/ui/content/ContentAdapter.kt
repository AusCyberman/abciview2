package au.com.scooterise.ui.content

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.MainActivity
import au.com.scooterise.R
import au.com.scooterise.ui.channels.Channel
import au.com.scooterise.ui.channels.ChannelFragmentArgs
import au.com.scooterise.ui.channels.ChannelFragmentDirections
import au.com.scooterise.ui.home.HomeFragmentDirections
import au.com.scooterise.ui.search.SearchFragmentDirections
import com.bumptech.glide.Glide
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter

class ContentAdapter(data: OrderedRealmCollection<Content>) : RealmRecyclerViewAdapter<Content, ContentAdapter.ContentViewHolder>(data, true) {

    class ContentViewHolder(itemView: View,context : Context) : RecyclerView.ViewHolder(itemView) {
        var imageView : ImageView = itemView.findViewById<ImageView>(R.id.tvshowimage)
        var classifcationView : ImageView = itemView.findViewById<ImageView>(R.id.classificationView)
        var subtext : TextView = itemView.findViewById<TextView>(R.id.horizontal_tvshowsubtext)
        var context = context
        var view = itemView
    }





    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        Glide.with(holder.context).load(data?.get(position)?.image).into(holder.imageView)
        var content : Content = data?.get(position)!!
        holder.view.setOnClickListener {
            var id : Int = content.id?.toInt()!!
            when(MainActivity.currentFragment){
                MainActivity.FragmentID.ChannelFragment->holder.view.findNavController().navigate(ChannelFragmentDirections.navigateToContent().setContent(id))
                MainActivity.FragmentID.SearchFragment->holder.view.findNavController().navigate(SearchFragmentDirections.navigateToContent().setContent(id))
                MainActivity.FragmentID.HomeFragment->holder.view.findNavController().navigate(HomeFragmentDirections.navigateToContent().setContent(id))
            }
        }

        holder.subtext.setText(content.name)
        when(content.classification){
            "g"-> holder.classifcationView.setImageResource(R.drawable.g)
            "pg"->holder.classifcationView.setImageResource(R.drawable.pg)
            "m"-> holder.classifcationView.setImageResource(R.drawable.m)
            "ma"->holder.classifcationView.setImageResource(R.drawable.ma)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ContentViewHolder {

        return ContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tvshow_horizontal,parent,false),parent.context)
    }
}