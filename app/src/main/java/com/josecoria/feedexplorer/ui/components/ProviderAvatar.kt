package com.josecoria.feedexplorer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josecoria.feedexplorer.domain.model.Provider
import kotlin.math.absoluteValue

private val avatarColors = listOf(
    Color(0xFF1E88E5),
    Color(0xFF43A047),
    Color(0xFFE53935),
    Color(0xFF8E24AA),
    Color(0xFFF4511E),
    Color(0xFF00897B),
    Color(0xFF3949AB),
    Color(0xFFC0CA33),
    Color(0xFF6D4C41),
    Color(0xFF00ACC1)
)

@Composable
fun ProviderAvatar(
    provider: Provider,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    val initials = remember(provider.id) {
        "${provider.firstName.first()}${provider.lastName.first()}".uppercase()
    }
    val backgroundColor = remember(provider.id) {
        avatarColors[(provider.firstName + provider.lastName).hashCode().absoluteValue % avatarColors.size]
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = Color.White,
            fontSize = (size.value * 0.38f).sp,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
