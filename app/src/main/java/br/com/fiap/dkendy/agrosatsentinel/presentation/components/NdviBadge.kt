package br.com.fiap.dkendy.agrosatsentinel.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviStatus
import br.com.fiap.dkendy.agrosatsentinel.ui.theme.NdviGreen
import br.com.fiap.dkendy.agrosatsentinel.ui.theme.NdviRed
import br.com.fiap.dkendy.agrosatsentinel.ui.theme.NdviYellow

@Composable
fun NdviBadge(status: NdviStatus) {
    val (backgroundColor, label) = when (status) {
        NdviStatus.HEALTHY -> NdviGreen to status.label
        NdviStatus.ATTENTION -> NdviYellow to status.label
        NdviStatus.CRITICAL -> NdviRed to status.label
    }

    Text(
        text = label,
        color = Color.White,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}
