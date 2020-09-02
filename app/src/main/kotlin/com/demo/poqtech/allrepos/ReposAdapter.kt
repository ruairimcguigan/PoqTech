package com.demo.poqtech.allrepos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.poqtech.R
import com.demo.poqtech.R.string.description_placeholder
import com.demo.poqtech.data.model.RepoResponseItem
import java.lang.String.format

class ReposAdapter : RecyclerView.Adapter<RepoViewHolder>() {

  private val repoList = mutableListOf<RepoResponseItem>()

  fun setRepoList(repoList: List<RepoResponseItem>) {
    this.repoList.clear()
    this.repoList.addAll(repoList)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
    return RepoViewHolder(view)
  }

  @SuppressLint("DefaultLocale")
  override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
    val character = repoList[position]
    with(holder) {
      repoNameView.text = format(repoNameView.context.getString(R.string.repo_name_label), character.name)
      descriptionView.text = format(descriptionView.context.getString(description_placeholder), character.description.capitalize()
      )
    }
  }

  override fun getItemCount() = repoList.size
}