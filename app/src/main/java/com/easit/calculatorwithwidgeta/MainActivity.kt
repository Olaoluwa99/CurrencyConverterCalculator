package com.easit.calculatorwithwidgeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easit.calculatorwithwidgeta.model.CurrencyRepositoryImpl
import com.easit.calculatorwithwidgeta.retrofit.RetrofitInstance
import com.easit.calculatorwithwidgeta.ui.currency.CurrencyScreen
import com.easit.calculatorwithwidgeta.ui.currency.CurrencyScreenViewModel
import com.easit.calculatorwithwidgeta.ui.theme.CalculatorWithWidgetATheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CurrencyScreenViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CurrencyScreenViewModel(CurrencyRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorWithWidgetATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val test = viewModel.retrieveState.collectAsState(0).value
                    //Text(text = "Status : $test")
                    //CurrencyScreen(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorWithWidgetATheme {
        //Greeting("Android")
    }
}