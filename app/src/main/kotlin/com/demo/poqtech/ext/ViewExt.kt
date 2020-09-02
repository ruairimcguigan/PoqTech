package com.demo.poqtech.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

var View.visible: Boolean
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if(value) View.VISIBLE else View.INVISIBLE
  }

fun View.snack(
  message: String,
  length: Int = Snackbar.LENGTH_LONG) {
  val snack = Snackbar.make(this, message, length)
  snack.show()
}

fun Context.toast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}