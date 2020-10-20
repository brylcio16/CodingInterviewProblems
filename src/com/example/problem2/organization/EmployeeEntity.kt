package com.example.problem2.organization

open class EmployeeEntity(
        open val employeeId: Int = -1,
        open val name: String = "",
        val age: Int = 0,
        open val address: String = "",
        val currentReports: String = "",
        val currentBoss: Int = -1) {

    constructor(employeeId: Int, employee: Employee): this(employeeId, employee.name, employee.age, employee.address, employee.currentReports, employee.currentBoss)

    fun toEmployee(): Employee {
        return Employee(employeeId, name, age, address, currentReports, currentBoss)
    }

    override fun toString(): String {
        return "Employee(name='$name', age=$age, address='$address', currentReports='$currentReports', currentBoss=$currentBoss, employeeId=$employeeId)"
    }
}