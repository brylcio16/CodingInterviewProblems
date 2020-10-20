package com.example

import com.example.problem1.Problem1
import com.example.problem2.organization.Employee
import com.example.problem2.organization.Organization
import java.util.*

fun main() {
    println("Problem 1:")
    val v = Vector<Int>(listOf(1, 2, 3, 1))
    println("Result: ${Problem1.rob(v)}")
    println()
    println("Problem 2:")
    val organization = Organization()
    val johnId = organization.add(Employee("John", 42, "John address"))

    println("Adding John: $johnId")
    val maryId = organization.add(Employee("Mary", 32, "Mary address", "", johnId))
    println("Adding Mary: $maryId")
    var result = organization.add(Employee("Peter", 33, "Peter address", "", johnId))
    println("Adding Peter: $result")
    result = organization.add(Employee("Michael", 34, "Michael address", "", johnId))
    println("Adding Michael: $result")
    result = organization.add(Employee("Sam", 35, "Sam address", "", maryId))
    println("Adding Sam: $result")

    val willId = organization.add(Employee("Will", 24, "Will address", "", maryId))
    println("Adding Will: $willId")
    result = organization.add(Employee("Jackie", 36, "Jackie address", "", willId))
    println("Adding Jackie: $result")
    result = organization.add(Employee("Frank", 37, "Frank address", "", willId))
    println("Adding Frank: $result")

    var employee = organization.find("John")
    println("Searching for John: $employee")
    employee = organization.find("Victor")
    println("Searching for Victor: $employee")
    employee = organization.find("Will")
    println("Searching for Will: $employee")
    employee?.address = "New Will Address"
    employee?.name = "William"
    println("New Will data: $employee")
    result = organization.update(employee!!)
    println("Update Will: $result")
    println("Searching for Will: ${organization.find("Will")}")
    println("Searching for William: ${organization.find("William")}")
}