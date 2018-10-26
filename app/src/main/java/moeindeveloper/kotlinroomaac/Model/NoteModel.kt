package moeindeveloper.kotlinroomaac.Model


import io.reactivex.Flowable
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.DataSource.RoomRepository
import javax.inject.Inject


/*
Model class.
Model class will get the data from repository

 */

class NoteModel @Inject constructor(private var repository: RoomRepository){

    //get the data
    fun getNotes(): Flowable<List<KNote>>{
        return repository.getAll()
    }

    fun addNote(kNote: KNote){
        repository.addNote(kNote)
    }

    fun deleteNote(kNote: KNote){
        repository.deleteNote(kNote)
    }

    fun updateNote(kNote: KNote){
        repository.updateNote(kNote)
    }
}