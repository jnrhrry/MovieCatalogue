package com.januar.submission2jetpack.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.januar.submission2jetpack.repository.Repository
import com.januar.submission2jetpack.ui.details.DetailsViewModel


@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repo:Repository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repo) as T
    }
}