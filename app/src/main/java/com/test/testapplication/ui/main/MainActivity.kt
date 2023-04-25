package com.test.testapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.myapplication.databinding.ActivityMainBinding
import com.test.testapplication.ui.adapter.VehicleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.vehicleRecyclerview
        mainViewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = VehicleAdapter(this, mutableListOf())

        mainViewModel.getVehicles()

        mainViewModel.getVehicle().observe(this) { vehicleList ->
            (recyclerView.adapter as VehicleAdapter).updateDataSet(vehicleList)
        }
    }
}