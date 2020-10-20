package com.example.problem2.organization


open class Employee(
        open var name: String = "",
        open var age: Int = 0,
        open var address: String = "",
        open var currentReports: String = "",
        open var currentBoss: Int = -1) {
    open var employeeId: Int = 0

    constructor(employeeId: Int, name: String, age: Int, address: String, currentReports: String, currentBoss: Int) : this(name, age, address, currentReports, currentBoss) {
        this.employeeId = employeeId
    }

    open fun isCeo(): Boolean {
        return currentBoss == -1
    }

    open fun isValid(): Boolean {
        return name.isNotEmpty() && age > 0 && address.isNotEmpty()
    }

    override fun toString(): String {
        return "Employee(name='$name', age=$age, address='$address', currentReports='$currentReports', currentBoss=$currentBoss, employeeId=$employeeId)"
    }
}