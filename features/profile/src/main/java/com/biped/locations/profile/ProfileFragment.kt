package com.biped.locations.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.biped.locations.theme.AppTheme

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    ProfileUI(
                        uiModel = UserUiModel(
                            "Edgar",
                            "https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg"
                        )
                    )
                }
            }
        }
    }
}
