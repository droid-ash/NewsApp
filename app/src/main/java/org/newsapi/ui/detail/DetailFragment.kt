package org.newsapi.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.newsapi.api.getModifiedDate
import com.newsapi.api.model.Article
import dagger.hilt.android.AndroidEntryPoint
import org.newsapi.R
import org.newsapi.databinding.FragmentDetailBinding
import org.newsapi.load
import org.newsapi.ui.HomeViewModel

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()
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
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeForData()
    }

    private fun observeForData() {
        homeViewModel.selectedArticle.observe(viewLifecycleOwner, { article ->
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
                it.textViewSource.text = article.source?.name
            } else {
                it.textViewSource.text = article.author + "\n" + article.source?.name
            }
            it.textViewDateTime.text = article.getModifiedDate()
            it.floatingActionButton.setOnClickListener { view ->
                homeViewModel.saveArticle(article)
                Snackbar.make(view, "Article Saved Successfully!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}