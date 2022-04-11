package co.il.giniappstest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import co.il.giniappstest.R
import co.il.giniappstest.databinding.ActivityMainBinding
import co.il.giniappstest.other.Constants.NETWORKING
import co.il.giniappstest.ui.adapters.NumberAdapter
import co.il.giniappstest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var numberAdapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        mainViewModel.numbers.observe(this) { it ->
            Log.d(NETWORKING, it.toString())
            initGridAdapter(it)
        }
    }

    private fun initGridAdapter(numbers: List<Int>) = binding.rvNumbers.apply {
        numberAdapter = NumberAdapter(numbers)
        adapter = numberAdapter
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }
}