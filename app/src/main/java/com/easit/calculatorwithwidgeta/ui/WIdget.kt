package com.easit.calculatorwithwidgeta.ui

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.GlanceId
import androidx.glance.action.clickable
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Box
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.layout.wrapContentWidth
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.easit.calculatorwithwidgeta.R
import com.easit.calculatorwithwidgeta.constants.Constants
import com.easit.calculatorwithwidgeta.ui.currency.ButtonItem

class Widget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetItem()
        }
    }

    class CurrencyWidgetReceiver: GlanceAppWidgetReceiver() {
        override val glanceAppWidget: GlanceAppWidget
            get() = Widget()
    }

    //
    @Composable
    fun WidgetItem() {
        //
        val smallTextSize = 12
        val designatedHeight = 60.dp
        val designatedWidth = 60.dp

        var allExchange = mutableListOf("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
            "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
        )
        var tempAllExchange = mutableListOf("AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF", "IDR",
            "ILS", "INR", "ISK", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "USD", "ZAR"
        )

        val pinnedExchange = listOf("EUR", "GBP", "AUD", "NZD")

        //var searchText by remember { mutableStateOf("") }
        var searchText = ""

        //var overflowVisible by remember { mutableStateOf(false) }
        var overflowVisible = false

        Column (
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Constants.darkGrey)
                .cornerRadius(16.dp)
        ){
            //
            Box(
                contentAlignment = Alignment.CenterEnd
            ){
                Row (
                    GlanceModifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ){
                    Box(
                        modifier = GlanceModifier
                            .background(Constants.clip)
                            .cornerRadius(16.dp)
                            .clickable { /*viewModel.defaultConverterClicked(pinnedExchange[0])*/ }
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    ){
                        GlanceText(text = "${pinnedExchange[3]}/USD",  size = smallTextSize)
                    }
                    Spacer(modifier = GlanceModifier.height(8.dp))

                    Box(
                        modifier = GlanceModifier
                            .background(Constants.clip)
                            .cornerRadius(16.dp)
                            .clickable { /*viewModel.defaultConverterClicked(pinnedExchange[1])*/ }
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    ){
                        GlanceText(text = "${pinnedExchange[3]}/USD",  size = smallTextSize)
                    }
                    Spacer(modifier = GlanceModifier.height(8.dp))

                    Box(
                        modifier = GlanceModifier
                            .background(Constants.clip)
                            .cornerRadius(16.dp)
                            .clickable { /*viewModel.defaultConverterClicked(pinnedExchange[2])*/ }
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    ){
                        GlanceText(text = "${pinnedExchange[3]}/USD",  size = smallTextSize)
                    }
                    Spacer(modifier = GlanceModifier.height(8.dp))

                    Box(
                        modifier = GlanceModifier
                            .background(Constants.clip)
                            .cornerRadius(16.dp)
                            .clickable { /*viewModel.defaultConverterClicked(pinnedExchange[3])*/ }
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    ){
                        GlanceText(text = "${pinnedExchange[3]}/USD",  size = smallTextSize)
                    }

                }

                Box(
                    modifier = GlanceModifier
                        .padding(8.dp)
                ){
                    Box(
                        modifier = GlanceModifier
                        .width(24.dp)
                        .height(24.dp)
                        .background(Color.LightGray)
                        .cornerRadius(24.dp)
                        //.clickable(/*overflowVisible = !overflowVisible*/)
                    ){}
                }
            }


            //
            Box(
                modifier = GlanceModifier
                    .wrapContentSize(),
                contentAlignment = Alignment.TopEnd
            ){
                Column (
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ){
                    GlanceTextBox(text = "0.00")
                    Spacer(modifier = GlanceModifier.height(24.dp))

                    //
                    Row (
                        modifier = GlanceModifier
                            .wrapContentWidth(),
                        //horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        //
                        Column{
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "7", onButtonClicked = {/*viewModel.onNumber7Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "4", onButtonClicked = {/*viewModel.onNumber4Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "1", onButtonClicked = {/*viewModel.onNumber1Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "0", onButtonClicked = {/*viewModel.onNumber0Click()*/})
                        }

                        //
                        Column{
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "8", onButtonClicked = {/*viewModel.onNumber8Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "5", onButtonClicked = {/*viewModel.onNumber5Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "2", onButtonClicked = {/*viewModel.onNumber2Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = ".", onButtonClicked = {/*viewModel.onSymbolPointClick()*/})
                        }

                        //
                        Column{
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "9", onButtonClicked = {/*viewModel.onNumber9Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "6", onButtonClicked = {/*viewModel.onNumber6Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "3", onButtonClicked = {/*viewModel.onNumber3Click()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "Ans", onButtonClicked = {/*viewModel.onSymbolAnsClick()*/})
                        }

                        //
                        Column{
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "÷", onButtonClicked = {/*viewModel.onSymbolDivideClick()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "×", onButtonClicked = {/*viewModel.onSymbolMultiplyClick()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "−", onButtonClicked = {/*viewModel.onSymbolSubtractClick()*/})
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "+", onButtonClicked = {/*viewModel.onSymbolAddClick()*/})
                        }

                        //
                        Column{
                            Box(modifier = GlanceModifier
                                .width(designatedWidth)
                                .height(designatedHeight)
                                .background(Constants.darkGrey)
                                .clickable { /*viewModel.onSymbolEraseClick()*/ },
                                contentAlignment = Alignment.Center
                            ){
//                                Icon(
//                                    imageVector = Icons.Filled.ArrowBack,
//                                    contentDescription = null,
//                                    tint = Color.Black
//                                )
                            }

                            //
                            GlanceButtonItem(width = designatedWidth, height = designatedHeight, value = "%", onButtonClicked = { /*viewModel.onSymbolPercentageClick()*/})
                            Box(modifier = GlanceModifier
                                .width((designatedWidth))
                                .height((designatedHeight * 2))
                                .background(Color.Gray)
                                .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Box(
                                    modifier = GlanceModifier
                                        .width(designatedWidth - 16.dp)
                                        .height((designatedHeight * 2) - 16.dp)
                                        .cornerRadius(16.dp)
                                        .background(Constants.darkGrey)
                                        .clickable { /*viewModel.equalsClicked()*/ }
                                        .padding(top = 24.dp),
                                    contentAlignment = Alignment.TopCenter
                                ){
                                    GlanceText(text = "=")
                                }

                            }
                        }

                    }
                }

                if (overflowVisible){
                    Box(modifier = GlanceModifier
                        .height(designatedHeight * 4)
                        .padding(end = 16.dp)){

                        Box(modifier = GlanceModifier
                            .cornerRadius(16.dp)
                            .background(Color.LightGray)
                            .padding(12.dp)
                        ){
                            Column {
                                GlanceTextBoxSmall(text = "Search")

                                Spacer(modifier = GlanceModifier.height(6.dp))

                                LazyColumn(
                                    modifier = GlanceModifier
                                ){
                                    items(count = allExchange.size){
                                        Row (
                                            modifier = GlanceModifier
                                                .clickable {
                                                    /*viewModel.defaultConverterClicked(
                                                        allExchange[it]
                                                    )*/
                                                }
                                                .padding(6.dp)
                                        ){
                                            if (pinnedExchange.contains(allExchange[it])){
                                                //IconImageProvider(Icon(R.drawable.ic_launcher_foreground))
                                                /*Icon(
                                                    imageVector = Icons.Filled.FavoriteBorder,
                                                    contentDescription = null,
                                                )*/
                                                Spacer(modifier = GlanceModifier.width(6.dp))
                                            }
                                            GlanceText(text = "${allExchange[it]}/USD")
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



}

@Composable
fun GlanceText(
    text: String,
    size: Int = 14,
    color: Color = Color.White
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = size.sp,
            fontWeight = FontWeight.Bold,
            color = GlanceTheme.colors.onSurface
        )
    )
}

@Composable
fun GlanceTextBox(
    text: String,
    size: Int = 30
) {
    Box (
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(Constants.lightGrey)
            .cornerRadius(16.dp)
            .padding(16.dp)
    ){
        Text(
            modifier = GlanceModifier.fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = size.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = GlanceTheme.colors.onSurface
            ),
            maxLines = 1
        )
    }
}

@Composable
fun GlanceTextBoxSmall(
    text: String,
    size: Int = 12
) {
    Box (
        modifier = GlanceModifier
            .wrapContentWidth()
            .background(Constants.lightGrey)
    ){
        Text(
            modifier = GlanceModifier.fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = size.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = GlanceTheme.colors.onSurface
            ), maxLines = 1
        )
    }
}

@Composable
fun GlanceButtonItem(
    width: Dp,
    height: Dp,
    value: String,
    //color: Color = GlanceTheme.colors.onPrimary.getColor(),
    onButtonClicked: () -> Unit = {},
) {
    //
    Box(modifier = GlanceModifier
        .width(width)
        .height(height)
        .background(Constants.darkGrey)
        .clickable { onButtonClicked() },
        contentAlignment = Alignment.Center
    ) {
        GlanceText(text = value,  size = 20)
    }
}


