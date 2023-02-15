package com.example.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.data.ShoppingItem

class ShoppingAddDialog(context: Context, var addDialogInterface: AddDialogInterface): AppCompatDialog(context) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_item_dialog)

        val etName = findViewById<EditText>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)

        val name = etName?.text.toString()
        val amount = etAmount?.text.toString()

        findViewById<Button>(R.id.btnAdd)?.setOnClickListener {
            if (etName?.text.toString().isEmpty() || etAmount?.text.toString().isEmpty()) {
                Toast.makeText(context,"Name and amount must not be empty!", Toast.LENGTH_SHORT).show()
            } else {
                val shoppingItem = ShoppingItem(etName?.text.toString(), etAmount?.text.toString().toInt())
                addDialogInterface.onAddButtonClicked(shoppingItem)
                dismiss()
            }
        }

        findViewById<Button>(R.id.btnCancel)?.setOnClickListener{
            dismiss()
        }

    }
}