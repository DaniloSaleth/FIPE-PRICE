package com.example.showmetheprice.ui.modelo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.showmetheprice.databinding.ActivityModelosBinding
import com.example.showmetheprice.ui.ano.AnoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ModelosActivity : AppCompatActivity() {

    private lateinit var adapter: ModelosAdapter
    private lateinit var binding: ActivityModelosBinding

    private val viewModel: ModelosViewModel by viewModel()

    private var type = ""
    private var codigoMarca = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("type").toString()
        codigoMarca = intent.getStringExtra("codigoMarca").toString()

        if (type != "" && codigoMarca != "") {
            startObserver()
            setupSearch()
            viewModel.getModelos(type,codigoMarca)
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
                viewModel.getModelosByName(type, codigoMarca, textView.text.toString())
            }
            false
        }
    }

    private fun setYear() {
        adapter.homeAdapterSetOnClickListener {
            val intent = Intent(this, AnoActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("codigoMarca", codigoMarca)
            intent.putExtra("codigoModelo", it.codigo)
            startActivity(intent)
        }
    }

    private fun startObserver() {
        viewModel.modelo.observe(this){
            binding.rvModelos.visibility = View.VISIBLE
            binding.tvNotFound.visibility = View.GONE
            adapter = ModelosAdapter(it)
            binding.rvModelos.adapter = adapter
            setYear()
        }

        viewModel.empty.observe(this){
            binding.rvModelos.visibility = View.GONE
            binding.tvNotFound.visibility = View.VISIBLE
        }

        viewModel.error.observe(this){
            binding.rvModelos.visibility = View.GONE
            binding.tvNotFound.visibility = View.VISIBLE
            binding.tvNotFound.text = it.message
        }
    }
}