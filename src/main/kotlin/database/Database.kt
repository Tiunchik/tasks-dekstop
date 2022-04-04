package database

import models.Item

interface Database {
    fun getAllTasks(): List<Item>
    fun getOneTask(id: Long): Item
}