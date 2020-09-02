package com.demo.poqtech.allrepos

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.poqtech.R
import com.demo.poqtech.data.model.RepoResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_item.view.*
import java.lang.String.format

class AllRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(context: Context, repo: RepoResponseItem) {

        val name = repo.name ?: "No repo name provided"
        val description = repo.description ?: "No description provided"
        val avatar = repo.owner.avatar_url ?: "https://avatars0.githubusercontent.com/u/82592?s=200&v=4"
        val stars = repo.watchers ?: 0

        val repoAvatarView: ImageView = itemView.repoAvatarView
        val repoNameView: TextView = itemView.repoNameView
        val repoDescriptionView: TextView = itemView.repoDescriptionView
        val repoStarz: TextView = itemView.watchersCountView

        Picasso.with(context).load(avatar).into(repoAvatarView);
        repoNameView.text = format(context.getString(R.string.repo_name_label), name)
        repoDescriptionView.text = format(context.getString(R.string.description_placeholder), description)
        repoStarz.text = stars.toString()

    }

}