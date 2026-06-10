package br.com.fiap.dkendy.agrosatsentinel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.dkendy.agrosatsentinel.presentation.navigation.AppNavigation
import br.com.fiap.dkendy.agrosatsentinel.ui.theme.AgroSatSentinelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgroSatSentinelTheme {
                AppNavigation()
            }
        }
    }
}
