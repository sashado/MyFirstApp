package ru.dobrov.myfirstapp.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.dobrov.myfirstapp.R
import ru.dobrov.myfirstapp.databinding.CardPostBinding
import ru.dobrov.myfirstapp.dto.Post
import ru.dobrov.myfirstapp.util.FormatUtils
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeClickListener: (Post) -> Unit,
    private val onShareClickListener: (Post) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            likeCount.text = FormatUtils.formatCount(post.likes)
            shareCount.text = FormatUtils.formatCount(post.shares)
            viewsCount.text = FormatUtils.formatCount(post.views)

            if (post.likedByMe) like.setImageResource(R.drawable.ic_favorite)
            else like.setImageResource(R.drawable.ic_favorite_border)

            like.setOnClickListener { onLikeClickListener(post) }
            share.setOnClickListener { onShareClickListener(post) }
            menu.setOnClickListener {
                android.widget.Toast.makeText(
                    itemView.context,
                    "Меню поста ${post.id}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
            avatar.setOnClickListener {
                android.widget.Toast.makeText(
                    itemView.context,
                    "Профиль автора ${post.author}",
                    android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
