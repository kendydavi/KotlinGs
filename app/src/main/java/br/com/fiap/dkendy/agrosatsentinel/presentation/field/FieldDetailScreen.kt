package br.com.fiap.dkendy.agrosatsentinel.presentation.field

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.dkendy.agrosatsentinel.domain.model.NdviRecord
import br.com.fiap.dkendy.agrosatsentinel.domain.model.WeatherInfo
import br.com.fiap.dkendy.agrosatsentinel.presentation.common.UiState
import br.com.fiap.dkendy.agrosatsentinel.presentation.components.NdviBadge
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetailScreen(
    fieldId: Int,
    onBackClick: () -> Unit,
    viewModel: FieldDetailViewModel = koinViewModel(parameters = { parametersOf(fieldId) })
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val ndviState by viewModel.ndviState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.field.name) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = viewModel::loadData) {
                        Icon(Icons.Default.Refresh, contentDescription = "Atualizar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            // Informações do talhão
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Informações do Talhão", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow("Cultura", viewModel.field.crop)
                        InfoRow("Área", "${viewModel.field.hectares} ha")
                        InfoRow("Latitude", "${viewModel.field.latitude}")
                        InfoRow("Longitude", "${viewModel.field.longitude}")
                    }
                }
            }

            // NDVI
            item {
                Text("Histórico NDVI", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            when (val state = ndviState) {
                is UiState.Idle, is UiState.Loading -> {
                    item {
                        Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is UiState.Error -> {
                    item {
                        Text(state.message, color = MaterialTheme.colorScheme.error)
                    }
                }
                is UiState.Success -> {
                    items(state.data) { record -> NdviRow(record) }
                }
            }

            // Clima
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text("Previsão do Tempo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            when (val state = weatherState) {
                is UiState.Idle, is UiState.Loading -> {
                    item {
                        Box(Modifier.fillMaxWidth().height(80.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is UiState.Error -> {
                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(state.message, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = viewModel::loadData) { Text("Tentar novamente") }
                        }
                    }
                }
                is UiState.Success -> {
                    items(state.data) { weather -> WeatherDetailRow(weather) }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f), fontSize = 14.sp)
        Text(text = value, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

@Composable
private fun NdviRow(record: NdviRecord) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = record.date, fontSize = 14.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = String.format("%.2f", record.value), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                NdviBadge(status = record.status)
            }
        }
    }
}

@Composable
private fun WeatherDetailRow(weather: WeatherInfo) {
    val hour = weather.time.substringAfterLast("T").take(5)
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = hour, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("${weather.temperature}°C", fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.WaterDrop, null, modifier = Modifier.size(14.dp))
                    Text("${weather.precipitationProbability}%", fontSize = 14.sp)
                }
            }
        }
    }
}
