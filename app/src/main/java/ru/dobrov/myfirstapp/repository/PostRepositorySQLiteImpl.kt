package ru.dobrov.myfirstapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.dobrov.myfirstapp.db.PostDao
import ru.dobrov.myfirstapp.dto.Post

class PostRepositorySQLiteImpl(
    private val postDao: PostDao
) : PostRepository {

    private var posts = emptyList<Post>()
    private val _data = MutableLiveData(posts)

    init {
        posts = postDao.getAll()
        _data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = _data

    override fun likeById(id: Long) {
        postDao.likeById(id)
        posts = postDao.getAll()
        _data.value = posts
    }

    override fun shareById(id: Long) {
        postDao.shareById(id)
        posts = postDao.getAll()
        _data.value = posts
    }

    override fun increaseViews(id: Long) {
        postDao.increaseViews(id)
        posts = postDao.getAll()
        _data.value = posts
    }

    override fun save(post: Post): Post {
        val saved = if (post.id == 0L) {
            postDao.insert(post)
        } else {
            postDao.update(post)
        }
        posts = postDao.getAll()
        _data.value = posts
        return saved
    }

    override fun removeById(id: Long) {
        postDao.delete(id)
        posts = postDao.getAll()
        _data.value = posts
    }
}
