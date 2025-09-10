package com.irv205.copilotcodingchallenge.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.irv205.copilotcodingchallenge.domain.model.Article
import com.irv205.copilotcodingchallenge.presentation.NewsUiState
import com.irv205.copilotcodingchallenge.presentation.NewsViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    onOpenArticle: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.load() }

    when (val s = state) {
        is NewsUiState.Loading -> LoadingView()
        is NewsUiState.Error -> ErrorView(message = s.message, onRetry = viewModel::retry)
        is NewsUiState.Success -> ArticleList(articles = s.articles, onOpenArticle = onOpenArticle)
    }
}

@Composable
fun LoadingView() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Oops: $message")
            Spacer(Modifier.height(12.dp))
            Button(onClick = onRetry) { Text("Retry") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleList(articles: List<Article>, onOpenArticle: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tech Headlines") })
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles) { article ->
                ArticleRowView(article = article, onOpenArticle = onOpenArticle)
                Divider()
            }
        }
    }
}

@Composable
fun ArticleRowView(article: Article, onOpenArticle: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl.ifBlank { null },
            contentDescription = article.title,
            modifier = Modifier
                .size(80.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(text = article.title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                text = article.sourceName,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { onOpenArticle(article.url) }) {
                Text("Open")
            }
        }
    }
}