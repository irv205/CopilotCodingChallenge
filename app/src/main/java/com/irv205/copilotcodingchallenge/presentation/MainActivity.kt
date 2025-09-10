package com.irv205.copilotcodingchallenge.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.irv205.copilotcodingchallenge.presentation.ui.theme.CopilotCodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import com.irv205.copilotcodingchallenge.presentation.component.NewsScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                NewsScreen(
                    viewModel = vm,
                    onOpenArticle = { url ->
                        if (url.isNotBlank()) {
                            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
                        }
                    }
                )
            }
        }
    }
}