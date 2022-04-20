package models;

data class Task (
    var id: Long = 0,
    var name: String = "Error",
    var description: String = "Error during request to server",
)
