package com.favoriteplaces.core.binding

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T : ViewBinding>(@LayoutRes layout: Int) : Fragment(layout) {

    private var _binding: T? = null
    val binding: T get() = _binding!!

    fun setViewBinding(binding: T) {
        _binding = binding
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}