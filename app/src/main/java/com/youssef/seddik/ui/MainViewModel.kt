package com.youssef.seddik.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssef.seddik.data.Ability
import com.youssef.seddik.domain.GetAbilitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAbilitiesUseCase: GetAbilitiesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AbilitiesUiState>(AbilitiesUiState.Loading)
    val uiState: StateFlow<AbilitiesUiState> = _uiState.asStateFlow()

    private var page = 1
    private val _abilities = mutableListOf<Ability>()

    fun loadInitialData() {
        loadMoreAbilities()
    }

    fun loadMoreAbilities() {
        viewModelScope.launch {
            _uiState.value = AbilitiesUiState.Loading
            try {
                val newAbilities = getAbilitiesUseCase.getAbilities(page)
                _abilities.addAll(newAbilities)
                _uiState.value = AbilitiesUiState.Success(_abilities.toList())
                page++
            } catch (e: Exception) {
                _uiState.value = AbilitiesUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class AbilitiesUiState {
    object Loading : AbilitiesUiState()
    data class Success(val abilities: List<Ability>) : AbilitiesUiState()
    data class Error(val message: String) : AbilitiesUiState()
}