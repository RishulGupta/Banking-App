package com.uilover.project2322.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.uilover.project2322.Domain.Profile
import com.uilover.project2322.Repository.ProfileRepository

class ProfileViewModel:ViewModel() {
    private val repository:ProfileRepository=ProfileRepository()
    val profileLiveData:LiveData<Profile> = repository.getProfileLiveData()
}