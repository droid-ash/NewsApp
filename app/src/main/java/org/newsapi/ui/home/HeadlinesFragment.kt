package org.newsapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsapi.api.model.Article
import dagger.hilt.android.AndroidEntryPoint
import org.newsapi.CATEGORY_GENERAL
import org.newsapi.CATEGORY_LIST
import org.newsapi.data.AppCache
import org.newsapi.databinding.FragmentHeadlinesBinding
import org.newsapi.ui.HomeViewModel
import org.newsapi.ui.NewsRecyclerAdapter

@AndroidEntryPoint
class HeadlinesFragment : Fragment(), NewsRecyclerAdapter.ArticleClickListener,
    CategoryAdapter.CategoryClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var binding: FragmentHeadlinesBinding? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        val category = homeViewModel.lastFetchCategory
        if (category.isNullOrEmpty()) observeForData(CATEGORY_GENERAL)
        else observeForData(category)
    }

    private fun observeForData(category: String?) {
        progressBar?.visibility = View.VISIBLE
        homeViewModel.fetchTopHeadlines(AppCache.CURRENT_COUNTRY, category).observe(viewLifecycleOwner, {
            binding?.categoryRecyclerView?.visibility = View.VISIBLE
            newsRecyclerAdapter.submitList(it)
            progressBar?.visibility = View.GONE
        })
    }

    private fun setUpRecyclerView() {
        newsRecyclerAdapter = NewsRecyclerAdapter(this)
        binding?.homeRecyclerview?.layoutManager = LinearLayoutManager(context)
        binding?.homeRecyclerview?.adapter = newsRecyclerAdapter

        categoryAdapter = CategoryAdapter(this)
        binding?.categoryRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.categoryRecyclerView?.adapter = categoryAdapter
        categoryAdapter.submitList(CATEGORY_LIST)

        progressBar = binding?.progressBar
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCategoryClicked(category: String) {
        observeForData(category)
    }

    override fun onArticleClicked(article: Article, imageView: ImageView) {
        homeViewModel.setSelectedArticle(article)
        val transitionUniqueId = article.articleUniqueId
        val extras = FragmentNavigatorExtras(imageView to transitionUniqueId)
        val action =
            HeadlinesFragmentDirections.actionNavigationHeadlinesFragmentToDetailFragment(transitionUniqueId)

        findNavController().navigate(action, extras)
    }
}