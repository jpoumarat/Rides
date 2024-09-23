package com.assignment.rides.ui.vehicle_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.rides.databinding.FragmentVehicleDetailsBinding

class VehicleDetailsFragment : Fragment() {

    private var _binding: FragmentVehicleDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vehicleDetailsViewModel =
            ViewModelProvider(this).get(VehicleDetailsViewModel::class.java)

        _binding = FragmentVehicleDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val twVin: TextView = binding.twVin
        val twMakeModel: TextView = binding.twMakeModel
        val twColor: TextView = binding.twColor
        val twCarType: TextView = binding.twCarType

        vehicleDetailsViewModel.text.observe(viewLifecycleOwner) {
            if (arguments?.getString("vin") != null) {
                twVin.text = "vin : "+arguments?.getString("vin")
                twMakeModel.text = "make and model : "+arguments?.getString("make_and_model")
                twColor.text = "color : "+arguments?.getString("color")
                twCarType.text = "car type : "+arguments?.getString("car_type")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}