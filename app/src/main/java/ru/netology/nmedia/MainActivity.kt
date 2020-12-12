package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_main.*
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
                imageViewLikes.setImageResource(
                    if (post.likedByMe) R.drawable.likes_liked
                    else R.drawable.likes
                )
            }
        }

        binding.imageViewLikes.setOnClickListener {
            viewModel.like()
            textViewLikes.text = viewModel.liked()
        }

        binding.imageViewShare.setOnClickListener {
            textViewShare.text = viewModel.shared()
        }

        binding.imageViewViews.setOnClickListener {
            textViewViews.text = viewModel.viewed()
        }
    }
}