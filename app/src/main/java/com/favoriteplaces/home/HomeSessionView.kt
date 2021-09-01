package com.favoriteplaces.home

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleObserver
import com.favoriteplaces.R
import com.favoriteplaces.databinding.HomeSessionViewBinding

class HomeSessionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleObserver {

    private val binding: HomeSessionViewBinding


    init {
        inflate(context, R.layout.home_session_view, this)
        binding = HomeSessionViewBinding.bind(rootView)
        setupSessionItemList()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupListeners()
    }

    private fun setupSessionItemList() {
        binding.sessionItemList.adapter = SessionItemAdapter()
    }

    private fun setupListeners() {
        getSessionItemAdapter()?.onItemClick{

        }
    }

    fun bind(session: Session) {
        binding.titleText.text = session.title
        getSessionItemAdapter()?.run { submitList(session.items) }
    }

    private fun getSessionItemAdapter(): SessionItemAdapter? {
        return binding.sessionItemList.adapter as? SessionItemAdapter
    }
}


