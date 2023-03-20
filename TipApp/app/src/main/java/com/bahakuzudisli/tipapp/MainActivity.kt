package com.bahakuzudisli.tipapp

import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bahakuzudisli.tipapp.ui.theme.TipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipTimeScreen()

                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var number by remember { mutableStateOf("") }
    val amount = number.toDoubleOrNull() ?: 0.0
    var tip = calculateTip(amount = amount)

    Column(
        modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = stringResource(R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))

        EditNumberField(number = number, onValueChanged = { number = it })
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { number = "1000" },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Count1000")
        }


    }

}

@Composable
fun EditNumberField(number: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = number,
        onValueChange = onValueChanged,
        label = { Text(text = "tip amount") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipAppTheme {
        TipTimeScreen()
    }
}