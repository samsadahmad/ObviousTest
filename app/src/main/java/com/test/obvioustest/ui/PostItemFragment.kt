package com.test.obvioustest.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.test.obvioustest.R
import com.test.obvioustest.data.responses.PostData
import com.test.obvioustest.utils.hide
import kotlinx.android.synthetic.main.fragment_post_item.view.*
import kotlinx.android.synthetic.main.post_info_bottom_sheet.view.*

class PostItemFragment() : Fragment() {
    private var postData:PostData? = null
    private var pos :Int = 0
    private var size :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postData = arguments?.getSerializable("POST_ITEM") as? PostData
        pos = arguments!!.getInt("POSITION", 0)
        size = arguments!!.getInt("SIZE", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_post_item, container, false)

        Glide.with(this)
            .load(postData?.hdurl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.pb_loading.hide()
                    view.pb_loading.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.pb_loading.hide()
                    return false
                }
            })
            .into(view.im_post_details)

        view.tv_title.text = postData?.title
        view.tv_copyright.text = postData?.copyright
        view.tv_date.text = postData?.date
        view.tv_desc.text = postData?.explanation
        var title = "[$pos / $size]"
        (activity as PostDetailsActivity).setActionBarTitle(title);

        return view
    }

    companion object{
        fun newInstance(postData: PostData, pos: Int, size: Int) : PostItemFragment{
            var fragment = PostItemFragment()
            var bundle = Bundle()
            bundle.putSerializable("POST_ITEM", postData)
            bundle.putInt("POSITION", pos)
            bundle.putInt("SIZE", size)
            fragment.setArguments(bundle);
            return fragment
        }
    }
}