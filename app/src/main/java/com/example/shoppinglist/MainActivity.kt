package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.adapter.ShoppingAdapter
import com.example.shoppinglist.data.ShoppingItem
import com.example.shoppinglist.viewModel.ShoppingViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shoppingRV = findViewById<RecyclerView>(R.id.rv)

        val viewModel = ViewModelProvider(this)[ShoppingViewModel::class.java]

        val shoppingAdapter = ShoppingAdapter(listOf(), viewModel)
        shoppingRV.layoutManager = LinearLayoutManager(this)
        shoppingRV.adapter = shoppingAdapter

        viewModel.shoppingList.observe(this) {
            if (it != null) {
                shoppingAdapter.items = it
                shoppingAdapter.notifyDataSetChanged()
            }
        }
        val floatingBtn = findViewById<FloatingActionButton>(R.id.floatingBtn)
        floatingBtn.setOnClickListener{
            ShoppingAddDialog(this, object: AddDialogInterface {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }


    }
}