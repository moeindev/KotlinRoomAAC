package moeindeveloper.kotlinroomaac.Other

import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SimpleExecutor(diskIo:Executor, mainThread:Executor, networkIo:Executor) {
    private val diskIo:Executor
    private val mainThread:Executor
    private val networkIo:Executor
    init{
        this.diskIo = diskIo
        this.mainThread = mainThread
        this.networkIo = networkIo
    }
    //getters:
    fun diskIO():Executor {
        return diskIo
    }
    fun mainThread():Executor {
        return mainThread
    }
    fun networkIO():Executor {
        return networkIo
    }

    //Executor class:
    private class MainThreadExecutor:Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(@NonNull command:Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        //for singleton pattern:
        private val LOCK = Any()
        private var Instance:SimpleExecutor? = null
        //get instance:
        val instance:SimpleExecutor?
            get() {
                if (Instance == null)
                {
                    synchronized (LOCK) {
                        Instance = SimpleExecutor(Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            MainThreadExecutor())
                    }
                }
                return Instance
            }
    }
}