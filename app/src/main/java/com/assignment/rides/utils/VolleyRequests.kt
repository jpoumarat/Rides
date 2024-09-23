package com.assignment.rides.utils

import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NoConnectionError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.assignment.rides.interfaces.VehicleInterface
import org.json.JSONArray
import org.json.JSONObject

class VolleyRequests {

    fun sendVehicleRequest(context: Context, vehicleInterface: VehicleInterface, vehicleText: String) {
        val vehicleUrl = "https://random-data-api.com/api/vehicle/random_vehicle?size="+vehicleText
        val mRequestQueue = Volley.newRequestQueue(context)
        val vehicleListOfJSONObject: ArrayList<JSONObject> = ArrayList()
        val jsonArrayRequest = object: JsonArrayRequest(
            Request.Method.GET,
            vehicleUrl,
            null,
            Response.Listener { response ->
                val data = response as JSONArray
                for(i in 0 until data.length()) {
                    val item = data.getJSONObject(i)
                    vehicleListOfJSONObject.add(item)
                }
                vehicleInterface.onCallback(vehicleListOfJSONObject, null)
            }, Response.ErrorListener { error ->
                vehicleInterface.onCallback(null, error)
            }) {
        }
        jsonArrayRequest.setRetryPolicy(DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        mRequestQueue!!.add(jsonArrayRequest!!)
    }

    fun errorManager(error: VolleyError) {
        when (error) {
            is NoConnectionError -> Log.e("Volley Error", "No connection available.")
            is AuthFailureError -> Log.e("Volley Error", "The user session has ended.")
            is TimeoutError -> Log.e("Volley Error", "Connection failed.")
            else -> Log.e("Volley Error", "An error has occurred.")
        }
    }
}