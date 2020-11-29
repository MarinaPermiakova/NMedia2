package ru.netology.nmedia

import android.icu.text.CompactDecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private var shares = 0
    private var likes = 0
    private var views = 0

    @RequiresApi(Build.VERSION_CODES.N)
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
                imageView_likes?.setImageResource(R.drawable.likes)
            }

            root.setOnClickListener {
                Log.d("stuff", "root")
            }

            menu.setOnClickListener {
                Log.d("stuff", "menu")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            imageView_likes?.setOnClickListener {
                post.likedByMe = !post.likedByMe

                if (post.likedByMe) {
                    imageView_likes?.setImageResource(R.drawable.likes_liked)
                    likes++
                } else {
                    imageView_likes?.setImageResource(R.drawable.likes)
                    likes--
                }

                textView_likes.text = CompactDecimalFormat.getInstance(
                    Locale.ENGLISH,
                    CompactDecimalFormat.CompactStyle.SHORT
                ).format(likes)
            }

            imageViewShare.setOnClickListener {
                shares++
                textViewShare.text = CompactDecimalFormat.getInstance(
                    Locale.ENGLISH,
                    CompactDecimalFormat.CompactStyle.SHORT
                ).format(shares)
            }

            imageViewViews.setOnClickListener {
                views++
                textViewViews.text = CompactDecimalFormat.getInstance(
                    Locale.ENGLISH,
                    CompactDecimalFormat.CompactStyle.SHORT
                ).format(views)
            }
        }
    }
}