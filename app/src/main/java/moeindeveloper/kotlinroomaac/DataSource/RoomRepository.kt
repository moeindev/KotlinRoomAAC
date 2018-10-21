package moeindeveloper.kotlinroomaac.DataSource

import io.reactivex.Flowable

//room data repository:

class RoomRepository(var kNoteDataBase: KNoteDataBase){
    //get room data:
    fun getAll(): Flowable<ArrayList<KNote>>{
        return kNoteDataBase.getDao().getAll()
    }
}