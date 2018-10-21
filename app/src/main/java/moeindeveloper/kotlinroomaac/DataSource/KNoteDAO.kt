package moeindeveloper.kotlinroomaac.DataSource

import android.arch.persistence.room.*
import io.reactivex.Flowable

//Data Access Object class: DAO
//define it by using below annotation:
@Dao
interface KNoteDAO {

    //get all notes as Flowable:
    @Query("SELECT * FROM notes")
    fun getAll():Flowable<ArrayList<KNote>>

    //insert:
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(kNote: KNote)

    //update:
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(kNote: KNote)

    //delete:
    @Delete
    fun deleteNote(kNote: KNote)

}