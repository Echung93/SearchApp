package com.echung93.searchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echung93.searchapp.domain.use_case.favorite.DeleteFavoriteDataUseCase
import com.echung93.searchapp.domain.use_case.favorite.GetFavoriteDataUseCase
import com.echung93.searchapp.model.KakaoSearchData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val deleteFavoriteDataUseCase: DeleteFavoriteDataUseCase,
    private val getFavoriteDataUseCase: GetFavoriteDataUseCase
) : ViewModel() {
    private val _favoriteList: MutableStateFlow<List<KakaoSearchData>> =
        MutableStateFlow(mutableListOf())
    val favoriteList: StateFlow<List<KakaoSearchData>>
        get() = _favoriteList.asStateFlow()

    private val _favoriteUiState: MutableStateFlow<FavoriteUiState> =
        MutableStateFlow(FavoriteUiState.LOADING)
    val favoriteUiState: StateFlow<FavoriteUiState>
        get() = _favoriteUiState.asStateFlow()

    fun getFavoriteList() {
        viewModelScope.launch {
            getFavoriteDataUseCase().collectLatest {
                _favoriteList.value = it
                _favoriteUiState.value = FavoriteUiState.SUCCESS
            }
        }
    }

    fun deleteFavorite(kakaoSearchData: KakaoSearchData) {
        viewModelScope.launch {
            deleteFavoriteDataUseCase(kakaoSearchData)
        }
    }
}

enum class FavoriteUiState {
    LOADING,
    SUCCESS,
}