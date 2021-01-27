package com.test.obvioustest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.obvioustest.data.repository.PostRepository
import com.test.obvioustest.data.responses.PostData
import com.test.obvioustest.utils.JsonUtils
import kotlinx.coroutines.launch

class PostViewModel(context: Context, postRepository: PostRepository): ViewModel() {
    val posts = MutableLiveData<List<PostData>>()

    init {
        viewModelScope.launch {
            posts.value =  postRepository.getPost(context);
        }
    }

    fun getPost() = posts
}