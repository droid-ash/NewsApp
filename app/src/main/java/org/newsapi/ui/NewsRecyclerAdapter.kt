package org.newsapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.newsapi.api.model.Article
import org.newsapi.R
import org.newsapi.databinding.ArticleListItemBinding

class NewsRecyclerAdapter(private val articleClickListener: ArticleClickListener) :
    ListAdapter<Article, NewsRecyclerAdapter.NewsRecyclerViewHolder>(
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.urlToImage == newItem.urlToImage
                        && oldItem.publishedAt == newItem.publishedAt
            }
        }
    ) {

    interface ArticleClickListener {
        fun onArticleClicked(article: Article)
    }

    inner class NewsRecyclerViewHolder(itemBinding: ArticleListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRecyclerViewHolder {
        val binding =
            ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsRecyclerViewHolder, position: Int) {
        ArticleListItemBinding.bind(holder.itemView).apply {
            val article = getItem(position)
            textViewSource.text = article.source.name
            textViewTitle.text = article.title
            textViewDate.text = article.publishedAt
            val urlToImage = article.urlToImage
            if (urlToImage.isNullOrEmpty()) {
                imageViewNews.load(R.drawable.ic_broken_image)
            } else {
                imageViewNews.load(urlToImage) {
                    crossfade(true)
                    placeholder(R.drawable.ic_broken_image)
//                    transformations(CircleCropTransformation())
                }
            }
            holder.itemView.setOnClickListener {
                articleClickListener.onArticleClicked(article)
            }
        }
    }
}