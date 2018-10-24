package moeindeveloper.kotlinroomaac.DataSource

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

//database class :

//define database class by below annotation:
//adding singleton annotation and injecting dependencies:
@Database(entities = arrayOf(KNote::class),version = 1,exportSchema = false)
abstract class KNoteDataBase(var context: Context): RoomDatabase() {
    abstract fun getDao(): KNoteDAO
}