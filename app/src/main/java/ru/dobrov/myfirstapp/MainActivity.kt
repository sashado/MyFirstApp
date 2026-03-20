package ru.dobrov.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.dobrov.myfirstapp.adapter.PostsAdapter
import ru.dobrov.myfirstapp.databinding.ActivityMainBinding
import ru.dobrov.myfirstapp.dto.Post
import ru.dobrov.myfirstapp.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostsAdapter(
            onLikeClickListener = { post ->
                viewModel.likeById(post.id)
                Toast.makeText(this, "Лайк поста ${post.id}", Toast.LENGTH_SHORT).show()
            },
            onShareClickListener = { post ->
                viewModel.shareById(post.id)
                Toast.makeText(this, "Репост поста ${post.id}", Toast.LENGTH_SHORT).show()
            }
        )
        binding.list.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}