package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel: ViewModel() {

    private var _shoeDetail: MutableLiveData<MutableList<Shoe>> = MutableLiveData()
    val shoeDetail: LiveData<MutableList<Shoe>> get() = _shoeDetail


    fun setShoeDetail(name: String, size: String, company: String, description: String){
        _shoeDetail.value = (_shoeDetail.value?.plus(Shoe(name, size, company, description)) ?: listOf(Shoe(name, size, company, description))) as MutableList<Shoe>?
        Log.i("TAG", "setShoeDetail: added in viewModel")
    }


}