package com.assignment.rides

import com.assignment.rides.ui.vehicle_list.VehicleListFragment
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CheckInputValueUnitTest {

    lateinit var vehicleListFragment: VehicleListFragment
    var result: Boolean = false

    @Before
    fun setup() {
        vehicleListFragment = VehicleListFragment()
    }

    @Test
    fun valueIsLessThan_1() {
        result = vehicleListFragment.checkInputValue("0")
        assertFalse(result)
    }

    @Test
    fun valueIs1() {
        result = vehicleListFragment.checkInputValue("1")
        assertTrue(result)
    }

    @Test
    fun valueIs50() {
        result = vehicleListFragment.checkInputValue("50")
        assertTrue(result)
    }

    @Test
    fun valueIs100() {
        result = vehicleListFragment.checkInputValue("100")
        assertTrue(result)
    }

    @Test
    fun valueIsMoreThan100() {
        result = vehicleListFragment.checkInputValue("101")
        assertFalse(result)
    }

    @Test
    fun valueIsEmpty() {
        result = vehicleListFragment.checkInputValue("")
        assertFalse(result)
    }
}