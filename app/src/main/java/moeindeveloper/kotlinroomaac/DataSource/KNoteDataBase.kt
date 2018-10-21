package moeindeveloper.kotlinroomaac.DataSource

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

//database class :

//define database class by below annotation:
@Database(entities = arrayOf(KNote::class),version = 1,exportSchema = false)
abstract class KNoteDataBase(context: Context): RoomDatabase() {
    //initialize database in init method
    init {
        //create Room db:
        Room.databaseBuilder(context, KNoteDataBase::class.java,"notesDB").build()
    }

    abstract fun getDao(): KNoteDAO
}