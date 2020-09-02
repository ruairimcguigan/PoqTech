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

        val name = repo.name ?: context.getString(R.string.no_repo_provided)
        val description = repo.description ?: context.getString(R.string.no_description_provided)
        val avatar = repo.owner.avatar_url ?: context.getString(R.string.default_avatar_url)
        val stars = repo.watchers ?: 0

        val repoAvatarView: ImageView = itemView.repoAvatarView
        val repoNameView: TextView = itemView.repoNameView
        val repoDescriptionView: TextView = itemView.repoDescriptionView
        val watchersCountViewtext: TextView = itemView.watchersCountView

        Picasso.with(context).load(avatar).into(repoAvatarView);
        repoNameView.text = format(context.getString(R.string.repo_name_label), name)
        repoDescriptionView.text = format(context.getString(R.string.description_placeholder), description)
        watchersCountViewtext.text = stars.toString()

    }
}