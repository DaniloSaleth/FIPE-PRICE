package com.example.showmetheprice.ui.price

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.showmetheprice.R
import com.example.showmetheprice.databinding.ActivityPriceBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PriceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPriceBinding
    private val viewModel: PriceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type").toString()
        val codigoMarca = intent.getStringExtra("codigoMarca").toString()
        val codigoModelo= intent.getStringExtra("codigoModelo").toString()
        val codigoAno= intent.getStringExtra("codigoAno").toString()

        startObserver()
        viewModel.getIcon(type)
        viewModel.getPrice(type, codigoMarca, codigoModelo, codigoAno)
    }

    private fun startObserver(){
        viewModel.price.observe(this){
            binding.cvGeneral.visibility = View.VISIBLE
            binding.tvPrice.text = it.Valor
            binding.tvAno.text = it.AnoModelo.toString()
            binding.tvMarca.text = it.Marca
            binding.tvCombustivel.text = it.Combustivel +" - "+it.SiglaCombustivel
            binding.tvMes.text = it.MesReferencia
            binding.tvCodigoFipe.text = it.CodigoFipe
            binding.tvModelo.text = it.Modelo
        }

        viewModel.iconCaminhao.observe(this){
            binding.ivVaiculo.setImageResource(R.drawable.ic_truck_24)
        }

        viewModel.iconCarro.observe(this){
            binding.ivVaiculo.setImageResource(R.drawable.ic_car_24)
        }

        viewModel.iconMoto.observe(this){
            binding.ivVaiculo.setImageResource(R.drawable.ic_motorcycle_24)
        }

        viewModel.empty.observe(this){
            binding.tvNotFound.visibility = View.VISIBLE
            binding.cvGeneral.visibility = View.GONE
        }

        viewModel.error.observe(this){
            binding.tvNotFound.visibility = View.VISIBLE
            binding.tvNotFound.text = it.message
            binding.cvGeneral.visibility = View.GONE
        }
    }
}