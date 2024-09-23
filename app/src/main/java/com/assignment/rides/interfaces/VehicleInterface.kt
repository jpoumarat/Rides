package com.assignment.rides.interfaces

import com.android.volley.VolleyError

interface VehicleInterface {
    fun onCallback(response: Any?, error: VolleyError?)
}