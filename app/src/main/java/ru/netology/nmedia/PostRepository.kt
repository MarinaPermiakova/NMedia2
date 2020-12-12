package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun liked(): String
    fun shared(): String
    fun viewed(): String
}