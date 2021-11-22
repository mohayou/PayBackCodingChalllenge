package com.younes.paybackcodingchallenge.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo

import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.younes.paybackcodingchallenge.R
import com.younes.paybackcodingchallenge.adapter.ImageRecyclerViewAdapter
import com.younes.paybackcodingchallenge.util.Status
import com.younes.paybackcodingchallenge.viewmodel.SearchImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerViewAdapter
) : Fragment(R.layout.fragment_search) {


    lateinit var searchViewModel: SearchImageViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      searchViewModel =    ViewModelProvider(requireActivity()).get(SearchImageViewModel::class.java)

        etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })

        rvResults.apply {
            adapter = imageRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        imageRecyclerAdapter.setOnItemClickListener {
            showConfirmationDialog(it)

        }

        performSearch()
    }

    private fun showConfirmationDialog(id:String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(resources.getString(R.string.image_details))
        builder.setMessage(resources.getString(R.string.show_details))
        builder.setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToImageDetailFragment(id)
            )
            dialog.dismiss()
        }

        builder.setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()

    }

    private fun performSearch() {
        if (!TextUtils.isEmpty(etSearch.text.toString().trim())) {
            searchViewModel.searchForImage((etSearch.text.toString().trim()))
        }

        searchViewModel.imageList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.hits?.let {
                        imageRecyclerAdapter.images = it
                        searchViewModel.insertImages(it)
                    }

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()

                }

                Status.LOADING -> {

                }
            }
        })
    }
}