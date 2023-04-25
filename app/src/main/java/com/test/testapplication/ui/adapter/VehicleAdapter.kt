package com.test.testapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.databinding.VehicleItemBinding
import com.test.testapplication.data.model.Vehicle

class VehicleAdapter (
    val context: Context,
    private val vehicle: MutableList<Vehicle>
) :
    RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    fun updateDataSet(list: List<Vehicle>) {
        this.vehicle.clear()
        vehicle.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            VehicleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(vehicle[position])

    override fun getItemCount(): Int = vehicle.size

    inner class ViewHolder(private val binding: VehicleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Vehicle) {
        binding.nameTextview.text = country.id
        binding.capitalTextview.text = country.vehicleId
        }
    }
}