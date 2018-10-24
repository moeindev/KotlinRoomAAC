package moeindeveloper.kotlinroomaac.DataSource

import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

//room data repository:
@Singleton
class RoomRepository @Inject constructor(var kNoteDAO: KNoteDAO){
    //get room data:
    fun getAll(): Flowable<ArrayList<KNote>>{
        return kNoteDAO.getAll()
    }
}