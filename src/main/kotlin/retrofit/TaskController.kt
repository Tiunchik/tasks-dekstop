package retrofit

import models.Task
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskController {

    @GET("/api/v1/tasks")
    fun getAllTasks(): Call<List<Task>>

    @GET("/api/v1/tasks/{id}")
    fun getOneTask(@Path("id") id: Long): Call<Task>

    @POST("/api/v1/tasks")
    fun createTask(@Body task: Task): Call<Task>

}