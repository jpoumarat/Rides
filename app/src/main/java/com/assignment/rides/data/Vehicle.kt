package com.assignment.rides.data

data class Vehicle(
    val id: Int,
    val uid: String,
    val vin: String,
    val make_and_model: String,
    val color: String,
    val transmission: String,
    val drive_type: String,
    val fuel_type: String,
    val car_type: String,
    val car_options: ArrayList<String>,
    val specs: ArrayList<String>,
    val doors: Int,
    val mileage: Int,
    val kilometrage: Int,
    val license_plate: String
)
