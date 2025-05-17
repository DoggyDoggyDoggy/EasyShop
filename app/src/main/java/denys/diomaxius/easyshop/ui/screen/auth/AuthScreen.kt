package denys.diomaxius.easyshop.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.easyshop.navigation.Screen

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Start your shopping journey now!",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Best e-com platform with best prices",
            style = TextStyle(
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                navHostController.navigate(Screen.Login.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text(
                text = "Login",
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                navHostController.navigate(Screen.Signup.route) {
                    launchSingleTop = true
                }
            }
        ) {
            Text(
                text = "Sign Up",
                fontSize = 22.sp
            )
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        navHostController = rememberNavController()
    )
}