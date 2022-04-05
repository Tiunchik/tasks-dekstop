package service

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.SingleScheduler
import models.Task
import org.koin.java.KoinJavaComponent
import retrofit.TaskController
import java.util.concurrent.TimeUnit

class TaskService {

    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)

    val rxjava =
        Observable
            .interval(0, 5, TimeUnit.SECONDS)
            .map {
                controller.getAllTasks().execute().body() ?: listOf()
            }
            .doOnNext{
                    stateOfTasks.clear()
                    stateOfTasks.addAll(it)
            }
            .subscribe()

    val stateOfTasks: MutableList<Task> = mutableListOf();
}