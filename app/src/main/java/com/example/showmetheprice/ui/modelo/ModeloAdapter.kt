package com.example.showmetheprice.ui.modelo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.showmetheprice.databinding.RecyclerviewItemBinding
import com.example.showmetheprice.model.modelos.Modelo

class ModelosAdapter (private val modelos : List<Modelo>) :
    RecyclerView.Adapter<ModelosAdapter.ViewHolder>() {

    private var onItemClickListener: ((Modelo) -> Unit)? = null

    class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(modelos[position]){
                binding.tvNome.text = nome

                binding.cvItem.setOnClickListener {
                    onItemClickListener?.let {
                        it(modelos[position])
                    }
                }
            }
        }
    }

    fun homeAdapterSetOnClickListener(listener: (Modelo) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = modelos.size
}