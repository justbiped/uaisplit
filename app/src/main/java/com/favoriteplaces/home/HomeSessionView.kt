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

    private val binder: HomeSessionViewBinding =
        HomeSessionViewBinding.bind(inflate(context, R.layout.home_session_view, this))

    init {
        setupSessionItemList()
    }

    private fun setupSessionItemList() {
        binder.sessionItemList.adapter = SessionItemAdapter()
    }

    fun bind(session: Session) {
        binder.titleText.text = session.title
        getSessionItemAdapter()?.run { submitList(session.items) }
    }

    private fun getSessionItemAdapter(): SessionItemAdapter? {
        return binder.sessionItemList.adapter as? SessionItemAdapter
    }
}


