package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. " +
                "Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: " +
                "от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, " +
                "которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста " +
                "и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false,
        likes = 0,
        views = 0,
        shares = 0
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }

    override fun liked(): String {
        if (post.likedByMe) post.likes++ else post.likes--
        return formatNumber(post.likes)
    }

    override fun shared(): String {
        return formatNumber(post.shares++)
    }

    override fun viewed(): String {
        return formatNumber(post.views++)
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