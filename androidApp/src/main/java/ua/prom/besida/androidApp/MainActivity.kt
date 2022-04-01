package ua.prom.besida.androidApp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ua.prom.besida.kmm.shared.network.AppSocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "KMM WebSocket"

        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)

        val appSocket = AppSocket("wss://echo.websocket.org")

        appSocket.stateListener = {
            println("New state: $it")

            if(it == AppSocket.State.CONNECTED) {
                appSocket.send("rom server:")
            }
        }

        appSocket.messageListener = {
            println("New message: $it")

            GlobalScope.launch(Dispatchers.Main) {
                textView.text = "Message from server: $it"
            }
        }

        appSocket.connect()
    }
}