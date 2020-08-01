package au.com.scooterise.ui.content

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import au.com.scooterise.MainActivity
import au.com.scooterise.R
import au.com.scooterise.ui.channels.Channel
import com.bumptech.glide.Glide
import io.realm.Realm

import io.realm.kotlin.where



class ContentFragment : Fragment() {
    lateinit var realm: Realm
    val args: ContentFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        realm= Realm.getDefaultInstance()
        System.out.println(args.content)
        var content : Content? = realm.where<Content>().equalTo("id",args.content).findFirst()
        System.out.println(content)
        var root = inflater.inflate(R.layout.fragment_content, container, false)
        var scroll : ScrollView = root.findViewById(R.id.content_Scroll)
        var channel : Channel? =realm.where<Channel>().equalTo("id", content?.channel!!).findFirst()
        scroll.setOnScrollChangeListener { view, scrollX, scrollY, oldX, oldY ->

        }
        root.findViewById<ImageView>(R.id.content_back_button).setOnClickListener { root.findNavController().popBackStack() }
        Glide.with(context!!).load(content!!.image!!).into(root.findViewById<ImageView>(R.id.content_content_image))
        root.findViewById<TextView>(R.id.content_title).setText(content.name)
        root.findViewById<TextView>(R.id.content_description).setText(content.description)
        val classification = root.findViewById<ImageView>(R.id.content_classification)
        when(content.classification){
            "g"-> classification.setImageResource(R.drawable.g)
            "pg"-> classification.setImageResource(R.drawable.pg)
            "m"-> classification.setImageResource(R.drawable.m)
            "ma"-> classification.setImageResource(R.drawable.ma)
        }
        when(content.channel!!.toInt()){
            0->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abc)
            1->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abccomedy)
            2->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abcme)
            3->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abckids)
            4->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abcnews)
            5->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_abcarts)
            6->root.findViewById<ImageView>(R.id.content_content_image).setImageResource(R.drawable.ic_iviewpresents)




        }


        return root
    }

    override fun onStart() {
        super.onStart()
        MainActivity.currentFragment= MainActivity.FragmentID.ContentFragment
    }

    override fun onDetach() {
        super.onDetach()
        realm.close()
    }





}