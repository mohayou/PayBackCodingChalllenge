package com.younes.paybackcodingchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.younes.paybackcodingchallenge.R
import com.younes.paybackcodingchallenge.databinding.FragmentImageDetailBinding
import com.younes.paybackcodingchallenge.model.ImageResult
import com.younes.paybackcodingchallenge.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.art_row.*
import javax.inject.Inject


@AndroidEntryPoint
class ImageDetailFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_image_detail) {

    lateinit var imageViewModel: ImageViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentImageDetailBinding = FragmentImageDetailBinding.inflate(inflater, container, false)
        imageViewModel = ViewModelProvider(requireActivity()).get(ImageViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.imageViewModel = imageViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var imageId = ImageDetailFragmentArgs.fromBundle(it).imageId

            imageViewModel.getImageFromDb(imageId)

        }
    }

    @BindingAdapter("loadLargeImage")
    fun loadLargeImage(ivRowImage: ImageView, url: String) {
        url?.let {
            glide.load(url).into(ivRowImage)
        }
    }

    private fun displayImageDetails(imageResult: ImageResult) {
        tvViews.text = "Views: ${imageResult.views}"
        tvViews.text = "Downloads: ${imageResult.downloads}"
        tvViews.text = "Comments: ${imageResult.comments}"

        glide.load(imageResult.largeImageURL).into(ivRowImage)
    }
}