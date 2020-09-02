package com.demo.poqtech.allrepos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val repoNameView: TextView = itemView.repoNameView
    val descriptionView: TextView = itemView.repoDescriptionView
}