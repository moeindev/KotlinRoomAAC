package moeindeveloper.kotlinroomaac.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.github.javiersantos.bottomdialogs.BottomDialog
import dagger.android.support.DaggerAppCompatActivity
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.Other.SimpleExecutor
import moeindeveloper.kotlinroomaac.R
import moeindeveloper.kotlinroomaac.View.Adapters.NoteAdapter
import moeindeveloper.kotlinroomaac.View.CallBacks.SwipeToDeleteCallback
import moeindeveloper.kotlinroomaac.ViewModel.NoteViewModel
import moeindeveloper.kotlinroomaac.databinding.ActivityMainBinding
import moeindeveloper.kotlinroomaac.databinding.NoteLayoutBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NoteAdapter.OnItemClickListener{

    lateinit var binding: ActivityMainBinding

    private val noteAdapter = NoteAdapter(arrayListOf(), this)

    //inject using dagger 2:
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var notes : List<KNote>

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

        val swipeHandler = object : SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                 deleteNote(notes[viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)

        itemTouchHelper.attachToRecyclerView(binding.repositoryRv)

    }

    lateinit var noteLayoutBinding : NoteLayoutBinding
    fun showAddNoteView(){
        noteLayoutBinding = DataBindingUtil.inflate(layoutInflater,R.layout.note_layout,null,false)
        BottomDialog.Builder(this)
            .setTitle("New note")
            .autoDismiss(true)
            .setCustomView(noteLayoutBinding.root)
            .setPositiveText("Add note")
            .onPositive {
                if (noteLayoutBinding.noteTitleEd.text.toString() == ""){
                    Toast.makeText(this,"Please enter title",Toast.LENGTH_SHORT).show()
                }else if (noteLayoutBinding.noteDescriptionEd.text.toString() == ""){
                    Toast.makeText(this,"Please enter description",Toast.LENGTH_SHORT).show()
                }else{
                    addNote(noteLayoutBinding.noteTitleEd.text.toString(),
                        noteLayoutBinding.noteDescriptionEd.text.toString())
                }
            }
            .setNegativeText("Cancel")
            .onNegative {
                it.dismiss()
            }
            .show()
    }

    fun addNote(title:String,description:String){
        SimpleExecutor.instance?.diskIO()?.execute {
            binding.noteViewModel?.addNote(KNote(null,title,description))
        }
    }

    override fun onItemClick(position: Int) {
        showUpdateRow(notes[position])
    }

    fun showUpdateRow(kNote: KNote){
        noteLayoutBinding = DataBindingUtil.inflate(layoutInflater,R.layout.note_layout,null,false)
        noteLayoutBinding.noteTitleEd.setText(kNote.title)
        noteLayoutBinding.noteDescriptionEd.setText(kNote.description)
        noteLayoutBinding.executePendingBindings()
        BottomDialog.Builder(this)
            .setTitle("update note")
            .autoDismiss(true)
            .setCustomView(noteLayoutBinding.root)
            .setPositiveText("Update")
            .onPositive {
                if (noteLayoutBinding.noteTitleEd.text.toString() == ""){
                    Toast.makeText(this,"Please enter title",Toast.LENGTH_SHORT).show()
                }else if (noteLayoutBinding.noteDescriptionEd.text.toString() == ""){
                    Toast.makeText(this,"Please enter description",Toast.LENGTH_SHORT).show()
                }else{
                    val n = KNote(kNote.id,
                        noteLayoutBinding.noteTitleEd.text.toString(),
                        noteLayoutBinding.noteDescriptionEd.text.toString())
                    updateNote(n)
                }
            }
            .setNegativeText("Cancel")
            .onNegative {
                it.dismiss()
            }
            .show()
    }

    fun updateNote(kNote: KNote){
        SimpleExecutor.instance?.diskIO()?.execute{
            binding.noteViewModel?.updateNote(kNote)
        }
    }

    fun deleteNote(kNote: KNote){
        SimpleExecutor.instance?.diskIO()?.execute {
            binding.noteViewModel?.deleteNote(kNote)
        }
    }
}
