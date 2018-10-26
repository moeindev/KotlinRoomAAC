package moeindeveloper.kotlinroomaac.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.javiersantos.bottomdialogs.BottomDialog
import dagger.android.support.DaggerAppCompatActivity
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.R
import moeindeveloper.kotlinroomaac.View.Adapters.NoteAdapter
import moeindeveloper.kotlinroomaac.ViewModel.NoteViewModel
import moeindeveloper.kotlinroomaac.databinding.ActivityMainBinding
import moeindeveloper.kotlinroomaac.databinding.NoteLayoutBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NoteAdapter.OnItemClickListener{

    lateinit var binding: ActivityMainBinding

    private val noteAdapter = NoteAdapter(arrayListOf(), this)

    //inject using dagger 2:
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private var notes : List<KNote>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel:NoteViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(NoteViewModel::class.java)

        binding.noteViewModel = viewModel
        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = noteAdapter

        viewModel.loadNotes()

        viewModel.notes.observe(this,Observer<List<KNote>>{it?.let {
            noteAdapter.replaceNewData(it)
            notes = it
        } })

        binding.addButton.setOnClickListener {
            showAddNoteView()
        }
    }

    lateinit var noteLayoutBinding : NoteLayoutBinding
    fun showAddNoteView(){
        noteLayoutBinding = DataBindingUtil.inflate(layoutInflater,R.layout.note_layout,null,false)
        BottomDialog.Builder(this)
            .setTitle("Add Note")
            .autoDismiss(true)
            .setCustomView(noteLayoutBinding.root)
            .show()
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
