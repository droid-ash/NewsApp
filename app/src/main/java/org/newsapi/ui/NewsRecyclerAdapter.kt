package org.newsapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.api.model.Article
import org.newsapi.R
import org.newsapi.databinding.ArticleListItemBinding
import org.newsapi.load

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

            imageViewNews.load(urlToImage, R.drawable.dummy_image, R.drawable.dummy_image)

            holder.itemView.setOnClickListener {
                articleClickListener.onArticleClicked(article)
            }
        }
    }
}