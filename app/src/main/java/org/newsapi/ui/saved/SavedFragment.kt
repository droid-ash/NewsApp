package org.newsapi.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.newsapi.api.model.Article
import org.newsapi.databinding.FragmentHeadlinesBinding
import org.newsapi.ui.NewsRecyclerAdapter
import org.newsapi.ui.HomeViewModel

class SavedFragment : Fragment(), NewsRecyclerAdapter.ArticleClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentHeadlinesBinding? = null
    private lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapterPosition = viewHolder.adapterPosition
            val article = newsRecyclerAdapter.currentList[adapterPosition]
            homeViewModel.deleteArticle(article)
            view?.let {
                Snackbar.make(it, "Article Deleted!", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        homeViewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }
    }

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

        binding?.categoryRecyclerView?.visibility = View.GONE
        setUpRecyclerView()
        observeForData()
    }

    private fun setUpRecyclerView() {
        newsRecyclerAdapter = NewsRecyclerAdapter(this)
        val homeRecyclerview = binding?.homeRecyclerview
        homeRecyclerview?.layoutManager = LinearLayoutManager(context)
        homeRecyclerview?.adapter = newsRecyclerAdapter
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(homeRecyclerview)
    }

    private fun observeForData() {
        homeViewModel.getSavedNews().observe(viewLifecycleOwner, {
            newsRecyclerAdapter.submitList(it)
        })
    }

    override fun onArticleClicked(article: Article, imageView: ImageView) {
        homeViewModel.setSelectedArticle(article)
        val transitionUniqueId = article.articleUniqueId
        val extras = FragmentNavigatorExtras(imageView to transitionUniqueId)
        val action =
            SavedFragmentDirections.actionNavigationSavedToDetailFragment(transitionUniqueId)

        findNavController().navigate(action, extras)
    }
}