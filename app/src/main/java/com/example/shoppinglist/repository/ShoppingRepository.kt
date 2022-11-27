package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.ShoppingDao
import com.example.shoppinglist.data.ShoppingItem

class ShoppingRepository(
    private val dao: ShoppingDao
) {

//    val shoppingList: LiveData<List<ShoppingItem>> = dao.getA

    suspend fun upsert(shoppingItem: ShoppingItem) {
        dao.upsert(shoppingItem)
    }

    suspend fun delete(shoppingItem: ShoppingItem) {
        dao.delete(shoppingItem)
    }

    fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return dao.getShoppingList()
    }
}