package com.sample.dishes.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.sample.dishes.R
import com.sample.dishes.data.remote.api.ViewState
import com.sample.dishes.databinding.ActivityMainBinding
import com.sample.dishes.model.HomeModelResponses
import com.sample.dishes.ui.base.BaseVMActivity
import com.sample.dishes.ui.details.DetailsActivity
import com.sample.dishes.utils.hide
import com.sample.dishes.utils.isOnline
import com.sample.dishes.utils.launchActivity
import com.sample.dishes.utils.show
import com.sample.dishes.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var homeModelResponses: HomeModelResponses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        //setToolbarTitle("Home")
    }

    override val mViewModel: MainViewModel by viewModels()


    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        mViewModel.getDishesOfTheDay()

       mViewBinding.shareButton.setOnClickListener {
            shareLink()
        }

        mViewBinding.cardView.setOnClickListener {
            launchActivity<DetailsActivity> {
                putExtra("ID", homeModelResponses.id)
            }

        }
    }

    override fun initializeObservers() {
        super.initializeObservers()
        mViewModel.homeModelFlow.asLiveData().observe(this) { viewState ->
            when (viewState) {
                is ViewState.Loading -> showProgressBar()
                is ViewState.Success -> {
                    hideProgressBar()
                    onSuccess(viewState.data)
                }
                is ViewState.Failed -> {
                    hideProgressBar()
                    toast(viewState.message)
                }
                is ViewState.NetworkFailed -> {
                    toast(getString(R.string.no_internet_message))
                    hideProgressBar()
                }
                is ViewState.ExceptionError -> {
                    toast(viewState.message)
                    hideProgressBar()
                }
                else -> toast(viewState.toString())
            }
        }
    }

    private fun onSuccess(data: HomeModelResponses) {
        homeModelResponses = data
        mViewBinding.primaryText.text = data.name
        mViewBinding.supportingText.text = data.short_desc
        Glide.with(this).load(data.image_url)
            .into(mViewBinding.mediaImage)
    }

    private fun hideProgressBar() {
        mViewBinding.progressBar.hide()
    }

    private fun showProgressBar() {
        mViewBinding.progressBar.show()
    }

    private fun shareLink() {
        if (homeModelResponses.share_link.isNotEmpty()) {
            val shortLinkShare = homeModelResponses.share_link

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT, "\n Check out this link:- $shortLinkShare\n"
            )
            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Send Via"
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }


    }
}