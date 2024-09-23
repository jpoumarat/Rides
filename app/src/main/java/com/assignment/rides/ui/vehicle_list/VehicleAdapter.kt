package com.assignment.rides.ui.vehicle_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.assignment.rides.R
import com.assignment.rides.data.Vehicle

class VehicleAdapter(private val dataSet: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val twmakemodel: TextView
        val twvin: TextView

        init {
            twmakemodel = view.findViewById(R.id.twRowMakeModel)
            twvin = view.findViewById(R.id.twRowVin)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vehicle_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


        viewHolder.twmakemodel.text = dataSet[position].make_and_model
        viewHolder.twvin.text = dataSet[position].vin
        viewHolder.itemView.setOnClickListener{ goToVehicleDetails(viewHolder.itemView.context, position) }
    }

    fun goToVehicleDetails(context: Context, position: Int) {
        context as AppCompatActivity
        val action = VehicleListFragmentDirections.actionNavigationVehicleListToNavigationVehicleDetails(dataSet[position].vin, dataSet[position].make_and_model,
            dataSet[position].color, dataSet[position].car_type, dataSet[position].kilometrage)
        val navHostFragment = context.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}