package com.example.redditandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.redditandroid.databinding.RedditItemBinding
import com.example.redditandroid.model.TopRedditModel
import java.util.*
import kotlin.collections.ArrayList

typealias OnClickImageCallBack = (String?, Boolean?) -> Unit
typealias LoadMore = (String) -> Unit

class HomeAdapter(var redditsList: ArrayList<TopRedditModel>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: RedditItemBinding

    var onClickImage: OnClickImageCallBack? = null
    var loadMore: LoadMore? = null

    private var isLoading = false

    override fun getItemCount() = redditsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RedditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= redditsList.size.minus(10) && !isLoading) {
            isLoading = true
            redditsList.last().name?.let {
                loadMore?.invoke(it)
            }
        }
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: RedditItemBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(position: Int) = with(binding) {
            val reddit = redditsList[position]
            tvAuthor.text = reddit.author
            tvTitle.text = reddit.title
            tvCommentCount.text = reddit.commentCount
            reddit.time?.let {
                val time = getRelativeTime(reddit.time)
                val timeAgo = if (time.toInt() != 1) " hours ago" else " hour ago"
                tvTime.text = "$time $timeAgo"
            }

            val requestOptions = RequestOptions()
                .override(reddit.thumbnailWidth ?: 120, reddit.thumbnailHeight ?: 120)

            Glide.with(binding.ivRedditThumbnail.context)
                .load(reddit.thumbnail)
                .apply(requestOptions)
                .into(ivRedditThumbnail)
            ivRedditThumbnail.setOnClickListener { onClickImage?.invoke(reddit.image, reddit.isVideo) }
        }

        private fun getRelativeTime(redditTime: Double) = (Date().time - redditTime.toLong() * 1000) / 3600000

    }

    fun setReddits(newReddits: ArrayList<TopRedditModel>) {
        redditsList.clear()
        redditsList.addAll(newReddits)
        isLoading = false
        notifyDataSetChanged()
    }

    fun addItems(newReddits: ArrayList<TopRedditModel>) {
        val position = redditsList.size
        redditsList.addAll(newReddits)
        isLoading = false
        notifyItemInserted(position)
    }
}