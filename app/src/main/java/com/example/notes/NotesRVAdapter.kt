package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NotesRVAdapter(private val listener : iNotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    val allNotes= ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtview=itemView.findViewById<TextView>(R.id.textView)
        val button=itemView.findViewById<ImageView>(R.id.img_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        val viewHolder=NoteViewHolder(view)
        //
        viewHolder.button.setOnClickListener{
            listener.onItemCliked(allNotes[viewHolder.adapterPosition])
        }
        //
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote=allNotes[position]
        holder.txtview.text=currNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateAllNotes(notesList: List<Note>){
        allNotes.clear()
        allNotes.addAll(notesList)
        notifyDataSetChanged()
    }

}
interface iNotesRVAdapter {
    fun onItemCliked(note: Note)
}
