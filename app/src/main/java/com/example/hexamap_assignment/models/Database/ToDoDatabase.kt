package com.example.to_doapp.data.Database

import android.content.Context
import androidx.room.*
import com.example.hexamap_assignment.models.data.Add_Data

@Database(entities = [Add_Data::class], version = 1, exportSchema = false)
abstract class AddDatabase : RoomDatabase() {

    abstract fun AddDao() : AddDao

    companion object{

        @Volatile
        private var INSTANCE: AddDatabase? = null

        fun getDatabase(context: Context): AddDatabase {
            val tempinstance = INSTANCE
            if(tempinstance != null){
                return tempinstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddDatabase::class.java,
                    "add_database1"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}