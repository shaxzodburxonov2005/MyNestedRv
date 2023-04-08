package com.example.mynestedrv.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynestedrv.model.movie.Search
import com.example.mynestedrv.repositorty.MainRepository
import com.example.mynestedrv.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    init {
        getAllMovies()
    }

    private val movieData = MutableLiveData<Resource<List<Search>>>()

    fun getAllMovies(): LiveData<Resource<List<Search>>> {
        viewModelScope.launch {
            val searchList = repository.getAllMovies()
            if (searchList.isSuccessful) {
                movieData.postValue(Resource.success(searchList.body()?.Search ?: emptyList()))
            }
        }
        return movieData
    }
}