package com.example.to_doapp.data.Repository

import androidx.lifecycle.LiveData
import com.example.hexamap_assignment.models.data.Add_Data
import com.example.to_doapp.data.Database.AddDao

class AddRepository(private val AddformsDao: AddDao) {

    /*==============================================Repository for Getting All Data From Dao=========================================================*/
    val getAllData:LiveData<List<Add_Data>> = AddformsDao.getAllData()

    /*==============================================Repository for Inserting data To Dao=========================================================*/
    suspend fun insertData(Addeddata: Add_Data){
        AddformsDao.insertData(Addeddata)
    }
}