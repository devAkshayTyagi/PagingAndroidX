package com.at.demo.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.at.demo.R
import com.at.demo.model.remote.ApiResponse
import com.at.demo.model.response.GeneralResponse
import com.at.demo.view.extensions.doTransaction
import com.at.demo.view.extensions.showToast
import com.at.demo.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity(), BaseInterFace, LifecycleOwner {

    private val baseViewModel: BaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutIdRes = layout
        if (layoutIdRes != 0) {
            val binding = DataBindingUtil.setContentView(this, layoutIdRes) as ViewDataBinding
            initUI(binding)
        }
    }

    fun addFragment(
        frameId: Int,
        fragment: Fragment,
        tag: String? = null,
        addtoBackstack: Boolean = true
    ) {
        supportFragmentManager.doTransaction {
            add(frameId, fragment).apply {
                if (addtoBackstack)
                    addToBackStack(tag)
            }
        }
    }

    fun isValidResponse(
        response: GeneralResponse
    ): Boolean {
        return if (response.status.equals("ok", ignoreCase = true)
        ) {
            true
        } else {
            showToast(getString(R.string.error))
            false
        }
    }

    fun handleError(error: ApiResponse.ApiError?, showAlert: Boolean = true) {
        showToast(error?.message ?: getString(R.string.error))
    }


}