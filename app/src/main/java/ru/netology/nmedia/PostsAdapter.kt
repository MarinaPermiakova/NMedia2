package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.CardPostBinding


typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit
typealias OnViewListener = (post: Post) -> Unit


class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onViewListener: OnViewListener
) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener, onViewListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onViewListener: OnViewListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            text.text = post.content
            textViewLikes.text = formatNumber(post.likes)
            textViewShare.text = formatNumber(post.shares)
            textViewViews.text = formatNumber(post.views)

            imageViewLikes.setImageResource(
                if (post.likedByMe) R.drawable.likes else R.drawable.likes_liked
            )

            imageViewLikes.setOnClickListener {
                onLikeListener(post)
            }

            imageViewShare.setOnClickListener {
                onShareListener(post)
            }

            imageViewViews.setOnClickListener {
                onViewListener(post)
            }
        }
    }


    private fun formatNumber(number: Int): String {
        val suffixes = charArrayOf('k', 'm', 'g')
        if (number < 1000) {
            return java.lang.String.valueOf(number)
        }

        val string = java.lang.String.valueOf(number)

        // разрядность числа
        val magnitude = (string.length - 1) / 3

        // количество цифр до суффикса
        val digits = (string.length - 1) % 3 + 1

        val value = CharArray(4)
        for (i in 0 until digits) {
            value[i] = string[i]
        }
        var valueLength = digits

        // добавление точки и числа
        if (digits == 1 && string[1] != '0') {
            value[valueLength++] = '.'
            value[valueLength++] = string[1]
        }

        // добавление суффикса
        value[valueLength++] = suffixes[magnitude - 1]
        return String(value)
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


