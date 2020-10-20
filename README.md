Coding Interview Problems
======
## Requirements
`kotlin:1.3+`
`junit:4.12`
`mockito-all:1.10.19`

## How to start a project

1. Clone repository using `git clone https://github.com/brylcio16/CodingInterviewProblems.git`
2. Open IntelliJ IDEA (Community version will be ok) 2018.3+
3. File > Open... and navigate to cloned folder
4. Kotlin could be not configured for that project, so open file `Main.kt`, click `Configure...` at right top corner of this file and choose `Java`.
5. After few seconds project should be configured - click green arrow that's on the right of `fun main()`
6. You can start tests in the same way

## Project structure
 - `Main.kt` - main file that demonstrates the functionality of the problems
 ### Problem 1
 - `Problem1.kt` - contains solution for Problem 1
 - `Problem1Tests.kt` - tests for rob function

### Problem 2
- `tests` - folder with tests for Employee and Organization classes
- `Employee` - class that represents employee; used by user to create new employees
- `Organization` - class that hold whole organization using HashMap. It also has methods to add or update an employee
- `Error` - object that holds errors returned by `add` and `update` methods in `Organization` class

## Assumptions

I made some assumptions in `Problem 2`:
1. There was no mention of removing employees, so `delete` method is not implemented
2. There can't be two (or more) employees with the same name

## `Main.kt` execution log
```
Problem 1:
	Input: [1, 2, 3, 1]
	Result: 4

Problem 2:
	Adding John: 0
	Adding Mary: 1
	Adding Peter: 2
	Adding Michael: 3
	Adding Sam: 4
	Adding Will: 5
	Adding Jackie: 6
	Adding Frank: 7
	Searching for John: Employee(name='John', age=42, address='John address', currentReports='', currentBoss=-1, employeeId=0)
	Searching for Victor: null
	Searching for Will: Employee(name='Will', age=24, address='Will address', currentReports='', currentBoss=1, employeeId=5)
	New Will data: Employee(name='William', age=24, address='New Will Address', currentReports='', currentBoss=1, employeeId=5)
	Update Will: 5
	Searching for Will: null
	Searching for William: Employee(name='William', age=24, address='New Will Address', currentReports='', currentBoss=1, employeeId=5)
```