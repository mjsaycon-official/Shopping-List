package com.example.shoppinglist

import com.example.shoppinglist.data.ShoppingItem

interface AddDialogInterface {
    fun onAddButtonClicked(item: ShoppingItem)
}