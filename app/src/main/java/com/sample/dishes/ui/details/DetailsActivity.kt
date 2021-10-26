package com.sample.dishes.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.dishes.R
import com.sample.dishes.data.remote.api.ViewState
import com.sample.dishes.databinding.ActivityDetailsBinding
import com.sample.dishes.model.HomeModelResponses
import com.sample.dishes.ui.base.BaseVMActivity
import com.sample.dishes.ui.details.adapter.CarouselTransformer
import com.sample.dishes.ui.details.adapter.ViewPagerAdapter
import com.sample.dishes.utils.hide
import com.sample.dishes.utils.show
import com.sample.dishes.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseVMActivity<MainViewModel, ActivityDetailsBinding>() {

    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        //setToolbarTitle("Details")
    }

    override fun initView() {
        val id = intent.getIntExtra("ID", 0)
        mViewModel.getDishesDetailsById(id)
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
        mViewBinding.primaryText.text = data.name
        mViewBinding.supportingText.text = data.short_desc

        mViewBinding.viewPager.adapter = ViewPagerAdapter(layoutInflater, data.more_images)

        mViewBinding.viewPager.offscreenPageLimit = 1
        mViewBinding.viewPager.setPageTransformer(CarouselTransformer(this))
    }

    private fun hideProgressBar() {
        mViewBinding.progressBar.hide()
    }

    private fun showProgressBar() {
        mViewBinding.progressBar.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}