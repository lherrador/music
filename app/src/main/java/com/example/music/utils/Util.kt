package com.example.music.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

fun <T : View> FragmentActivity.bind(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun <T : View?> FragmentActivity.bindOptional(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun <T : View> View.bind(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun <T : View?> View.bindOptional(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
}

fun <T : View> Fragment.bind(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { view!!.findViewById<T>(res) }
}

fun <T : View?> Fragment.bindOptional(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { view!!.findViewById<T>(res) }
}

fun <T : View> RecyclerView.ViewHolder.bind(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<T>(res) }
}

fun <T : View?> RecyclerView.ViewHolder.bindOptional(@IdRes res: Int): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<T>(res) }
}
