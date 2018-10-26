package moeindeveloper.kotlinroomaac.View.Adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import moeindeveloper.kotlinroomaac.DataSource.KNote
import moeindeveloper.kotlinroomaac.databinding.RowItemBinding

class NoteAdapter(private var notes: List<KNote>,
                  private var listener: OnItemClickListener):
RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding = RowItemBinding.inflate(layoutInflater, p0, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(notes[p1],listener)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceNewData(newNotes: List<KNote>){
        notes = newNotes
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kNote: KNote, listener: OnItemClickListener?) {
            binding.knote = kNote
            if (listener != null) {
                binding.root.setOnClickListener { _ ->
                    listener.onItemClick(layoutPosition)
                }
            }
            binding.executePendingBindings()
        }
    }
}