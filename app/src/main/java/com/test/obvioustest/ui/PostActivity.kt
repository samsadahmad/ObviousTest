package com.test.obvioustest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.test.obvioustest.R
import com.test.obvioustest.data.repository.PostRepository
import com.test.obvioustest.ui.adapter.PostGridAdapter
import com.test.obvioustest.ui.viewmodel.PostViewModel
import com.test.obvioustest.ui.viewmodel.PostViewModelFactory
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    lateinit var JsonDataAdapter : PostGridAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        JsonDataAdapter = PostGridAdapter()
        lv_jsondata.apply {
            layoutManager = GridLayoutManager(this@PostActivity, 3)
            setHasFixedSize(true)
            adapter = JsonDataAdapter
        }
        val factory =
            PostViewModelFactory(this, PostRepository())
        var viewmodel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        viewmodel.getPost().observe(this, Observer {
            JsonDataAdapter.setData(it)
        })
    }

    fun onItemClickListener(position: Int){
        val intent = Intent(this, PostDetailsActivity::class.java)
        intent.putExtra("POSITION", position)
        startActivity(intent);
    }
}