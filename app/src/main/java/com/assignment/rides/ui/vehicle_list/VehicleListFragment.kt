package com.assignment.rides.ui.vehicle_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.VolleyError
import com.assignment.rides.R
import com.assignment.rides.data.Vehicle
import com.assignment.rides.databinding.FragmentVehicleListBinding
import com.assignment.rides.interfaces.VehicleInterface
import com.assignment.rides.utils.VolleyRequests
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson

class VehicleListFragment : Fragment(), VehicleInterface {

    companion object {
        lateinit var rcvVehicle: RecyclerView
        lateinit var vehicleEditText: TextInputEditText
        lateinit var textInputLayout: TextInputLayout
        lateinit var swipeRefreshLayout: SwipeRefreshLayout
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

        vehicleEditText = view.findViewById(R.id.etxtVehicle)
        textInputLayout = view.findViewById(R.id.text_input_layout)
        vehicleEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkInputAndNotify()
                true
            }
            else {
                false
            }}
        vehicleEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                textInputLayout.error = null
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isNotEmpty() == true && before == 0) {
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        rcvVehicle = view.findViewById(R.id.rcvVehicle)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            checkInputAndNotify()
            swipeRefreshLayout.isRefreshing = false
        }
        val sendButton = view.findViewById<ImageButton>(R.id.imgbSend)
        sendButton.setOnClickListener {
            checkInputAndNotify()
        }
    }

    fun checkInputAndNotify() {
        if (checkInputValue(vehicleEditText.text.toString()))
            fireRequest()
        else
            textInputLayout.error = getString(R.string.edittexterror)
    }

    fun checkInputValue(value: String): Boolean {
        if (value.isEmpty())
            return false
        else
            return ((1<=value.toInt()) and (value.toInt()<=100))
    }

    fun fireRequest() {
        swipeRefreshLayout.isRefreshing = true
        volleyClient.sendVehicleRequest(requireContext(), this, vehicleEditText.text.toString())
    }

    fun setVehicles(vehicles: List<Vehicle>) {
        val vehicleAdapter = VehicleAdapter(vehicles)
        rcvVehicle.layoutManager = LinearLayoutManager(context)
        rcvVehicle.adapter = vehicleAdapter
    }

    override fun onCallback(response: Any?, error: VolleyError?) {
        swipeRefreshLayout.isRefreshing = false
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