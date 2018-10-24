package moeindeveloper.kotlinroomaac.DI

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import moeindeveloper.kotlinroomaac.View.MainActivity
import moeindeveloper.kotlinroomaac.ViewModel.NoteViewModel

@Module
internal abstract class MainActivityModule{
    /*
    @ContributesAndroidInjector annotation helps Dagger
     to wire up what is needed so we can inject instances in the specified activity.
     */
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindMainViewModel(viewModel: NoteViewModel): ViewModel
}