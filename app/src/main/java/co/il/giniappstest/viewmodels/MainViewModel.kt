package co.il.giniappstest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.il.giniappstest.data.models.NumbersResponse
import co.il.giniappstest.other.Constants.FAILED_REQUEST
import co.il.giniappstest.other.Constants.NETWORKING
import co.il.giniappstest.repositories.NumberRepository
import co.il.giniappstest.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val numberRepository: NumberRepository) : ViewModel() {

    private val _numbers = MutableLiveData<List<Int>>()
    val numbers: LiveData<List<Int>> = _numbers

    init {
        getNumbers()
    }

    private fun getNumbers() = viewModelScope.launch {

        val response = numberRepository.getNumbers()
        when (response.status) {
            Status.SUCCESS -> {
                convertResponseToList(response.data!!)
            }
            Status.ERROR -> Log.e(NETWORKING, response.message.toString())
            else -> Log.d(NETWORKING, FAILED_REQUEST)
        }
    }

    private fun convertResponseToList(response: NumbersResponse) {
        val numbers = ArrayList<Int>()
            response.numbers.forEach {
                numbers.add(it.number)
            }
        _numbers.postValue(numbers)
    }

}