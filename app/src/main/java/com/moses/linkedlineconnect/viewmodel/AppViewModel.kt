package com.moses.linkedlineconnect.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    private val _students = MutableStateFlow<List<String>>(emptyList())
    val students: StateFlow<List<String>> = _students

    private val _schools = MutableStateFlow<List<String>>(emptyList())
    val schools: StateFlow<List<String>> = _schools

    private val _routes = MutableStateFlow<List<String>>(emptyList())
    val routes: StateFlow<List<String>> = _routes

    init {
        fetchStudents()
        fetchSchools()
        fetchRoutes()
    }

    private fun fetchStudents() {
        viewModelScope.launch {
            // Simulate fetching from a database
            _students.value = listOf("Student A", "Student B", "Student C")
        }
    }

    private fun fetchSchools() {
        viewModelScope.launch {
            // Simulate fetching from a database
            _schools.value = listOf("School A", "School B", "School C")
        }
    }

    private fun fetchRoutes() {
        viewModelScope.launch {
            // Simulate fetching from a database
            _routes.value = listOf("Route 1", "Route 2", "Route 3")
        }
    }
}