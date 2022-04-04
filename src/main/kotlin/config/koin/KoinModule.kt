package config.koin

import database.Database
import database.DatabaseImp
import org.koin.dsl.module
import retrofit.TaskController
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val builder = Retrofit.Builder()
    .baseUrl("http://localhost:8080")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val koinModule = module {

    single { builder.create(TaskController::class.java) }

}