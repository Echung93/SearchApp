package com.echung93.searchapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echung93.searchapp.domain.repository.SearchRepository
import com.echung93.searchapp.domain.util.Resource
import com.echung93.searchapp.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    fun loadSearchData() {
        viewModelScope.launch {
            when (val result = repository.getSearchData("강아지")) {
                is Resource.Success -> {
                    Log.d(TAG, "loadData: }" + result.data )
                }

                is Resource.Error -> {
                    Log.d(TAG, "error: " + result.message)
                }
            }
        }
    }

}