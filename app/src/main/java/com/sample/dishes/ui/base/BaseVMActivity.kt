package com.sample.dishes.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.sample.dishes.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseVMActivity <VM : ViewModel, VB : ViewBinding> : AppCompatActivity(),
    CoroutineScope by MainScope() {
    protected abstract val mViewModel: VM
    lateinit var mViewBinding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        mViewBinding = getViewBinding()
        performViewModelBinding()
        initializeObservers()
    }

    protected fun setToolbarTitle(title: String) {
        val mToolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        mToolbar.title = title
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        cancel()

    }

    open fun initializeObservers() {
    }

    private fun performViewModelBinding() {
        initView()
    }
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}