package moeindeveloper.kotlinroomaac.DataSource

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

//database class :

//define database class by below annotation:
//adding singleton annotation and injecting dependencies:
@Database(entities = [KNote::class],version = 1)
abstract class KNoteDataBase: RoomDatabase() {
    abstract fun getDao(): KNoteDAO
}