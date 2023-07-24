package com.harisewak.knowledge

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.harisewak.knowledge.ui.theme.KnowledgeTheme
import com.harisewak.knowledge.ui.theme.threads.LooperThread
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val looperThread = LooperThread()
        looperThread.start()
        setContent {
            KnowledgeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UI(looperThread.handler)                }
            }
        }
    }
}

@Composable
fun UI(bgHandler: Handler?) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Log.i("LooperThread", "onClick")
                bgHandler?.let {
                    Log.i("LooperThread", "onClick - handler not null")
                    val msg = it.obtainMessage()
                    msg.what = Random.nextInt(1000)
                    it.sendMessage(msg)
                }
            }
        ) {
            Text("Button1")
        }
        Button(
            onClick = {
                Toast.makeText(context, "Button click", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Button2")
        }
        Button(
            onClick = {
                Toast.makeText(context, "Button click", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Button3")
        }
        Button(
            onClick = {
                Toast.makeText(context, "Button click", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Button4")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KnowledgeTheme {
        UI(null)
    }
}