package com.demo.poqtech.allrepos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demo.poqtech.R
import com.demo.poqtech.data.api.ApiResponse
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.ext.snack
import com.demo.poqtech.ext.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject

class ReposActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: ReposViewModel

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        AndroidInjection.inject(this)
        initViewModel()
        initRepoList()
    }

    private fun initViewModel() {
        viewModel.reposResult.observe(this, Observer { response ->
            when(response){
                    is ApiResponse.Success -> showRepos(response.data as ReposResponse)
                    is ApiResponse.Error -> showError(response.error)
                    is ApiResponse.Loading -> toggleLoading(true)
                }
            Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show()
        })
    }

    private fun initRepoList() {
        adapter = ReposAdapter()
        repoListView.adapter = adapter
    }

    private fun showRepos(repo: ReposResponse) {
        toggleLoading(false)

        Toast.makeText(this, repo.first().name, Toast.LENGTH_LONG).show()
    }

    private fun showError(message: String?) {
        message?.let { root.snack(it) }
    }

    private fun toggleLoading(isVisible: Boolean) {
        progressBar.visible = isVisible
    }
}


