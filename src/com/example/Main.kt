package com.example

import com.example.problem1.Problem1
import com.example.problem2.Employee
import com.example.problem2.Organization
import java.util.*

fun main() {
    println("Problem 1:")
    val v = Vector<Int>(listOf(3, 1, 2, 5, 4, 2))
    println("\tInput: $v")
    println("\tResult: ${Problem1.rob(v)}")
    println()
    println("Problem 2:")
    val organization = Organization()
    val johnId = organization.add(Employee("John", 42, "John address"))

    println("\tAdding John: $johnId")
    val maryId = organization.add(Employee("Mary", 32, "Mary address", "", johnId))
    println("\tAdding Mary: $maryId")
    var result = organization.add(Employee("Peter", 33, "Peter address", "", johnId))
    println("\tAdding Peter: $result")
    result = organization.add(Employee("Michael", 34, "Michael address", "", johnId))
    println("\tAdding Michael: $result")
    result = organization.add(Employee("Sam", 35, "Sam address", "", maryId))
    println("\tAdding Sam: $result")

    val willId = organization.add(Employee("Will", 24, "Will address", "", maryId))
    println("\tAdding Will: $willId")
    result = organization.add(Employee("Jackie", 36, "Jackie address", "", willId))
    println("\tAdding Jackie: $result")
    result = organization.add(Employee("Frank", 37, "Frank address", "", willId))
    println("\tAdding Frank: $result")

    var employee = organization.find("John")
    println("\tSearching for John: $employee")
    employee = organization.find("Victor")
    println("\tSearching for Victor: $employee")
    employee = organization.find("Will")
    println("\tSearching for Will: $employee")
    employee?.address = "New Will Address"
    employee?.name = "William"
    println("\tNew Will data: $employee")
    result = organization.update(employee!!)
    println("\tUpdate Will: $result")
    println("\tSearching for Will: ${organization.find("Will")}")
    println("\tSearching for William: ${organization.find("William")}")
}