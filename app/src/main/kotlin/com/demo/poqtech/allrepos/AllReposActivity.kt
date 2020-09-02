package com.demo.poqtech.allrepos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demo.poqtech.R
import com.demo.poqtech.data.api.ApiResponse.*
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.ext.gone
import com.demo.poqtech.ext.invisible
import com.demo.poqtech.ext.snack
import com.demo.poqtech.ext.visible
import dagger.android.AndroidInjection.inject
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject

class AllReposActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: AllReposViewModel
    @Inject lateinit var adapter: AllReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        inject(this)
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.reposResult.observe(this, Observer { response ->
            when (response) {
                is Success -> showRepos(response.data as ReposResponse)
                is Error -> showError(response.error)
                is Loading -> progressBar.visible()
            }
        })
    }

    private fun showRepos(repo: ReposResponse) {
        progressBar.gone()
        repoListView.visible()
        adapter.setRepoList(repo)
        repoListView.adapter = adapter
    }

    private fun showError(message: String?) {
        progressBar.gone()
        noReposView.visible()
        message?.let { root.snack(it) }
    }
}


