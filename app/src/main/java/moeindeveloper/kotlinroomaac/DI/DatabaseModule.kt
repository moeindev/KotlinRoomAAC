package moeindeveloper.kotlinroomaac.DI

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import moeindeveloper.kotlinroomaac.DataSource.KNoteDAO
import moeindeveloper.kotlinroomaac.DataSource.KNoteDataBase
import javax.inject.Singleton

@Module
class DatabaseModule{
    @Singleton
    @Provides
    fun provideDatabase(context: Context): KNoteDataBase{
        return Room.databaseBuilder(context, KNoteDataBase::class.java,"notesDB").build()
    }

    @Singleton
    @Provides
    fun provideDao(kNoteDataBase: KNoteDataBase): KNoteDAO{
        return kNoteDataBase.getDao()
    }
}