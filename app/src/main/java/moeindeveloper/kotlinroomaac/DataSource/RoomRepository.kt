package moeindeveloper.kotlinroomaac.DataSource

import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

//room data repository:
@Singleton
class RoomRepository @Inject constructor(var kNoteDAO: KNoteDAO){
    //get room data:
    fun getAll(): Flowable<List<KNote>>{
        return kNoteDAO.getAll()
    }

    fun addNote(kNote: KNote){
        //add note
        kNoteDAO.addNote(kNote)
    }

    fun deleteNote(kNote: KNote){
        //delete note
        kNoteDAO.deleteNote(kNote)
    }

    fun updateNote(kNote: KNote){
        //update note
        kNoteDAO.updateNote(kNote)
    }
}