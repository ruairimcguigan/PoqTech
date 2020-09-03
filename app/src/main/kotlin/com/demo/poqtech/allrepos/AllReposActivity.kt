package com.demo.poqtech.allrepos

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demo.poqtech.R
import com.demo.poqtech.R.string.*
import com.demo.poqtech.data.api.ApiResponse.*
import com.demo.poqtech.data.model.ReposResponse
import com.demo.poqtech.ext.gone
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
        viewModel.fetchRepos().observe(this, Observer { response ->
            when (response) {
                is Loading -> progressBar.visible()
                is Success -> showRepos(response.data as ReposResponse)
                is Error -> showError(response.error)
                is HttpErrors.Forbidden -> showForbiddenNetworkErrorView()
                is HttpErrors.NotFound -> showResourceNotFoundError()
                is HttpErrors.BadGateway -> showBadGatewayError()
                is HttpErrors.InternalError -> showInternalError()
                is HttpErrors.ResourceMoved -> showResourceMovedError()
            }
        })

        viewModel.activeNetworkState.observe(this, Observer { isActive ->
            showNoConnectionSnack(isActive)
        })
    }

    private fun showRepos(repo: ReposResponse) {
        progressBar.gone()
        repoListView.visible()
        adapter.setRepoList(repo)
        repoListView.adapter = adapter
    }

    private fun showForbiddenNetworkErrorView() =
        setErrorViewState(resource_forbidden_error_message)

    private fun showResourceNotFoundError() = setErrorViewState(not_found_error_message)
    private fun showBadGatewayError() = setErrorViewState(bad_gateway_message)
    private fun showInternalError() = setErrorViewState(internal_error_message)
    private fun showResourceMovedError() = setErrorViewState(resource_moved_error_message)

    private fun showNoConnectionSnack(isNetworkAvailable: Boolean) {
        if (!isNetworkAvailable) {
            noReposView.visible()
            noReposView.text = getString(check_connection_message)
            repoListView.gone()
            progressBar.gone()
            getString(check_connection_message).let { root.snack(it) }
        }
    }

    private fun setErrorViewState(@StringRes message: Int) {
        progressBar.gone()
        repoListView.gone()
        noReposView.visible()
        noReposView.text = getString(message)
    }

    private fun showError(message: String?) {
        progressBar.gone()
        repoListView.gone()
        noReposView.visible()
        message?.let { root.snack(it) }
    }
}


