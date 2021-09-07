package com.at.demo.view.photo

import android.widget.RadioButton
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.at.demo.R
import com.at.demo.databinding.ActivityMainBinding
import com.at.demo.model.bean.PhotoItem
import com.at.demo.view.photo.adapters.PhotosReposAdapter
import com.at.demo.view.base.BaseActivity
import com.at.demo.viewmodel.PhotoViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    private val viewModel: PhotoViewModel by viewModel()
    private val photoAdapter = PhotosReposAdapter {
        onPhotoClicked(it)
    }
    private var searchJob: Job? = null

    companion object {
        private const val DEFAULT_QUERY = "Kitten"
    }

    override fun getLayout() = R.layout.activity_main

    /** Method to initialize Ui*/
    override fun initUI(binding: ViewDataBinding?) {
        this.binding = binding as ActivityMainBinding
        layoutManager = LinearLayoutManager(this)
        initPhotoAdapter()
        val query = DEFAULT_QUERY
        search(query)
    }

    /** Method to adapter of recyclerview */
    private fun initPhotoAdapter() {

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val selectedRadioButton =
                findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
            layoutManager = LinearLayoutManager(this)

            when (selectedRadioButton.id) {
                R.id.rbGrid2by2 -> {
                    layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
                }
                R.id.rbGrid3by3 -> {
                    layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)

                }
            }
            binding.rvPhotos.layoutManager = layoutManager
            binding.rvPhotos.adapter = photoAdapter

        }

        binding.rbLinear.isChecked = true


    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                photoAdapter.submitData(it)
            }
        }
    }


    /** Adapter interface method */
    private fun onPhotoClicked(photoItem: PhotoItem) {

        addFragment(
            R.id.container,
            FragmentImageZoom.create(photoItem)
        )
    }


}