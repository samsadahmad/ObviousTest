package com.test.obvioustest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.obvioustest.data.repository.PostRepository

class PostViewModelFactory(val context: Context, val postRepository: PostRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(context, postRepository) as T
    }
}