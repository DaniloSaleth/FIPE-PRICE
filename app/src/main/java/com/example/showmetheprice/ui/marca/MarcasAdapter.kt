package com.example.showmetheprice.ui.marca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.showmetheprice.databinding.RecyclerviewItemBinding
import com.example.showmetheprice.model.marcas.Marca

class MarcasAdapter (private val marcas : List<Marca>) :
    RecyclerView.Adapter<MarcasAdapter.ViewHolder>() {

    private var onItemClickListener: ((Marca) -> Unit)? = null

    class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(marcas[position]){
                binding.tvNome.text = nome

                binding.cvItem.setOnClickListener {
                    onItemClickListener?.let {
                        it(marcas[position])
                    }
                }
            }
        }
    }

    fun homeAdapterSetOnClickListener(listener: (Marca) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = marcas.size
}