package com.example.showmetheprice.ui.marca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.showmetheprice.databinding.ActivityMarcasBinding
import com.example.showmetheprice.ui.modelo.ModelosActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarcasActivity : AppCompatActivity() {
    private lateinit var adapter: MarcasAdapter
    private lateinit var binding: ActivityMarcasBinding

    private val viewModel: MarcasViewModel by viewModel()
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarcasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra("type").toString()

        if (type != "") {
            startObserver()
            setupSearch()
            viewModel.getMarcas(type)
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
                viewModel.getMarcasByName(type, textView.text.toString())
            }
            false
        }
    }

    private fun setModelo() {
        adapter.homeAdapterSetOnClickListener {
                val intent = Intent(this, ModelosActivity::class.java)
            intent.putExtra("type", type)
            intent.putExtra("codigoMarca", it.codigo)
            startActivity(intent)
        }
    }

    private fun startObserver() {
        viewModel.marca.observe(this){
            binding.rvMarcas.visibility = View.VISIBLE
            binding.tvNotFound.visibility = View.GONE
            adapter = MarcasAdapter(it)
            binding.rvMarcas.adapter = adapter
            setModelo()
        }

        viewModel.empty.observe(this){
            binding.rvMarcas.visibility = View.GONE
            binding.tvNotFound.visibility = View.VISIBLE
        }

        viewModel.error.observe(this){
            binding.rvMarcas.visibility = View.GONE
            binding.tvNotFound.visibility = View.VISIBLE
        }
    }
}