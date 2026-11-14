package com.example.composelearning.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ankur.mqttlibrary.MqttConstants
import com.ankur.mqttlibrary.MqttEventListener
import com.ankur.mqttlibrary.MqttLibClientImplementation
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MqttActivity : ComponentActivity() {

    private val mqttClient = MqttLibClientImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningTheme {
                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MqttScreen(mqttClient = mqttClient)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mqttClient.disconnect()
    }
}

@Composable
fun MqttScreen(mqttClient: MqttLibClientImplementation) {
    var isConnected by remember { mutableStateOf(false) }
    var connectionStatus by remember { mutableStateOf("Disconnected") }
    var publishMessage by remember { mutableStateOf("Hello from Android App!") }
    var topicToSubscribe by remember { mutableStateOf(MqttConstants.DEFAULT_TOPIC) }
    val logs = remember { mutableStateListOf<String>() }

    DisposableEffect(Unit) {
        mqttClient.setEventListener(object : MqttEventListener {
            override fun onConnected(serverUri: String, reconnect: Boolean) {
                isConnected = true
                connectionStatus = "Connected to $serverUri"
                logs.add(0, "[SUCCESS] Connected! Subscribing to $topicToSubscribe...")
                // Auto subscribe on connect
                mqttClient.subscribe(topicToSubscribe)
            }

            override fun onConnectionFailed(cause: Throwable) {
                isConnected = false
                connectionStatus = "Connection Failed"
                logs.add(0, "[ERROR] Connection failed: ${cause.message}")
            }

            override fun onDisconnected(cause: Throwable?) {
                isConnected = false
                connectionStatus = "Disconnected"
                logs.add(0, "[INFO] Disconnected: ${cause?.message ?: "Clean disconnect"}")
            }

            override fun onSubscribed(topic: String, qos: Int) {
                logs.add(0, "[SUCCESS] Subscribed to topic '$topic' (QoS: $qos)")
            }

            override fun onSubscriptionFailed(topic: String, cause: Throwable) {
                logs.add(0, "[ERROR] Subscription failed for '$topic': ${cause.message}")
            }

            override fun onMessageReceived(topic: String, message: String) {
                logs.add(0, "[MESSAGE RECEIVED] Topic: $topic -> $message")
            }

            override fun onPublishSuccess(topic: String, messageId: Int) {
                logs.add(0, "[PUBLISH SUCCESS] Topic: $topic (Msg ID: $messageId)")
            }

            override fun onPublishFailed(topic: String, cause: Throwable) {
                logs.add(0, "[ERROR] Publish failed: ${cause.message}")
            }
        })

        onDispose {
            mqttClient.setEventListener(null)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "MQTT Client Demo", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Status: $connectionStatus",
            style = MaterialTheme.typography.bodyMedium,
            color = if (isConnected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    connectionStatus = "Connecting..."
                    mqttClient.connect()
                },
                enabled = !isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Connect")
            }

            Button(
                onClick = { mqttClient.disconnect() },
                enabled = isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Disconnect")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = topicToSubscribe,
            onValueChange = { topicToSubscribe = it },
            label = { Text("Topic Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { mqttClient.subscribe(topicToSubscribe) },
                enabled = isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Subscribe")
            }

            Button(
                onClick = { mqttClient.unsubscribe(topicToSubscribe) },
                enabled = isConnected,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Unsubscribe")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = publishMessage,
            onValueChange = { publishMessage = it },
            label = { Text("Message to Publish") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { mqttClient.publish(topicToSubscribe, publishMessage) },
            enabled = isConnected,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Publish Message")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Event Logs:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 8.dp)
        ) {
            items(logs) { log ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {
                    Text(
                        text = log,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
