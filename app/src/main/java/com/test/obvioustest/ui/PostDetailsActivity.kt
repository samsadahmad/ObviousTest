package com.test.obvioustest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.obvioustest.R
import com.test.obvioustest.data.repository.PostRepository
import com.test.obvioustest.ui.adapter.PostViewPagerAdapter
import com.test.obvioustest.ui.viewmodel.PostViewModel
import com.test.obvioustest.ui.viewmodel.PostViewModelFactory
import kotlinx.android.synthetic.main.activity_post_details.*


class PostDetailsActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: PostViewPagerAdapter
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        position = intent.getIntExtra("POSITION", 0)

        pagerAdapter =
            PostViewPagerAdapter(supportFragmentManager)
        viewPagerpost.adapter = pagerAdapter

        val factory =
            PostViewModelFactory(this, PostRepository())
        var viewmodel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        viewmodel.getPost().observe(this, Observer {
            pagerAdapter.setData(it)
            viewPagerpost.setCurrentItem(position)
        })
    }

    fun setActionBarTitle(title: String){
        supportActionBar?.setTitle(title)
    }
}