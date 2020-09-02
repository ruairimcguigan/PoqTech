package com.demo.poqtech.allrepos

import android.content.Context
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.demo.poqtech.R.layout.repo_item
import com.demo.poqtech.data.model.RepoResponseItem

class AllReposAdapter : Adapter<AllRepoViewHolder>() {

  private val repoList = mutableListOf<RepoResponseItem>()
  private lateinit var context: Context

  fun setRepoList(repoList: List<RepoResponseItem>) {
    this.repoList.clear()
    this.repoList.addAll(repoList)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AllRepoViewHolder {
    context = parent.context
    return AllRepoViewHolder(from(parent.context).inflate(
      repo_item,
      parent,
      false)
    )
  }

  override fun onBindViewHolder(holder: AllRepoViewHolder, position: Int) = holder.bind(context, repoList[position])

  override fun getItemCount() = repoList.size
}