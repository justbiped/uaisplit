package com.favoriteplaces.core.binding

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingFragment<T : ViewBinding>(@LayoutRes layout: Int) : Fragment(layout) {

    private var _binding: T? = null
    val binding: T get() = _binding!!

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = setupBinding(view)
    }

    abstract fun setupBinding(view: View): T

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}