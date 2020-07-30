package au.com.scooterise.ui.content

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.ui.channels.Channel
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter

class ContentAdapter(data: OrderedRealmCollection<Content>) : RealmRecyclerViewAdapter<Content, ContentAdapter.ContentViewHolder>(data, true) {
    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }





    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        TODO("Not yet implemented")
    }
}