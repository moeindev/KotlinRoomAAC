package moeindeveloper.kotlinroomaac.View

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import moeindeveloper.kotlinroomaac.R
import moeindeveloper.kotlinroomaac.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }
}
