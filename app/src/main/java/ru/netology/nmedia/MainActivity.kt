package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                text.text = post.content
                textViewLikes.text = formatNumber(post.likes)
                textViewShare.text = formatNumber(post.shares)
                textViewViews.text = formatNumber(post.views)
                imageViewLikes.setImageResource(
                    if (post.likedByMe) R.drawable.likes_liked
                    else R.drawable.likes
                )
            }
        }

        binding.imageViewLikes.setOnClickListener {
            viewModel.like()
        }

        binding.imageViewShare.setOnClickListener {
            viewModel.shared()
        }

        binding.imageViewViews.setOnClickListener {
            viewModel.viewed()
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