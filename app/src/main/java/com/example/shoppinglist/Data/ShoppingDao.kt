package com.example.shoppinglist.Data

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
    suspend fun getShoppingList(): LiveData<List<ShoppingItem>>
}