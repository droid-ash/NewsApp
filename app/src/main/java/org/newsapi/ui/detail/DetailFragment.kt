package org.newsapi.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newsapi.api.model.Article
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

        viewModel.selectedArticle.observe(viewLifecycleOwner, { article ->
            bindData(article)
        })
    }

    private fun bindData(article: Article) {
        binding?.let {
            it.titleTextView.text = article.title
            it.descriptionTextView.text = article.content
            it.readMoreTextView.setOnClickListener { _ ->
                it.webView.visibility = View.VISIBLE
                it.webView.loadUrl(article.url)
            }
            it.imageViewArticle.load(
                article.urlToImage,
                R.drawable.dummy_image,
                R.drawable.dummy_image
            )
            if (article.author.isNullOrEmpty()) {
                it.textViewSource.text = article.source.name
            } else {
                it.textViewSource.text = article.author + "\n" + article.source.name
            }
            it.textViewDateTime.text = article.publishedAt
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}