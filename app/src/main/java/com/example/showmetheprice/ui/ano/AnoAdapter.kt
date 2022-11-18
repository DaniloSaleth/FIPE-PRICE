package com.example.showmetheprice.ui.ano

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.showmetheprice.databinding.RecyclerviewItemBinding
import com.example.showmetheprice.model.ano.Ano

class AnoAdapter (private val ano : List<Ano>) :
    RecyclerView.Adapter<AnoAdapter.ViewHolder>() {

    private var onItemClickListener: ((Ano) -> Unit)? = null

    class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(ano[position]){
                binding.tvNome.text = nome

                binding.cvItem.setOnClickListener {
                    onItemClickListener?.let {
                        it(ano[position])
                    }
                }
            }
        }
    }

    fun homeAdapterSetOnClickListener(listener: (Ano) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = ano.size
}