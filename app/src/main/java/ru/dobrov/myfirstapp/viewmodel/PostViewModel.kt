package ru.dobrov.myfirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dobrov.myfirstapp.dto.Post
import ru.dobrov.myfirstapp.repository.PostRepository
import ru.dobrov.myfirstapp.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    init {
        println("ViewModel: created")
    }
    override fun onCleared() {
        super.onCleared()
        println("ViewModel: cleared")
    }
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data: LiveData<Post> = repository.get()

    fun like() = repository.like()
    fun share() = repository.share()
    fun increaseViews() = repository.increaseViews()
}
