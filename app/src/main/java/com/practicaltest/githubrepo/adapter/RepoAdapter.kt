package com.practicaltest.githubrepo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.practicaltest.githubrepo.R
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.utils.Utils
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoAdapter(val arrayList: ArrayList<RepoDetail>, val context: Context) :
    RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    var mOnItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return MyViewHolder(view, mOnItemClickListener)
    }
    fun setItems(items: ArrayList<RepoDetail>) {
        Log.d("Test: adapter: ",""+items.size)
        this.arrayList.clear()
        this.arrayList.addAll(items)
        notifyDataSetChanged()
    }
    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        val model: RepoDetail = arrayList[position]!!
        val requestOptions = RequestOptions()
        requestOptions.placeholder(Utils.getRandomDrawbleColor())
        requestOptions.error(Utils.getRandomDrawbleColor())
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions.centerCrop()
        model.avatarUrl?.let { img ->
            Glide.with(context)
                .load(img)
                .into(holder.imageView)
        }
        model.fullName?.let {
            holder.title.setText(it)
        }
        model.description?.let {
            holder.desc.setText(it)
        }
       /* model.sourceName?.let {
            holder.author.setText(model.sourceName)
        }
        model.publishedAt?.let {
            holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(model.publishedAt))
        }
        model.publishedAt?.let {
            holder.published_ad.setText(Utils.DateFormat(model.publishedAt))
        }*/

    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    class MyViewHolder(itemView: View, onItemClickListener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title = itemView.item_title
        var desc = itemView.item_desc
        var item_time = itemView.item_time
        var item_likes = itemView.item_likes
        var imageView = itemView.item_profile_img
        var imageViewlg = itemView.item_img_language
        lateinit var onItemClickListener: OnItemClickListener
        override fun onClick(v: View) {
            onItemClickListener.onItemClick(v, getAdapterPosition())
        }

        init {
            itemView.setOnClickListener(this)

            if (onItemClickListener != null) {
                this.onItemClickListener = onItemClickListener
            }
        }
    }


}
