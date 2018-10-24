package moeindeveloper.kotlinroomaac.Model


import io.reactivex.Flowable
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.DataSource.RoomRepository


/*
Model class.
Model class will get the data from repository


 */

class NoteModel(private var repository: RoomRepository){

    //get the data
    fun getNotes(): Flowable<ArrayList<KNote>>{
        return repository.getAll()
    }


}