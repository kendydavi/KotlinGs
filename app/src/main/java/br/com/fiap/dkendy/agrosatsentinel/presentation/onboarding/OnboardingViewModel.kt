package br.com.fiap.dkendy.agrosatsentinel.presentation.onboarding

import androidx.lifecycle.ViewModel
import br.com.fiap.dkendy.agrosatsentinel.data.local.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    val totalPages = 3

    fun onNextPage() {
        if (_currentPage.value < totalPages - 1) {
            _currentPage.value++
        }
    }

    fun onPreviousPage() {
        if (_currentPage.value > 0) {
            _currentPage.value--
        }
    }

    fun finishOnboarding() {
        sharedPreferencesManager.isOnboardingDone = true
    }
}
