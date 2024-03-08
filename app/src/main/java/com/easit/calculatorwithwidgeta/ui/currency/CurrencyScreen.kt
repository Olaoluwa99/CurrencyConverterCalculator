package com.easit.calculatorwithwidgeta.ui.currency

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyScreen(viewModel: CurrencyScreenViewModel) {
    val screenWidth2 = LocalContext.current.resources.displayMetrics.xdpi.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthDivision = 5
    val screenWidthRatio = 1.25

    val designatedWidth = screenWidth / screenWidthDivision.toFloat()
    val designatedHeight = ((screenWidth / screenWidthDivision.toFloat()) * screenWidthRatio.toFloat())

    val smallTextSize = 12

    var allExchange by remember { mutableStateOf(mutableListOf(
        "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
        "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
    )) }

    var tempAllExchange by remember { mutableStateOf(mutableListOf(
        "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
        "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
    )) }
    var allExchangeYY = mutableListOf("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
        "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
    )
    val tempAllExchangeYY = mutableListOf("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
        "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
    )


    val pinnedExchange = listOf("EUR", "GBP", "AUD", "NZD")
    var searchText by remember { mutableStateOf("") }
    var overflowVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ){
            TextField(
                value = viewModel.resultText.collectAsState().value,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(
                    //textDirection = Text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                readOnly = true
            )

            TextField(
                value = viewModel.calculationText.collectAsState().value,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "0",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    //textDirection = Text,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                readOnly = true
            )

            /*
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.resultText.collectAsState().value,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.calculationText.collectAsState().value,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.End
            )*/
            Spacer(modifier = Modifier.height(24.dp))
        }

        //
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable { viewModel.defaultConverterClicked(pinnedExchange[0]) }
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ){
                    Text(text = "${pinnedExchange[0]}/USD", fontWeight = FontWeight.Bold, fontSize = smallTextSize.sp)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable { viewModel.defaultConverterClicked(pinnedExchange[1]) }
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ){
                    Text(text = "${pinnedExchange[1]}/USD", fontWeight = FontWeight.Bold, fontSize = smallTextSize.sp)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable { viewModel.defaultConverterClicked(pinnedExchange[2]) }
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ){
                    Text(text = "${pinnedExchange[2]}/USD", fontWeight = FontWeight.Bold, fontSize = smallTextSize.sp)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .clickable { viewModel.defaultConverterClicked(pinnedExchange[3]) }
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                ){
                    Text(text = "${pinnedExchange[3]}/USD", fontWeight = FontWeight.Bold, fontSize = smallTextSize.sp)
                }

            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { overflowVisible = !overflowVisible }
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.Black)
                        .padding(4.dp)
                )
            }
        }

        //
        Box(
            modifier = Modifier
                .wrapContentSize(),
            contentAlignment = Alignment.TopEnd
        ){
            Row (
                modifier = Modifier
                    .width(screenWidth),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                //
                Column{
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "7", onButtonClicked = {viewModel.onNumber7Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "4", onButtonClicked = {viewModel.onNumber4Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "1", onButtonClicked = {viewModel.onNumber1Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "0", onButtonClicked = {viewModel.onNumber0Click()})
                }

                //
                Column{
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "8", onButtonClicked = {viewModel.onNumber8Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "5", onButtonClicked = {viewModel.onNumber5Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "2", onButtonClicked = {viewModel.onNumber2Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = ".", onButtonClicked = {viewModel.onSymbolPointClick()})
                }

                //
                Column{
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "9", onButtonClicked = {viewModel.onNumber9Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "6", onButtonClicked = {viewModel.onNumber6Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "3", onButtonClicked = {viewModel.onNumber3Click()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "Ans", onButtonClicked = {viewModel.onSymbolAnsClick()})
                }

                //
                Column{
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "÷", onButtonClicked = {viewModel.onSymbolDivideClick()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "×", onButtonClicked = {viewModel.onSymbolMultiplyClick()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "−", onButtonClicked = {viewModel.onSymbolSubtractClick()})
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "+", onButtonClicked = {viewModel.onSymbolAddClick()})
                }

                //
                Column{
                    Box(modifier = Modifier
                        .width(designatedWidth)
                        .height(designatedHeight)
                        //.background(Color.Gray)
                        .clickable { viewModel.onSymbolEraseClick() },
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }

                    //
                    ButtonItem(width = designatedWidth, height = designatedHeight, value = "%", onButtonClicked = { viewModel.onSymbolPercentageClick()})
                    Box(modifier = Modifier
                        .width((designatedWidth))
                        .height((designatedHeight * 2))
                        //.background(Color.Gray)
                        .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Box(
                            modifier = Modifier
                                .width(designatedWidth - 16.dp)
                                .height((designatedHeight * 2) - 16.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.LightGray)
                                .clickable { viewModel.equalsClicked() }
                                .padding(top = 24.dp),
                            contentAlignment = Alignment.TopCenter
                        ){
                            Text(text = "=", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }

                    }
                }

            }

            if (overflowVisible){
                Box(modifier = Modifier.height(designatedHeight * 4).padding(end = 16.dp)){
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .padding(12.dp)
                    ){
                        Column {
                            TextField(
                                value = searchText,
                                onValueChange = { it ->
                                    searchText = it
                                    allExchange = if (it == ""){
                                        tempAllExchange
                                    }else{
                                        allExchange.filter {
                                            it.contains(searchText.uppercase())
                                        }.toMutableList()
                                    }
                                },
                                modifier = Modifier
                                    .width(designatedWidth * 2)
                                    .border(
                                        width = 1.dp,
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                Color.Black,
                                                Color.Black
                                            )
                                        ),
                                        shape = RoundedCornerShape(36.dp)
                                    ),
                                placeholder = {
                                    Text(
                                        text = "Search"
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    containerColor = Color.Transparent
                                ),
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Search,
                                        "Search"
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            LazyColumn(
                                modifier = Modifier
                            ){
                                items(count = allExchange.size){
                                    Row (
                                        modifier = Modifier
                                            .width(designatedWidth * 2)
                                            .clickable { viewModel.defaultConverterClicked(allExchange[it]) }
                                            .padding(6.dp)
                                    ){
                                        if (pinnedExchange.contains(allExchange[it])){
                                            Icon(
                                                imageVector = Icons.Outlined.LocationOn,
                                                contentDescription = null,
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                        }

                                        Text(text = "${allExchange[it]}/USD", fontWeight = FontWeight.Bold)
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ButtonItem(
    width: Dp,
    height: Dp,
    value: String,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    onButtonClicked: () -> Unit = {},
) {
    //
    Box(modifier = Modifier
        .width(width)
        .height(height)
        .clickable { onButtonClicked() }
        .background(color),
        contentAlignment = Alignment.Center
    ){
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

