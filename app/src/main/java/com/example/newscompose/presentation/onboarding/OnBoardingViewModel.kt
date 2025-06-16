package com.example.newscompose.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Author: Shivang Yadav
 * Created: 6/8/25
 * Description: [Add description here]
 */

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){


    private val _appEntryState = MutableStateFlow(false)
    val appEntryState: StateFlow<Boolean> = _appEntryState

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect {
                _appEntryState.value = it
            }
        }
    }

    fun saveAppEntry(){

        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}