package au.com.scooterise.ui.channels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import au.com.scooterise.R

class ChannelsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var layout : ScrollView = inflater.inflate(R.layout.fragment_channels, container, false) as ScrollView

        return layout
    }
    fun openChannel(view : View){
        System.out.println("lmao")
    }
}