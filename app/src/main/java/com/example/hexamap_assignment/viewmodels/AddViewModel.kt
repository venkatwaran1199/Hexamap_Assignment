package com.example.hexamap_assignment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hexamap_assignment.models.data.Add_Data
import com.example.to_doapp.data.Database.AddDatabase
import com.example.to_doapp.data.Repository.AddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application):AndroidViewModel(application) {

    private val AddDao = AddDatabase.getDatabase(application).AddDao()

    private val repository =  AddRepository(AddDao)
    val getAllData : LiveData<List<Add_Data>> = repository.getAllData

    fun insertData(Adddata: Add_Data){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(Adddata)
        }
    }

    fun validateuser(name: String, email: String, phone: String, address: String, DOB: String, NumberOfPersons: String, latitude: String, longitude: String):Boolean{
        return !(name.isEmpty() || email.isEmpty()|| phone.isEmpty()|| address.isEmpty()|| DOB.isEmpty()|| NumberOfPersons.isEmpty()|| latitude.isEmpty()|| longitude.isEmpty())
    }

}