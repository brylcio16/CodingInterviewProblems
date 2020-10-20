package com.example.problem2.organization

open class Organization {
    private var idCount = 0
    private var hasCeo = false
    private val employees: HashMap<Int, EmployeeEntity> = HashMap()
    // mapping employee names to employeeId, so finding employee by name is fast
    private val namesIndex: HashMap<String, Int> = HashMap()

    open fun getNextId(): Int {
        val result = idCount
        idCount++
        return result
    }

    open fun hasCeo(): Boolean {
        return hasCeo
    }

    open fun getById(id: Int): Employee? {
        return employees[id]?.toEmployee()
    }

    open fun getIdByName(name: String): Int? {
        return namesIndex[name]
    }

    open fun employeeExists(name: String): Boolean {
        return namesIndex.containsKey(name)
    }

    open fun employeeExists(id: Int): Boolean {
        return employees.containsKey(id)
    }

    fun add(employee: Employee): Int {
        if(hasCeo() && employee.isCeo())
            return Status.CEO_ALREADY_EXISTS
        if(!hasCeo() && !employee.isCeo())
            return Status.CEO_NOT_EXISTS
        if(!employee.isValid())
            return Status.EMPLOYEE_IS_INVALID
        if(employeeExists(employee.name))
            return Status.EMPLOYEE_ALREADY_EXISTS

        val entity = EmployeeEntity(getNextId(), employee)
        employees[entity.employeeId] = entity
        namesIndex[employee.name] = entity.employeeId
        if(employee.isCeo())
            hasCeo = true
        return entity.employeeId
    }

    fun update(employee: Employee): Int {
        if(hasCeo() && employee.isCeo())
            return Status.CEO_ALREADY_EXISTS
        if(!hasCeo() && !employee.isCeo())
            return Status.CEO_NOT_EXISTS
        if(!employeeExists(employee.employeeId))
            return Status.EMPLOYEE_NOT_EXISTS
        if(!employee.isValid())
            return Status.EMPLOYEE_IS_INVALID

        val employeeEntity = getById(employee.employeeId)!!

        // name of an employee has been changed, so  we have to change also name index to new one
        if(employeeEntity.name != employee.name) {
            if(employeeExists(employee.name))
                return Status.EMPLOYEE_WITH_NAME_ALREADY_EXISTS
            namesIndex.remove(employeeEntity.name)
            namesIndex[employee.name] = employee.employeeId
        }

        employees[employee.employeeId] = EmployeeEntity(employee.employeeId, employee)
        return employee.employeeId
    }

    fun find(name: String): Employee? {
        val index = getIdByName(name)
        index?.let {
            return getById(it)
        }
        return null
    }
}