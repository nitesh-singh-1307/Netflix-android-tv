package com.example.netflix.ui.screen.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.netflix.data.entities.discovere.DiscoverData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.netflix.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverDataViewModel @Inject constructor(
    private val getDiscoverDataUseCase: DiscoverDataUseCase
) : ViewModel() {
    private val _movieDetails = MutableStateFlow<Result<List<DiscoverData>>>(Result.Loading)
    val movieDetails: StateFlow<Result<List<DiscoverData>>> = _movieDetails.asStateFlow()
// DiscoverDataUseCase
    fun doDiscoverData() {
        viewModelScope.launch {
            _movieDetails.value = getDiscoverDataUseCase()
        }
    }
}
