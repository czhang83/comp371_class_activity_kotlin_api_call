package com.example.classactivity9

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    // define a mutable live data to store the information
    val userInformation = MutableLiveData<UserInformation>()
    // Any -> any types (Generic types)

    // write a get and set to get and update mutable live data
    fun setInformation(info : UserInformation){
        userInformation.value = info
    }

    fun getInformation() : MutableLiveData<UserInformation>{
        return userInformation
    }

}