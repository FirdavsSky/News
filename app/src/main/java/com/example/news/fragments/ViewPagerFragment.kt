package com.example.news.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.ArticleAdapter
import com.example.news.di.AppModule
import com.example.news.viewModel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private var articleRecyclerView: RecyclerView? = null
    private var articleAdapter: ArticleAdapter? = null

    private val viewModel: ArticleViewModel by viewModels()
    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        param1?.let { viewModel.fetchArticles(it,AppModule.API_KEY) }

        articleRecyclerView = view.findViewById(R.id.articleRecyclerView)

        param1?.let { viewModel.fetchArticles(it,AppModule.API_KEY) }

        viewModel.article.observe(viewLifecycleOwner){article ->
            articleRecyclerView.let {
                if (it != null) {
                    it.adapter = ArticleAdapter(article)
                }
            }

        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}