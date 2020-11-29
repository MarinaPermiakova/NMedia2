package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var shares = 0
    private var likes = 0
    private var views = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. " +
                    "Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: " +
                    "от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, " +
                    "которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и " +
                    "начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            text.text = post.content
            if (post.likedByMe) {
                imageViewLikes.setImageResource(R.drawable.likes)
            }

            imageViewLikes.setOnClickListener {
                post.likedByMe = !post.likedByMe

                if (post.likedByMe) {
                    imageViewLikes.setImageResource(R.drawable.likes_liked)
                    likes++
                } else {
                    imageViewLikes.setImageResource(R.drawable.likes)
                    likes--
                }

                textViewLikes.text = formatNumber(likes)
            }

            imageViewShare.setOnClickListener {
                shares++
                textViewShare.text = formatNumber(shares)
            }

            imageViewViews.setOnClickListener {
                views++
                textViewViews.text = formatNumber(views)
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