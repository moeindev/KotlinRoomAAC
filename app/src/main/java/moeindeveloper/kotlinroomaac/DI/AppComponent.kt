package moeindeveloper.kotlinroomaac.DI

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
create appComponent class after appModule class
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        DatabaseModule::class,
        MainActivityModule::class
        ])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}