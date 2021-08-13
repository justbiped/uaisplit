package com.favoriteplaces.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.favoriteplaces.R
import com.favoriteplaces.databinding.HomeFragmentBinding
import android.widget.LinearLayout


class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = HomeFragmentBinding.bind(view)

        setupSessions()
    }

    private fun setupSessions() {

        val list = getSessionList()

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        list.forEach {
            val sessionView = HomeSessionView(requireContext())
            sessionView.bind(it)

            binding.sessionLayoutContainer.addView(sessionView)
        }

    }

    private fun getSessionList(): List<Session> {
        return listOf(
            Session(
                "Um titulo",
                getListItems()
            ),
            Session(
                "Outro titulo",
                getListItems()
            ),
            Session(
                "Mais um titulo",
                getListItems()
            ),
            Session(
                "Cansado de titulo",
                getListItems()
            )
        )
    }

    private fun getListItems(): List<SessionItem> {
        return listOf(
            SessionItem(
                "Primeiro",
                "https://cdn.pixabay.com/photo/2021/07/25/12/04/monkey-6491669_960_720.jpg",
                ""
            ), SessionItem(
                "Segundo",
                "https://cdn.pixabay.com/photo/2021/07/25/12/04/monkey-6491669_960_720.jpg",
                ""
            ), SessionItem(
                "Terceiro",
                "https://cdn.pixabay.com/photo/2021/07/25/12/04/monkey-6491669_960_720.jpg",
                ""
            ), SessionItem(
                "Quarto",
                "https://cdn.pixabay.com/photo/2021/07/25/12/04/monkey-6491669_960_720.jpg",
                ""
            ), SessionItem(
                "Quinto",
                "https://cdn.pixabay.com/photo/2021/07/25/12/04/monkey-6491669_960_720.jpg",
                ""
            )
        )
    }
}

