package com.easit.calculatorwithwidgeta.ui.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easit.calculatorwithwidgeta.model.Currency
import com.easit.calculatorwithwidgeta.model.Result
import com.easit.calculatorwithwidgeta.model.CurrencyRepository
import com.easit.calculatorwithwidgeta.model.Data
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.round

enum class TYPE { NONE, MULTIPLICATION, ADDITION, SUBTRACTION, DIVISION, RESULT }

class CurrencyScreenViewModel(
    private val currencyRepository: CurrencyRepository
): ViewModel() {
    private val dataDefault = Data(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1,0.0)
    private val _currency = MutableStateFlow(dataDefault)
    val currency = _currency.asStateFlow()

    private val _retrieveState = MutableStateFlow(0)
    val retrieveState = _retrieveState.asStateFlow()

    private val _tempCalculationText = MutableStateFlow("")
    val tempCalculationText = _tempCalculationText.asStateFlow()

    private val _calculationText = MutableStateFlow("")
    val calculationText = _calculationText.asStateFlow()

    private val _resultText = MutableStateFlow("")
    val resultText = _resultText.asStateFlow()

    private val _selectedRate = MutableStateFlow(0.0)
    val selectedRate = _selectedRate.asStateFlow()

    private val _selectedCurrencyName = MutableStateFlow("")
    val selectedCurrencyName = _selectedCurrencyName.asStateFlow()

    private val _calculationType = MutableStateFlow(TYPE.NONE)
    val calculationType = _calculationType.asStateFlow()

    init {
        viewModelScope.launch {
            _retrieveState.value = 1
            currencyRepository.getCurrencyData().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _retrieveState.value = 3
                    }
                    is Result.Success -> {
                        _retrieveState.value = 2
                        result.data?.let { currencies ->
                            _currency.update { currencies }
                        }
                    }
                }
            }
        }
    }

    private fun calculateRate (rate: Double){
        _selectedRate.value = rate
        _tempCalculationText.value = calculationText.value
        if (calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            _calculationText.value = (((calculationText.value.toDouble() * rate) * 1000)/1000).toString()
            _resultText.value = "${tempCalculationText.value} ${selectedCurrencyName.value}/USD ($rate)"
        }
        _calculationType.value = TYPE.RESULT
    }

    fun defaultConverterClicked(currencyName: String){
        _selectedCurrencyName.value = currencyName
        when (currencyName){
            "AUD" -> calculateRate(currency.value.AUD)
            "BGN" -> calculateRate(currency.value.BGN)
            "BRL" -> calculateRate(currency.value.BRL)
            "CAD" -> calculateRate(currency.value.CAD)
            "CHF" -> calculateRate(currency.value.CHF)
            "CNY" -> calculateRate(currency.value.CNY)
            "CZK" -> calculateRate(currency.value.CZK)
            "DKK" -> calculateRate(currency.value.DKK)
            "EUR" -> calculateRate(currency.value.EUR)
            "GBP" -> calculateRate(currency.value.GBP)
            "HKD" -> calculateRate(currency.value.HKD)
            "HRK" -> calculateRate(currency.value.HRK)
            "HUF" -> calculateRate(currency.value.HUF)
            "IDR" -> calculateRate(currency.value.IDR)
            "ILS" -> calculateRate(currency.value.ILS)
            "INR" -> calculateRate(currency.value.INR)
            "ISK" -> calculateRate(currency.value.ISK)
            "JPY" -> calculateRate(currency.value.JPY)
            "KRW" -> calculateRate(currency.value.KRW)
            "MXN" -> calculateRate(currency.value.MXN)
            "MYR" -> calculateRate(currency.value.MYR)
            "NOK" -> calculateRate(currency.value.NOK)
            "NZD" -> calculateRate(currency.value.NZD)
            "PHP" -> calculateRate(currency.value.PHP)
            "PLN" -> calculateRate(currency.value.PLN)
            "RON" -> calculateRate(currency.value.RON)
            "RUB" -> calculateRate(currency.value.RUB)
            "SEK" -> calculateRate(currency.value.SEK)
            "SGD" -> calculateRate(currency.value.SGD)
            "THB" -> calculateRate(currency.value.THB)
            "TRY" -> calculateRate(currency.value.TRY)
            "USD" -> calculateRate(currency.value.USD.toDouble())
            "ZAR" -> calculateRate(currency.value.ZAR)
        }
    }

    fun onConverter1Clicked() {
        if (calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            _resultText.value =
                (((calculationText.value.toDouble() * currency.value.CNY) * 1000)/1000).toString()
            _calculationText.value = ""
        }
    }

    fun onConverter2Clicked() {
        if (calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            _resultText.value =
                (((calculationText.value.toDouble() * currency.value.JPY) * 1000)/1000).toString()
            _calculationText.value = ""
        }
    }

    fun onConverter3Clicked() {
        if (calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            _resultText.value =
                (((calculationText.value.toDouble() * currency.value.GBP) * 1000)/1000).toString()
            _calculationText.value = ""
        }
    }//id: Int

    fun onConverter4Clicked() {
        if (calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            _resultText.value =
                (((calculationText.value.toDouble() * currency.value.EUR) * 1000)/1000).toString()
            _calculationText.value = ""
        }
    }

    fun equalsClicked(){
        if (resultText.value != "" && calculationText.value != ""){
            val other = calculationText.value.substring(2).toDouble()
            when (_calculationType.value){
                TYPE.ADDITION -> {
                    val temp = "${resultText.value} ${calculationText.value}"
                    _calculationText.value = (((_resultText.value.toDouble() + other) * 100)/100).toString()
                    _resultText.value = temp
                    _calculationType.value = TYPE.RESULT
                }
                TYPE.SUBTRACTION -> {
                    val temp = "${resultText.value} ${calculationText.value}"
                    _calculationText.value = (((_resultText.value.toDouble() - other) * 100)/100).toString()
                    _resultText.value = temp
                    _calculationType.value = TYPE.RESULT
                }
                TYPE.MULTIPLICATION -> {
                    val temp = "${resultText.value} ${calculationText.value}"
                    _calculationText.value = (((_resultText.value.toDouble() * other) * 100)/100).toString()
                    _resultText.value = temp
                    _calculationType.value = TYPE.RESULT
                }
                TYPE.DIVISION -> {
                    val temp = "${resultText.value} ${calculationText.value}"
                    _calculationText.value = (((_resultText.value.toDouble() / other) * 100)/100).toString()
                    _resultText.value = temp
                    _calculationType.value = TYPE.RESULT
                }
                else -> {}
            }
        }
    }

    fun swapCalculationAndResult(symbol: String){
        _resultText.value = _calculationText.value
        _calculationText.value = "$symbol "
    }

    fun onNumber1Click() {
        _calculationText.value = "${calculationText.value}1"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber2Click() {
        _calculationText.value = "${calculationText.value}2"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber3Click() {
        _calculationText.value = "${calculationText.value}3"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber4Click() {
        _calculationText.value = "${calculationText.value}4"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber5Click() {
        _calculationText.value = "${calculationText.value}5"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber6Click() {
        _calculationText.value = "${calculationText.value}6"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber7Click() {
        _calculationText.value = "${calculationText.value}7"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber8Click() {
        _calculationText.value = "${calculationText.value}8"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber9Click() {
        _calculationText.value = "${calculationText.value}9"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }
    fun onNumber0Click() {
        _calculationText.value = "${calculationText.value}0"
        if (calculationType.value  == TYPE.RESULT){
            _calculationType.value = TYPE.NONE
        }
    }

    //
    fun onSymbolMultiplyClick() {
        if (calculationText.value != ""){
            _calculationType.value = TYPE.MULTIPLICATION
            swapCalculationAndResult("×")
        }
    }
    fun onSymbolAddClick() {
        if (calculationText.value != ""){
            _calculationType.value = TYPE.ADDITION
            swapCalculationAndResult("+")
        }
    }
    fun onSymbolSubtractClick() {
        if (calculationText.value != ""){
            _calculationType.value = TYPE.SUBTRACTION
            swapCalculationAndResult("−")
        }
    }
    fun onSymbolDivideClick() {
        if (calculationText.value != ""){
            _calculationType.value = TYPE.DIVISION
            swapCalculationAndResult("÷")
        }

    }
    fun onSymbolPointClick() {
        if (calculationText.value != "") _calculationText.value = "${calculationText.value}."
    }
    fun onSymbolAnsClick() {
        equalsClicked()
    }
    fun onSymbolEraseClick() {
        if (_calculationType.value == TYPE.RESULT){
            _calculationText.value = ""
            _resultText.value = ""
        } else {
            if (calculationText.value.isNotEmpty()){
                _calculationText.value = _calculationText.value.dropLast(1)
            }
        }
    }
    fun onSymbolPercentageClick() {
        if (_calculationText.value.isNotEmpty() && calculationText.value.toDoubleOrNull() != null){
            val x = calculationText.value.toDouble()
            _resultText.value = "${calculationText.value}%"
            _calculationText.value = (x/100).toString()
        }
    }




    //
    /*fun onSymbolSubtractClick() {
        _calculationText.value = "${calculationText.value}6"
    }
    fun onSymbolDivideClick() {
        _calculationText.value = "${calculationText.value}7"
    }
    fun onSymbolPointClick() {
        _calculationText.value = "${calculationText.value}8"
    }*/
}
