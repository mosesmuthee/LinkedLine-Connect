package com.moses.linkedlineconnect.ui.theme.PaymentConfirmationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PayConfirmDialog(
    transactionId: String,
    onDismiss: () -> Unit, // Callback to dismiss the dialog
    onNavigateBack: () -> Unit // Callback to navigate back to the previous page
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss() // Dismiss the dialog
                    onNavigateBack() // Navigate back to the previous page
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("OK", color = Color.White)
            }
        },
        title = {
            Text(
                text = "Payment Successful",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your payment has been accepted.",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "The student has been successfully booked.",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Transaction ID:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(
                    text = transactionId,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE)
                )
            }
        }
    )
}

//@Preview
//@Composable
//fun PayConfirmDialogPreview() {
//    PayConfirmDialog(
//        transactionId = "TXN123456789",
//        onDismiss = {}
//    )
//}

