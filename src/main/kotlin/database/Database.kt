package database

import models.Item

interface Database {
    fun getAll(): List<Item>
    fun getById(id: Long): Item
}