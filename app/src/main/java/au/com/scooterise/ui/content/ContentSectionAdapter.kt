package au.com.scooterise.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.scooterise.R
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.layout_content_sections_recycler.view.*
import org.w3c.dom.Text

class ContentSectionAdapter(sections: LinkedHashMap<String?, RealmResults<Content>>) : RecyclerView.Adapter<ContentSectionAdapter.ContentSectionViewHolder>() {
    val viewPool : RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    var sections = sections

    class ContentSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var text : TextView = itemView.findViewById<TextView>(R.id.content_section_title)
        var recyclerView : RecyclerView = itemView.findViewById<RecyclerView>(R.id.content_recycler)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentSectionAdapter.ContentSectionViewHolder {

       return ContentSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_content_sections_recycler,parent,false))
    }

    override fun getItemCount(): Int {
        return sections.size
    }

    override fun onBindViewHolder(holder: ContentSectionAdapter.ContentSectionViewHolder, position: Int) {
        holder.text.setText(sections.toList().get(position).first)

        holder.recyclerView.apply {
            setRecycledViewPool(viewPool)
            adapter=ContentAdapter(sections.toList().get(position).second)
        }
    }

}