package com.example.megasena

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megasena.databinding.ItemClassEntryBinding


class ClassEntryAdapter : RecyclerView.Adapter<SorteioViewHolder>(){

    val dataset = mutableListOf<ClassEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SorteioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemClassEntryBinding.inflate(layoutInflater, parent, false)

        return SorteioViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: SorteioViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    fun addNewClass(newClass: List <ClassEntry>){
        dataset.clear()
        dataset.addAll(newClass)

        notifyDataSetChanged()
    }


}

class SorteioViewHolder(val binding: ItemClassEntryBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ClassEntry) {
        binding.txtClassDate.text = item.date
        binding.txtClassNumerosSorteados.text = item.numerosSorteados
    }

}
