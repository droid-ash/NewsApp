package org.newsapi.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapi.api.model.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.newsapi.SEARCH_NEWS_DELAY
import org.newsapi.databinding.FragmentExploreBinding
import org.newsapi.ui.HomeViewModel
import org.newsapi.ui.NewsRecyclerAdapter

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentExploreBinding? = null
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        val query = homeViewModel.lastSearchQuery
        if (!query.isNullOrEmpty()) observeForData(query)
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        var job: Job? = null
        binding?.searchEditText?.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_DELAY)
                editable?.let {
                    val searchQuery = it.toString()
                    if (searchQuery.isNotBlank()) {
                        observeForData(searchQuery)
                    }
                }
            }
        }
    }

    private fun observeForData(query: String) {
        binding?.progressBar?.visibility = View.VISIBLE
        homeViewModel.searchHeadlines(query).observe(viewLifecycleOwner, {
            newsRecyclerAdapter.submitList(it)
            binding?.progressBar?.visibility = View.GONE
        })
    }

    private fun setUpRecyclerView() {
        newsRecyclerAdapter =
            NewsRecyclerAdapter { article, imageView -> onArticleClicked(article, imageView) }
        binding?.recyclerViewSearch?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewSearch?.adapter = newsRecyclerAdapter
    }

    private fun onArticleClicked(article: Article, imageView: ImageView) {
        homeViewModel.setSelectedArticle(article)
        val transitionUniqueId = article.articleUniqueId
        val extras = FragmentNavigatorExtras(imageView to transitionUniqueId)
        val action =
            ExploreFragmentDirections.actionNavigationExploreToDetailFragment(transitionUniqueId)

        findNavController().navigate(action, extras)
    }
}