package org.newsapi.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.newsapi.api.model.Article
import org.newsapi.CATEGORY_GENERAL
import org.newsapi.R
import org.newsapi.databinding.DetailFragmentBinding
import org.newsapi.databinding.FragmentTopHeadlinesBinding
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
            binding?.imageViewArticle?.load(it.urlToImage)
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}