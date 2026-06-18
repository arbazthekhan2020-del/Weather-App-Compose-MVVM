package com.example.weatherappretrofit.uiScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappretrofit.R
import com.example.weatherappretrofit.model.Weather
import com.example.weatherappretrofit.viewmodel.WeatherViewModel
import com.example.weatherappretrofit.weatherIconsmapping.weatherIconsMap

@Composable
fun WeatherScreen(
    padding: PaddingValues,
    viewmodel: WeatherViewModel = viewModel(),
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
){

    var location by remember { mutableStateOf("")}
    val state by viewmodel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Weather App",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.size(50.dp))

        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Enter location", fontSize = 16.sp) },
                modifier = Modifier.weight(3f),
                singleLine = true,
                maxLines = 1
            )

            Button(
                onClick = { viewmodel.getWeather(location) ; location = ""},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                ) {
                Text("Search", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(modifier = Modifier.fillMaxSize()){
            Card(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    }

                    state.weather?.let { weather ->
                        WeatherInfo(weather)
                    }

                    state.error?.let {
                        Text(it)
                    }
                }
            }

            IconButton(
                onClick = {onThemeToggle()},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isDarkTheme) R.drawable.lightmode else R.drawable.darkmode
                    ),
                    contentDescription = "Toggle theme"
                )
            }
        }
    }
}

@Composable
fun WeatherInfo(weather: Weather){
    Text(
        text = "${weather.temperature}°C",
        fontSize = 50.sp
    )

    Row {
        Text(
            text = weather.weather,
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )

        val icons = weatherIconsMap(weather.weather)
        Image(
            painter = painterResource(icons),
            contentDescription = weather.weather,
            modifier = Modifier.size(64.dp)
        )
    }

    Text(
        text = "Wind Speed: ${weather.windSpeed}km/h",
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
    Text(
        text = weather.location,
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
}