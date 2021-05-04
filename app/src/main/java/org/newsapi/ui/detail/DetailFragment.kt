package org.newsapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.newsapi.api.model.Article
import org.newsapi.R
import org.newsapi.databinding.DetailFragmentBinding
import org.newsapi.load
import org.newsapi.ui.top.HomeViewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private var binding: DetailFragmentBinding? = null
    private val viewModel: HomeViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

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
        observeForData()
    }

    private fun observeForData() {
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
            it.imageViewArticle.apply {
                transitionName = args.imageTransitionUniqueId
                load(article.urlToImage)
            }
            if (article.author.isNullOrEmpty()) {
                it.textViewSource.text = article.source.name
            } else {
                it.textViewSource.text = article.author + "\n" + article.source.name
            }
            it.textViewDateTime.text = article.modifiedPublishedAt
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}