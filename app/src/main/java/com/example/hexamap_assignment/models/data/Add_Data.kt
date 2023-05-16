package com.example.hexamap_assignment.models.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "forms_table")
@Parcelize
data class Add_Data(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var email:String,
    var phone:String,
    var Address:String,
    var DOB:String,
    var No_of_Persons:Int,
    var latitude:Double,
    var longitude:Double,
):Parcelable
