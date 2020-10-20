package com.example.problem2


open class Organization {
    private var idCount = 0
    private var hasCeo = false
    private val employees: HashMap<Int, Employee> = HashMap()
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
        return employees[id]?.let { Employee(it) }
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

    /**
     * Return: id of added employee (result >= 0), or error (result < 0)
     */
    fun add(employee: Employee): Int {
        if(hasCeo() && employee.isCeo())
            return Error.CEO_ALREADY_EXISTS
        if(!hasCeo() && !employee.isCeo())
            return Error.CEO_NOT_EXISTS
        if(!employee.isValid())
            return Error.EMPLOYEE_IS_INVALID
        if(employeeExists(employee.name))
            return Error.EMPLOYEE_ALREADY_EXISTS

        employee.employeeId = getNextId()
        val entity = Employee(employee)
        employees[entity.employeeId] = entity
        namesIndex[employee.name] = entity.employeeId
        if(employee.isCeo())
            hasCeo = true
        return entity.employeeId
    }

    /**
     * Return: id of added employee (result >= 0), or error (result < 0)
     */
    fun update(employee: Employee): Int {
        if(hasCeo() && employee.isCeo())
            return Error.CEO_ALREADY_EXISTS
        if(!hasCeo() && !employee.isCeo())
            return Error.CEO_NOT_EXISTS
        if(!employeeExists(employee.employeeId))
            return Error.EMPLOYEE_NOT_EXISTS
        if(!employee.isValid())
            return Error.EMPLOYEE_IS_INVALID

        val employeeEntity = getById(employee.employeeId)!!

        // name of an employee has been changed, so  we have to change also name index to new one
        if(employeeEntity.name != employee.name) {
            if(employeeExists(employee.name))
                return Error.EMPLOYEE_WITH_NAME_ALREADY_EXISTS
            namesIndex.remove(employeeEntity.name)
            namesIndex[employee.name] = employee.employeeId
        }

        employees[employee.employeeId] = Employee(employee)
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