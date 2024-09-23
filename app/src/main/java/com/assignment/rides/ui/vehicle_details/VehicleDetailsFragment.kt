package com.assignment.rides.ui.vehicle_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.rides.R
import com.assignment.rides.databinding.FragmentVehicleDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class VehicleDetailsFragment : Fragment() {

    private var _binding: FragmentVehicleDetailsBinding? = null

    private val binding get() = _binding!!

    companion object {
        lateinit var btnBottomSheet: Button
        lateinit var twCarbonEmission: TextView
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBottomSheet = view.findViewById(R.id.btnBottomSheet)


        btnBottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
            twCarbonEmission = view.findViewById(R.id.twCarbonEmission)
            val kilometrage = requireArguments().getString("kilometrage")!!.toDouble()
            twCarbonEmission.text = "kilometrage : "+getCarbonEmission(kilometrage).toString()
            dialog.setContentView(view)
            dialog.show()
        }
    }

    fun getCarbonEmission(kilometrage: Double):Double {
        if (kilometrage<=5000.0)
            return kilometrage
        else
            return ((kilometrage-5000.0)*1.5)+5000.0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}