package br.com.fiap.dkendy.agrosatsentinel.presentation.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

data class OnboardingPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val pages = listOf(
    OnboardingPage(
        icon = Icons.Default.Satellite,
        title = "Monitoramento Satelital",
        description = "Acompanhe suas lavouras em tempo real com dados do satélite Sentinel-2 da ESA. Imagens atualizadas a cada 5 dias, sem custo de aquisição."
    ),
    OnboardingPage(
        icon = Icons.Default.BarChart,
        title = "Índice NDVI",
        description = "Visualize a saúde da vegetação com o índice NDVI. Identifique áreas críticas e tome decisões antes que o problema se agrave."
    ),
    OnboardingPage(
        icon = Icons.Default.NotificationsActive,
        title = "Alertas Climáticos",
        description = "Receba alertas automáticos de chuva, seca, geada e vento forte para cada talhão cadastrado. Proteja sua produção com antecedência."
    )
)

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val currentPage by viewModel.currentPage.collectAsState()
    val page = pages[currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        AnimatedContent(
            targetState = currentPage,
            label = "onboarding_page"
        ) { pageIndex ->
            val p = pages[pageIndex]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = p.icon,
                    contentDescription = p.title,
                    modifier = Modifier.size(120.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = p.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = p.description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Indicadores de página
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            repeat(viewModel.totalPages) { index ->
                Box(
                    modifier = Modifier
                        .size(if (index == currentPage) 12.dp else 8.dp)
                        .background(
                            color = if (index == currentPage)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
            }
        }

        // Botões de navegação
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (currentPage > 0) {
                OutlinedButton(
                    onClick = viewModel::onPreviousPage,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Voltar")
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Button(
                onClick = {
                    if (currentPage < viewModel.totalPages - 1) {
                        viewModel.onNextPage()
                    } else {
                        viewModel.finishOnboarding()
                        onFinish()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (currentPage < viewModel.totalPages - 1) "Próximo" else "Começar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
