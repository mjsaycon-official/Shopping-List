package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    //Insert item if not existed or replace the item if existed
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getShoppingList(): LiveData<List<ShoppingItem>>
}