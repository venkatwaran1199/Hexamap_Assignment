package com.example.to_doapp.data.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hexamap_assignment.models.data.Add_Data

@Dao
interface AddDao {

    /*==============================================Query for Inserting data To Database=========================================================*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(Addeddata: Add_Data)

    /*==============================================Query for Getting All Data From Database=========================================================*/
    @Query("SELECT * FROM forms_table")
    fun getAllData(): LiveData<List<Add_Data>>
}