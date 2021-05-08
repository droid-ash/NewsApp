package org.newsapi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.newsapi.databinding.CateogoryListItemBinding

class CategoryAdapter(private val onCategoryClicked: (String)-> Unit) :
    ListAdapter<String, CategoryAdapter.CategoryViewHolder>(
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.equals(newItem, true)
            }
        }
    ) {

    inner class CategoryViewHolder(categoryItemBinding: CateogoryListItemBinding) :
        RecyclerView.ViewHolder(categoryItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CateogoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        CateogoryListItemBinding.bind(holder.itemView).apply {
            val category = getItem(position)
            categoryButton.text = category
            holder.itemView.setOnClickListener {
                onCategoryClicked(category)
            }
        }
    }
}