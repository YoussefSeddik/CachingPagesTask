package com.youssef.seddik.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.youssef.seddik.adapter.AbilitiesAdapter
import com.youssef.seddik.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    private val abilitiesAdapter = AbilitiesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiRecyclerView()
        mainViewModel.loadInitialData()
        handleUiState()
        handlePagination()
    }

    private fun intiRecyclerView() {
        binding.recyclerView.adapter = abilitiesAdapter
    }


    private fun handleUiState() {
        mainViewModel.viewModelScope.launch {
            mainViewModel.uiState.collect { state ->
                when (state) {
                    is AbilitiesUiState.Loading -> {
                        showLoading()
                    }

                    is AbilitiesUiState.Success -> {
                        hideLoading()
                        abilitiesAdapter.addMoreData(state.abilities)
                    }

                    is AbilitiesUiState.Error -> {
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun handlePagination() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    mainViewModel.loadMoreAbilities()
                }
            }
        })
    }
}