package com.test.obvioustest.data.repository

import android.content.Context
import com.test.obvioustest.data.responses.PostData
import com.test.obvioustest.utils.JsonUtils

class PostRepository {
    suspend fun getPost(context: Context): List<PostData>{
        return JsonUtils.getJsonData(context)
    }
}