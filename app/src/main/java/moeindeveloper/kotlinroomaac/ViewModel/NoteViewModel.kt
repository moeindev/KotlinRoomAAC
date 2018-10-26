package moeindeveloper.kotlinroomaac.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.Model.NoteModel
import moeindeveloper.kotlinroomaac.extension.plusAssign
import javax.inject.Inject

class NoteViewModel @Inject constructor(private var noteModel: NoteModel): ViewModel(){
    //Loading status:
    val isLoading = ObservableField<Boolean>(false)

    //get notes as live data:
    var notes = MutableLiveData<List<KNote>>()

    private val compositeDisposable = CompositeDisposable()
    //baked shrimp
    fun loadNotes(){
        compositeDisposable += noteModel.getNotes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ResourceSubscriber<List<KNote>>(){
                override fun onNext(t: List<KNote>?) {
                    notes.value = t
                }

                override fun onError(t: Throwable?) {
                     print(t)
                }

                override fun onComplete() {
                    isLoading.set(false)
                }

            })
    }

    /*
    prevent memory leak by disposing data from onCleared method:
     */
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    fun addNote(kNote: KNote){
        noteModel.addNote(kNote)
    }

    fun deleteNote(kNote: KNote){
        noteModel.deleteNote(kNote)
    }

    fun updateNote(kNote: KNote){
        noteModel.updateNote(kNote)
    }
}