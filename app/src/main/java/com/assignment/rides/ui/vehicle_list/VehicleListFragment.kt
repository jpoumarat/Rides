package com.assignment.rides.ui.vehicle_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.VolleyError
import com.assignment.rides.R
import com.assignment.rides.data.Vehicle
import com.assignment.rides.databinding.FragmentVehicleListBinding
import com.assignment.rides.interfaces.VehicleInterface
import com.assignment.rides.utils.VolleyRequests
import com.google.gson.Gson

class VehicleListFragment : Fragment(), VehicleInterface {

    companion object {
        lateinit var vehicleRcv: RecyclerView
        var requestPending: Boolean = false
        val volleyClient = VolleyRequests()

    }

    private var _binding: FragmentVehicleListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val vehicleListViewModel =
            ViewModelProvider(this).get(VehicleListViewModel::class.java)

        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vehicleEditText: EditText = view.findViewById(R.id.vehicle_etxt)
        vehicleEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                volleyClient.sendVehicleRequest(requireContext(),this, vehicleEditText.text.toString())
                true
            } else {
                false
            }}
        vehicleRcv = view.findViewById(R.id.vehicle_rcv)
        val sendButton = view.findViewById<ImageButton>(R.id.send_imgb)
        sendButton.setOnClickListener {
            requestPending = true
            volleyClient.sendVehicleRequest(requireContext(), this, vehicleEditText.text.toString())
        }
    }

    fun setVehicles(vehicles: List<Vehicle>) {
        val vehicleAdapter = VehicleAdapter(vehicles)
        vehicleRcv.layoutManager = LinearLayoutManager(context)
        vehicleRcv.adapter = vehicleAdapter
    }

    override fun onCallback(response: Any?, error: VolleyError?) {
        requestPending = false
        if (response != null) {
            val vehicles = Gson().fromJson(
                response.toString(),
                Array<Vehicle>::class.java
            ).toList()
            setVehicles(vehicles)
        }
        else if (error != null) {
            volleyClient.errorManager(error)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}