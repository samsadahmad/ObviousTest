package com.test.obvioustest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.obvioustest.R
import com.test.obvioustest.data.responses.PostData
import com.test.obvioustest.ui.PostActivity
import kotlinx.android.synthetic.main.post_item.view.*

class PostGridAdapter : RecyclerView.Adapter<PostGridAdapter.JsonDataViewHolder>() {
    var posts: List<PostData> = ArrayList<PostData>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return JsonDataViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: JsonDataViewHolder, position: Int) {
        var JsonDataItem = posts.get(position);
        Glide.with(holder.context)
            .load(JsonDataItem.url)
            .centerCrop()
            .into(holder.im_image);

        holder.itemView.setOnClickListener( View.OnClickListener {
            (it.context as PostActivity).onItemClickListener(position)
        })
    }

    fun setData(posts: List<PostData>){
        this.posts = posts
        notifyDataSetChanged()
    }

    class JsonDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var im_image = itemView.list_image
        var context = itemView.context
    }
}