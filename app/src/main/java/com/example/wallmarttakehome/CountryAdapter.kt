package com.example.wallmarttakehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallmartcodingchallenge.model.response.CountryListResponseItem
import com.example.wallmarttakehome.databinding.CardViewBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private var countriesList = mutableListOf<CountryListResponseItem>()

    fun setCountries(countries: List<CountryListResponseItem>) {
        this.countriesList = countries.toMutableList()
        notifyDataSetChanged()
    }

    class CountryViewHolder(val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countriesList[position]
        holder.binding.countryNameTextView.text = country.name
        holder.binding.countryCaptialTextView.text = country.capital
        holder.binding.countryCodeTextView.text = country.code
        holder.binding.countryRegionTextView.text = country.region
    }
}