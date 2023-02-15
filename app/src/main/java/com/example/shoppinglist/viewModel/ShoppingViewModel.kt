package com.example.shoppinglist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.ShoppingDatabase
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(application: Application): AndroidViewModel(application) {
    val repository: ShoppingRepository
    val shoppingList: LiveData<List<ShoppingItem>>

    init {
        val dao = ShoppingDatabase.getDatabase(application).getShoppingDao()
        repository = ShoppingRepository(dao)
        shoppingList = repository.getShoppingList()
    }

    fun upsert(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(shoppingItem)
    }

    fun delete(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(shoppingItem)
    }

}