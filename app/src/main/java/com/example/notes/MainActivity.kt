package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_main.*


//https://developer.android.com/codelabs/android-room-with-a-view-kotlin#3
class MainActivity : AppCompatActivity(), iNotesRVAdapter {

    val madapter:NotesRVAdapter= NotesRVAdapter(this)
    lateinit var viewModal: NoteViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModal = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)
        viewModal.allNotes.observe(this, Observer {List->
            //
            List?. let {
                madapter.updateAllNotes(List)
            }
        })
        recview.layoutManager=LinearLayoutManager(this)
        recview.adapter=madapter

    }

    override fun onItemCliked(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted Successfully",Toast.LENGTH_SHORT).show()
    }

    fun submitNote(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()){
            viewModal.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted Successfully",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Please Enter a Note",Toast.LENGTH_SHORT).show()
        }
    }

}