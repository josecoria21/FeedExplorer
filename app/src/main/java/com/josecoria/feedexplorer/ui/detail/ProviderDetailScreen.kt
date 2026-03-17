package com.josecoria.feedexplorer.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.josecoria.feedexplorer.R
import com.josecoria.feedexplorer.domain.model.Provider
import com.josecoria.feedexplorer.ui.UiState
import com.josecoria.feedexplorer.ui.components.ErrorScreen
import com.josecoria.feedexplorer.ui.components.LoadingIndicator
import com.josecoria.feedexplorer.ui.components.ProviderAvatar
import com.josecoria.feedexplorer.ui.theme.Green
import com.josecoria.feedexplorer.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderDetailScreen(
    onBackClick: () -> Unit,
    viewModel: ProviderDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.screen_title_provider_detail)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.content_description_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is UiState.Loading -> {
                LoadingIndicator(modifier = Modifier.padding(innerPadding))
            }
            is UiState.Error -> {
                ErrorScreen(
                    message = state.message,
                    onRetry = {},
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is UiState.Success -> {
                ProviderDetailContent(
                    provider = state.data,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun ProviderDetailContent(
    provider: Provider,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProviderAvatar(
            provider = provider,
            size = 120.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(
                R.string.provider_full_name,
                provider.firstName,
                provider.lastName,
                provider.suffix
            ),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        DetailCard {
            DetailRow(
                label = stringResource(R.string.label_specialty),
                value = provider.specialty
            )
            DetailRow(
                label = stringResource(R.string.label_npi),
                value = provider.npi
            )
            DetailRow(
                label = stringResource(R.string.label_location),
                value = stringResource(
                    R.string.location_format,
                    provider.location.city,
                    provider.location.state
                )
            )
            DetailRow(
                label = stringResource(R.string.label_salary_range),
                value = provider.salaryRange
            )
            DetailRow(
                label = stringResource(R.string.label_accepting_patients),
                value = if (provider.acceptingNewPatients) {
                    stringResource(R.string.accepting_patients_yes)
                } else {
                    stringResource(R.string.accepting_patients_no)
                },
                valueColor = if (provider.acceptingNewPatients) Green else Red
            )
        }
    }
}

@Composable
private fun DetailCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = valueColor
        )
    }
}
