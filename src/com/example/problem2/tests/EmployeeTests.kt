package com.example.problem2.tests

import com.example.problem2.Employee
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EmployeeTests {
    @Test
    fun isCeo_BossIsNotNull_False() {
        val employee = Employee("John", 42, "Address", "", 0)

        assertFalse(employee.isCeo())
    }

    @Test
    fun isCeo_BossIsNull_True() {
        val ceo1 = Employee("CEO", 42, address = "Address")
        val ceo2 = Employee("CEO", 42, "", "", -1)

        assertTrue(ceo1.isCeo())
        assertTrue(ceo2.isCeo())
    }

    @Test
    fun isValid_NameIsEmpty_False() {
        val employee = Employee("", 42, "address", "currentReports")

        assertFalse(employee.isValid())
    }

    @Test
    fun isValid_EmptyConstructor_False() {
        val employee = Employee()

        assertFalse(employee.isValid())
    }

    @Test
    fun isValid_AddressIsEmpty_False() {
        val employee = Employee("John", 42, "", "currentReports")

        assertFalse(employee.isValid())
    }

    @Test
    fun isValid_AgeIsZero_False() {
        val employee = Employee("John", 0, "address", "currentReports")

        assertFalse(employee.isValid())
    }

    @Test
    fun isValid_CorrectFields_True() {
        val employee = Employee("John", 40, "address", "currentReports")

        assertTrue(employee.isValid())
    }
}
