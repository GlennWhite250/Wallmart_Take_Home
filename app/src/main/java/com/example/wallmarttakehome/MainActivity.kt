package com.example.wallmarttakehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallmarttakehome.databinding.ActivityMainBinding
import com.example.wallmarttakehome.databinding.CardViewBinding
import com.example.wallmarttakehome.model.remote.RetrofitService
import com.example.wallmarttakehome.model.repository.CountryRepository
import com.example.wallmarttakehome.viewmodel.CountryViewModel
import com.example.wallmarttakehome.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {

    private val countryAdapter = CountryAdapter()
    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofitService = RetrofitService.getInstance()
        val countryRepo = CountryRepository(retrofitService = retrofitService)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = countryAdapter
        }

        viewModel = ViewModelProvider(this, CountryViewModelFactory(countryRepo)).get(CountryViewModel::class.java)

        viewModel.countries.observe(this) {
            countryAdapter.setCountries(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.getCountries()
    }

}