package ru.dobrov.myfirstapp.adapter

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.dobrov.myfirstapp.R
import ru.dobrov.myfirstapp.databinding.CardPostBinding
import ru.dobrov.myfirstapp.dto.Post
import ru.dobrov.myfirstapp.util.FormatUtils
class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: OnPostInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            like.isChecked = post.likedByMe
            like.text = FormatUtils.formatCount(post.likes)

            share.text = FormatUtils.formatCount(post.shares)
            views.text = FormatUtils.formatCount(post.views)

            like.setOnClickListener {
                listener.onLike(post)
            }
            share.setOnClickListener {
                listener.onShare(post)
            }
            avatar.setOnClickListener {
                listener.onAvatarClick(post)
            }
            menu.setOnClickListener { view ->
                showPopupMenu(view, post)
            }
        }
    }

    private fun showPopupMenu(anchor: View, post: Post) {
        PopupMenu(anchor.context, anchor).apply {
            inflate(R.menu.post_menu)

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> {
                        listener.onEdit(post)
                        true
                    }
                    R.id.remove -> {
                        listener.onRemove(post)
                        true
                    }
                    else -> false
                }
            }
            show()
        }
    }
}
