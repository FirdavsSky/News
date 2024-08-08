package com.example.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.model.Article
import org.w3c.dom.Text

class ArticleAdapter(
    private val listArticle: List<Article>
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private var context: Context? = null

    inner class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val itemImageView: ImageView = view.findViewById(R.id.itemImageView)
        private val titleTextView: TextView = view.findViewById(R.id.title_textView)
        private val descriptionTextView: TextView = view.findViewById(R.id.description_textView)

        fun bind(item: Article){
            context?.let {
                Glide.with(it)
                    .load(item.urlToImage)
                    .into(itemImageView)
            }
            titleTextView.text = item.title
            descriptionTextView.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_holder,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = listArticle[position]
        holder.bind(item)
    }
}