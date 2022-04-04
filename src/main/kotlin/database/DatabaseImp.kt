package database

import models.Item

class DatabaseImp : Database {

    private val data = arrayListOf<Item>(
        Item(0, "Test Text for item 0"),
        Item(1, "Test Text for item 1"),
        Item(2, "Test Text for item 2"),
        Item(3, "Test Text for item 3"),
        Item(4, "Test Text for item 4"),
    )

    override fun getAllTasks(): List<Item> = data

    override fun getOneTask(id: Long): Item = data.first { it.id == id }
}