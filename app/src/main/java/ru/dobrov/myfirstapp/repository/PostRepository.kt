package ru.dobrov.myfirstapp.repository
import androidx.lifecycle.LiveData
import ru.dobrov.myfirstapp.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    fun increaseViews()
}
