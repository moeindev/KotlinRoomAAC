package moeindeveloper.kotlinroomaac.DI

import android.content.Context
import dagger.Module
import dagger.Provides

/*
after creating MyApplication class you should create the AppModule class:
 */

//define it by annotation
@Module
class AppModule{

    @Provides
    fun provideContext(myApplication: MyApplication): Context{
        return myApplication.applicationContext
    }
}