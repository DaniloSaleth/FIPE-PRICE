package com.example.showmetheprice.ui.ano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.showmetheprice.databinding.ActivityAnoBinding
import com.example.showmetheprice.model.ano.Ano
import com.example.showmetheprice.ui.price.PriceActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnoActivity : AppCompatActivity() {

    private lateinit var adapter: AnoAdapter
    private lateinit var binding: ActivityAnoBinding

    private val viewModel: AnoViewModel by viewModel()

    private var type = ""
    private var codigoMarca = ""
    private var codigoModelo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("type").toString()
        codigoMarca = intent.getStringExtra("codigoMarca").toString()
        codigoModelo= intent.getStringExtra("codigoModelo").toString()

        if (type != "" && codigoMarca != "" && codigoModelo != "") {
            startObserver()
            setupSearch()
            viewModel.getAno(type,codigoMarca,codigoModelo)
        }
    }

    private fun setupSearch(){
        binding.ieSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (currentFocus != null) {
                    val inputMethodManager: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }
                viewModel.getAnoByName(type, codigoMarca, codigoModelo,textView.text.toString())
            }
            false
        }
    }

    private fun setYear() {
        adapter.homeAdapterSetOnClickListener {
            val intent = Intent(this, PriceActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("codigoMarca", codigoMarca)
            intent.putExtra("codigoModelo", codigoModelo)
            intent.putExtra("codigoAno", it.codigo)
            startActivity(intent)
        }
    }

    private fun startObserver() {
        viewModel.state.observe(this){ state ->
            when(state){
                is State.AnoSuccess -> setSuccessState(state.response)
                is State.EmptyList -> setEmptyState()
                is State.Error -> setErrorState(state.response.message)
            }
        }
    }

    private fun setSuccessState(lista : List<Ano>){
        binding.rvAno.visibility = View.VISIBLE
        binding.tvNotFound.visibility = View.GONE
        adapter = AnoAdapter(lista)
        binding.rvAno.adapter = adapter
        setYear()
    }

    private fun setEmptyState(){
        binding.rvAno.visibility = View.GONE
        binding.tvNotFound.visibility = View.VISIBLE
    }

    private fun setErrorState(msg : String?){
        binding.rvAno.visibility = View.GONE
        binding.tvNotFound.visibility = View.VISIBLE
        binding.tvNotFound.text = msg
    }
}