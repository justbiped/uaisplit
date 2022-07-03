package com.biped.locations.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.profile.ui.theme.LocationsandroidTheme

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LocationsandroidTheme {
                    ProfileView()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            ProfileHeader()
        }
    }
}

@Composable
fun ProfileHeader() {
    Image(
        painter = rememberAsyncImagePainter(
            model = "https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg",
            placeholder = painterResource(id = R.drawable.ic_profile_on)
        ),
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}