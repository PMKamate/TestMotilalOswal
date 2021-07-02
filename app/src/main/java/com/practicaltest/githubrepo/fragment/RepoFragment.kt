package com.practicaltest.githubrepo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicaltest.githubrepo.activity.RepoDetailsActivityWeb
import com.practicaltest.githubrepo.adapter.RepoAdapter
import com.practicaltest.githubrepo.data.entities.RepoDetail
import com.practicaltest.githubrepo.databinding.NewsFragmentBinding
import com.practicaltest.githubrepo.utils.Resource
import com.practicaltest.githubrepo.utils.autoCleared
import com.practicaltest.githubrepo.viewModel.RepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RepoFragment : Fragment() {

    //val API_KEY = "a6c1c11ccd4a4d22ab54cbdb9edf371e"
    private var binding: NewsFragmentBinding by autoCleared()
    private val viewModel: RepoViewModel by viewModels()
    private lateinit var adapter: RepoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var mNewsDetails = ArrayList<RepoDetail>()
    private var currentPage = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setAdapter()
        setRVLayoutManager()
        initListener()
    }

    private fun setAdapter() {
        adapter = context?.let { RepoAdapter(mNewsDetails, it) }!!
        adapter.notifyDataSetChanged()
        binding.rvlist.adapter = adapter
    }
    private fun initListener() {
        adapter.setOnItemClickListener(object : RepoAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                  val intent = Intent(activity, RepoDetailsActivityWeb::class.java)
                val repoDetail: RepoDetail = mNewsDetails.get(position)
                intent.putExtra("url", repoDetail.htmlUrl)
                intent.putExtra("title", repoDetail.fullName)

                startActivity(intent)

            }
        })
    }
    private fun setRVLayoutManager() {
        linearLayoutManager = LinearLayoutManager(context)
        binding.rvlist.layoutManager = linearLayoutManager
        binding.rvlist.setHasFixedSize(true)
    }
    private fun setupObservers() {
        getActivity()?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        viewModel.getNewsDetails(++currentPage).observe(
            viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING ->
                        binding.progressBar.visibility = View.VISIBLE
                }
            })

    }

}