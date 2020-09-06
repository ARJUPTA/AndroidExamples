package com.arjupta.kauth.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.arjupta.kauth.data.repository.UserRepository
import com.arjupta.kauth.util.startLoginActivity

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View){
        repository.logout()
        view.context.startLoginActivity()
    }
}