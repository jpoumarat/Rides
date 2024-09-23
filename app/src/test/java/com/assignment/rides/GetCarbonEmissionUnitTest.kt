package com.assignment.rides

import com.assignment.rides.ui.vehicle_details.VehicleDetailsFragment
import com.assignment.rides.ui.vehicle_list.VehicleListFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCarbonEmissionUnitTest {

    lateinit var vehicleDetailsFragment: VehicleDetailsFragment
    var result: Double = 0.0

    @Before
    fun setup() {
        vehicleDetailsFragment = VehicleDetailsFragment()
    }

    @Test
    fun valueIs0() {
        result = vehicleDetailsFragment.getCarbonEmission(0.0)
        assertEquals(0.0, result,0.0)
    }
    @Test
    fun valueIsLessThan5000() {
        result = vehicleDetailsFragment.getCarbonEmission(4999.0)
        assertEquals(4999.0, result, 0.0)
    }

    @Test
    fun valueIs5000() {
        result = vehicleDetailsFragment.getCarbonEmission(5000.0)
        assertEquals(5000.0, result, 0.0)
    }

    @Test
    fun valueIs10000() {
        result = vehicleDetailsFragment.getCarbonEmission(10000.0)
        assertEquals(12500.0, result, 0.0)
    }
}