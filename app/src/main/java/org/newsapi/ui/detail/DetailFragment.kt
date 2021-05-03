package org.newsapi.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.newsapi.R
import org.newsapi.databinding.DetailFragmentBinding
import org.newsapi.load
import org.newsapi.ui.top.HomeViewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private var binding: DetailFragmentBinding? = null
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        viewModel.selectedArticle.observe(viewLifecycleOwner, {
            binding?.imageViewArticle?.load(
                it.urlToImage,
                R.drawable.dummy_image,
                R.drawable.dummy_image
            )
            binding?.titleTextView?.text = it.title
            binding?.descriptionTextView?.text = it.content
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}