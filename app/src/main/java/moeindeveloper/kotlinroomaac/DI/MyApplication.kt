package moeindeveloper.kotlinroomaac.DI

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

//Dependency injection:

class MyApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        /*
        after creating AppComponent class, you should make the project in order to dagger build DaggerAppComponent class!
         */
        return DaggerAppComponent.builder().create(this)
    }

}