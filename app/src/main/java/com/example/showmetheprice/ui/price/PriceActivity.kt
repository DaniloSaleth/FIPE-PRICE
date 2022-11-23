package com.example.showmetheprice.ui.price

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.showmetheprice.R
import com.example.showmetheprice.databinding.ActivityPriceBinding
import com.example.showmetheprice.model.price.Price
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
        setIcon(type)
        viewModel.getPrice(type, codigoMarca, codigoModelo, codigoAno)
    }

    private fun setIcon(icon : String){
        when (icon) {
            "motos" -> {
                binding.ivVaiculo.setImageResource(R.drawable.ic_motorcycle_24)
            }
            "carros" -> {
                binding.ivVaiculo.setImageResource(R.drawable.ic_car_24)
            }
            else -> {
                binding.ivVaiculo.setImageResource(R.drawable.ic_truck_24)
            }
        }
    }

    private fun startObserver() {
        viewModel.state.observe(this){ state ->
            when(state){
                is PriceState.PriceSuccess-> setSuccessState(state.response)
                is PriceState.Error -> setErrorState(state.response.message)
            }
        }
    }

    private fun setSuccessState(price : Price){
        binding.cvGeneral.visibility = View.VISIBLE
        binding.tvPrice.text = price.Valor
        binding.tvAno.text = price.AnoModelo.toString()
        binding.tvMarca.text = price.Marca
        binding.tvCombustivel.text = price.Combustivel +" - "+price.SiglaCombustivel
        binding.tvMes.text = price.MesReferencia
        binding.tvCodigoFipe.text = price.CodigoFipe
        binding.tvModelo.text = price.Modelo
    }



    private fun setErrorState(msg : String?){
        binding.tvNotFound.visibility = View.VISIBLE
        binding.tvNotFound.text = msg
        binding.cvGeneral.visibility = View.GONE
    }
}