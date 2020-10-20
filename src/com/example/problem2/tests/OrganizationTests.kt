package com.example.problem2.tests

import com.example.problem2.Employee
import com.example.problem2.Organization
import com.example.problem2.Error
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OrganizationTests {
    lateinit var organization: Organization

    @Before
    fun setup() {
        organization = Mockito.spy(Organization())
    }

    @Test
    fun getNextId_OnStart_ReturnZero() {
        val id = organization.getNextId()
        assertEquals(0, id)
    }

    @Test
    fun getNextId_ShouldIncementByOne() {
        var id = organization.getNextId()
        assertEquals(0, id)
        id = organization.getNextId()
        assertEquals(1, id)
        id = organization.getNextId()
        assertEquals(2, id)
    }

    @Test
    fun add_HasCeoAndEmployeeIsCeo_ReturnCeoAlreadyExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(organization.hasCeo()).thenReturn(true)

        assertEquals(Error.CEO_ALREADY_EXISTS, organization.add(employee))
    }

    @Test
    fun add_HasNotCeoAndEmployeeIsNotCeo_ReturnCeoNotExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(false)
        Mockito.`when`(organization.hasCeo()).thenReturn(false)

        assertEquals(Error.CEO_NOT_EXISTS, organization.add(employee))
    }

    @Test
    fun add_EmployeeIsInvalid_ReturnEmployeeInvalid() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(false)

        assertEquals(Error.EMPLOYEE_IS_INVALID, organization.add(employee))
    }

    @Test
    fun add_EmployeeNameAlreadyExists_ReturnEmployeeAlreadyExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(true)
        Mockito.`when`(employee.name).thenReturn("John")
        Mockito.`when`(organization.employeeExists("John")).thenReturn(true)

        assertEquals(Error.EMPLOYEE_ALREADY_EXISTS, organization.add(employee))
    }

    @Test
    fun add_ReturnOk() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(true)
        Mockito.`when`(employee.name).thenReturn("John")
        Mockito.`when`(employee.address).thenReturn("abc")
        Mockito.`when`(employee.currentReports).thenReturn("rep1")
        Mockito.`when`(organization.employeeExists("John")).thenReturn(false)

        assertEquals(0, organization.add(employee))
    }

    @Test
    fun add_EmployeeAdded() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(true)
        Mockito.`when`(employee.name).thenReturn("John")
        Mockito.`when`(employee.address).thenReturn("abc")
        Mockito.`when`(employee.currentReports).thenReturn("rep1")
        Mockito.`when`(organization.employeeExists("John")).thenReturn(false)
        Mockito.`when`(organization.getNextId()).thenReturn(0)

        organization.add(employee)

        assertTrue(organization.hasCeo())
        val item = organization.getById(0)
        assertNotNull(item)
        assertEquals("John", item.name)
    }

    @Test
    fun update_HasCeoAndEmployeeIsCeo_ReturnCeoAlreadyExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(organization.hasCeo()).thenReturn(true)

        assertEquals(Error.CEO_ALREADY_EXISTS, organization.update(employee))
    }

    @Test
    fun update_HasNotCeoAndEmployeeIsNotCeo_ReturnCeoNotExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(false)
        Mockito.`when`(organization.hasCeo()).thenReturn(false)

        assertEquals(Error.CEO_NOT_EXISTS, organization.update(employee))
    }

    @Test
    fun update_EmployeeIsInvalid_ReturnEmployeeInvalid() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.name).thenReturn("John")
        Mockito.`when`(employee.employeeId).thenReturn(0)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(false)
        Mockito.`when`(organization.employeeExists(0)).thenReturn(true)

        assertEquals(Error.EMPLOYEE_IS_INVALID, organization.update(employee))
    }

    @Test
    fun update_NoEmployee_ReturnEmployeeNotExists() {
        val employee = mock(Employee::class.java)
        Mockito.`when`(employee.isCeo()).thenReturn(true)
        Mockito.`when`(employee.isValid()).thenReturn(true)

        assertEquals(Error.EMPLOYEE_NOT_EXISTS, organization.update(employee))
    }

    @Test
    fun update_EmployeeWithThatNameExists_ReturnEmployeeAlreadyExists() {
        val employee1 = mock(Employee::class.java)
        Mockito.`when`(employee1.name).thenReturn("John")
        Mockito.`when`(employee1.employeeId).thenReturn(0)
        Mockito.`when`(employee1.isCeo()).thenReturn(true)
        Mockito.`when`(employee1.isValid()).thenReturn(true)

        val employee2 = mock(Employee::class.java)
        Mockito.`when`(employee2.name).thenReturn("Johnny")
        Mockito.`when`(employee2.employeeId).thenReturn(0)
        Mockito.`when`(employee2.isCeo()).thenReturn(true)
        Mockito.`when`(employee2.isValid()).thenReturn(true)

        Mockito.`when`(organization.employeeExists("John")).thenReturn(true)
        Mockito.`when`(organization.employeeExists(0)).thenReturn(true)
        Mockito.`when`(organization.getById(0)).thenReturn(employee2)

        assertEquals(Error.EMPLOYEE_WITH_NAME_ALREADY_EXISTS, organization.update(employee1))
    }

    @Test
    fun update_EmployeeWithSameNameUpdated_ReturnOK() {
        val employee1 = mock(Employee::class.java)
        Mockito.`when`(employee1.name).thenReturn("John")
        Mockito.`when`(employee1.employeeId).thenReturn(0)
        Mockito.`when`(employee1.address).thenReturn("abc")
        Mockito.`when`(employee1.currentReports).thenReturn("rep1")
        Mockito.`when`(employee1.isCeo()).thenReturn(true)
        Mockito.`when`(employee1.isValid()).thenReturn(true)

        val employee2 = mock(Employee::class.java)
        Mockito.`when`(employee2.name).thenReturn("John")
        Mockito.`when`(employee2.employeeId).thenReturn(0)
        Mockito.`when`(employee2.address).thenReturn("def")
        Mockito.`when`(employee2.currentReports).thenReturn("rep2")
        Mockito.`when`(employee2.isCeo()).thenReturn(true)
        Mockito.`when`(employee2.isValid()).thenReturn(true)

        Mockito.`when`(organization.employeeExists("John")).thenReturn(true)
        Mockito.`when`(organization.employeeExists(0)).thenReturn(true)
        Mockito.`when`(organization.getById(0)).thenReturn(employee2)

        val e3 = organization.getById(0)
        assertEquals(0, organization.update(employee1))
        assertEquals("def", e3!!.address)
    }

    @Test
    fun find_employeeNotExists_ReturnNull() {
        val employee = organization.find("John")
        assertNull(employee)
    }

    @Test
    fun find_employeeExists_ReturnObject() {
        val ceo = Employee("John", 40, "address", "currentReports")
        Mockito.`when`(organization.getIdByName("John")).thenReturn(0)
        Mockito.`when`(organization.getById(0)).thenReturn(ceo)
        val employee = organization.find("John")
        assertNotNull(employee)
    }
}