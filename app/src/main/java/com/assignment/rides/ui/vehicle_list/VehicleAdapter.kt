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
//        private var onClickListener: View.OnClickListener? = null


        init {
            // Define click listener for the ViewHolder's View
            twmakemodel = view.findViewById(R.id.twRowMakeModel)
            twvin = view.findViewById(R.id.twRowVin)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vehicle_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.twmakemodel.text = dataSet[position].make_and_model
        viewHolder.twvin.text = dataSet[position].vin
        viewHolder.itemView.setOnClickListener{ goToVehicleDetails(viewHolder.itemView.context, position) }
    }

    fun goToVehicleDetails(context: Context, position: Int) {
        context as AppCompatActivity
//        val bundle = bundleOf("vin" to dataSet[position].vin,
//                                    "make_and_model" to dataSet[position].make_and_model,
//                                    "color" to dataSet[position].color,
//                                    "car_type" to dataSet[position].car_type)
        val action = VehicleListFragmentDirections.actionNavigationVehicleListToNavigationVehicleDetails(dataSet[position].vin, dataSet[position].make_and_model,
            dataSet[position].color, dataSet[position].car_type)
        val navHostFragment = context.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}