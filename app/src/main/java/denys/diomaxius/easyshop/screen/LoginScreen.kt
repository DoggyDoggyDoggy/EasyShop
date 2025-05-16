package denys.diomaxius.easyshop.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome back!",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Sign in to your account",
            style = TextStyle(
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = {
                Text(text = "Email address")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {

            }
        ) {
            Text(
                text = "Login",
                fontSize = 22.sp
            )
        }
    }
}