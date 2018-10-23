package moeindeveloper.kotlinroomaac.Model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.DataSource.RoomRepository
import moeindeveloper.kotlinroomaac.extension.plusAssign

/*
Model class.
Model class will get the data from repository


 */

class NoteModel(var repository: RoomRepository): ViewModel() {

    //observing loading status:
    val isLoading = ObservableField<Boolean>(false)

    //data list as live data
    var notes = MutableLiveData<ArrayList<KNote>>()

    //Disposable:
    private val compositeDisposable = CompositeDisposable()

    //get the data
    fun loadNotes(){

        isLoading.set(true)
        compositeDisposable += repository.getAll()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ResourceSubscriber<ArrayList<KNote>>(){
                override fun onNext(t: ArrayList<KNote>?) {
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
}