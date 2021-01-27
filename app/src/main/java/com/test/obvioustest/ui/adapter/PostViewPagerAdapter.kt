package com.test.obvioustest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.test.obvioustest.data.responses.PostData
import com.test.obvioustest.ui.PostItemFragment

class PostViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var posts: List<PostData> = ArrayList<PostData>();

    override fun getItem(position: Int): Fragment {
        return PostItemFragment.newInstance(
            posts.get(
                position
            ),
            position,
            posts.size
        );
    }

    override fun getCount(): Int {
        return posts.size
    }

    fun setData(posts: List<PostData>){
        this.posts = posts
        notifyDataSetChanged()
    }
}