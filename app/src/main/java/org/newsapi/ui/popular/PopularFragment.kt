package org.newsapi.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.newsapi.R

class PopularFragment : Fragment() {

    private lateinit var popularViewModel: PopularViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        popularViewModel =
                ViewModelProvider(this).get(PopularViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_popular, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        popularViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}