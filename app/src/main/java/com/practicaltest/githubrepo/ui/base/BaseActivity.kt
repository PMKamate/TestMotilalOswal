package com.practicaltest.githubrepo.ui.base

import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseFragment.Callback {

    private var mProgressDialog: Dialog? = null
    private var viewDataBinding: T? = null
    private var mViewModel: V? = null

    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun getViewDataBinding(): T? { return viewDataBinding }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        viewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        viewDataBinding?.executePendingBindings()
    }

    fun updateUi(){
        viewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        viewDataBinding?.executePendingBindings()
    }

    fun setStatusBarBackground(statusBarColor: Int, setDarkStatusBarIcons: Boolean) {
        val window = getWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (setDarkStatusBarIcons) {
                // clear FLAG_TRANSLUCENT_STATUS confirmedPasswordflag:
                window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS confirmedPasswordflag to the window
                window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window?.getDecorView()?.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            } else {
                window.getDecorView()?.setSystemUiVisibility(0)
            }
            // finally change the color
            window?.statusBarColor = statusBarColor
        }
    }


}