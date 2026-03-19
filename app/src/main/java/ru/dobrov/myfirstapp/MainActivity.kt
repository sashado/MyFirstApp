package ru.dobrov.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.dobrov.myfirstapp.databinding.ActivityMainBinding
import ru.dobrov.myfirstapp.dto.Post
import ru.dobrov.myfirstapp.util.FormatUtils
import ru.dobrov.myfirstapp.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Activity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.data.observe(this) { post ->
            bindPost(post)
        }
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        println("Activity: onStart")
    }
    override fun onResume() {
        super.onResume()
        println("Activity: onResume")
    }
    override fun onPause() {
        super.onPause()
        println("Activity: onPause")
    }
    override fun onStop() {
        super.onStop()
        println("Activity: onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        println("Activity: onDestroy")
    }
    private fun bindPost(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likeCount.text = FormatUtils.formatCount(post.likes)
            shareCount.text = FormatUtils.formatCount(post.shares)
            viewsCount.text = FormatUtils.formatCount(post.views)

            if (post.likedByMe)
                like.setImageResource(R.drawable.ic_favorite)
            else
                like.setImageResource(R.drawable.ic_favorite_border)

            linkTitle.text = "Новая Нетология: 4 уровня карьеры"
            linkUrl.text = "netology.ru"
        }
    }
    private fun setupClickListeners() {
        binding.apply {
            like.setOnClickListener {
                viewModel.like()
                Toast.makeText(this@MainActivity, "Лайк", Toast.LENGTH_SHORT).show()
            }
            share.setOnClickListener {
                viewModel.share()
                Toast.makeText(this@MainActivity, "Репост +1", Toast.LENGTH_SHORT).show()
            }

            menu.setOnClickListener {
                Toast.makeText(this@MainActivity, "Меню поста", Toast.LENGTH_SHORT).show()
            }
            avatar.setOnClickListener {
                Toast.makeText(this@MainActivity, "Профиль автора", Toast.LENGTH_SHORT).show()
                viewModel.increaseViews()
            }
            root.setOnClickListener {
                println("CLICK: корневой layout")
                Toast.makeText(this@MainActivity, "Клик по фону", Toast.LENGTH_SHORT).show()
            }
        }
    }
}