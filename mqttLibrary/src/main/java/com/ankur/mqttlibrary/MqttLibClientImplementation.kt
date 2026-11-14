package com.ankur.mqttlibrary

import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttLibClientImplementation {

    private var mqttClient: MqttAsyncClient? = null
    private var listener: MqttEventListener? = null

    val isConnected: Boolean
        get() = mqttClient?.isConnected == true

    fun setEventListener(listener: MqttEventListener?) {
        this.listener = listener
    }

    /**
     * Connects to the MQTT Broker using specified server URI and client configuration.
     */
    fun connect(
        serverUri: String = MqttConstants.DEFAULT_SERVER_URI,
        clientId: String = MqttConstants.generateClientId(),
        options: MqttConnectOptions = MqttConstants.createDefaultOptions(),
    ) {
        try {
            if (mqttClient?.isConnected == true) {
                return
            }

            mqttClient = MqttAsyncClient(serverUri, clientId, MemoryPersistence())
            mqttClient?.setCallback(
                object : MqttCallbackExtended {
                    override fun connectComplete(reconnect: Boolean, serverURI: String) {
                        listener?.onConnected(serverURI, reconnect)
                    }

                    override fun connectionLost(cause: Throwable?) {
                        listener?.onDisconnected(cause)
                    }

                    override fun messageArrived(topic: String, message: MqttMessage) {
                        val payload = String(message.payload, Charsets.UTF_8)
                        listener?.onMessageReceived(topic, payload)
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken?) {
                        // Handled via publish listener
                    }
                }
            )

            mqttClient?.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    // Handled by connectComplete callback in MqttCallbackExtended
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    listener?.onConnectionFailed(
                        exception ?: Exception("Connection failed to broker at $serverUri")
                    )
                }
            })

        } catch (e: Exception) {
            listener?.onConnectionFailed(e)
        }
    }

    /**
     * Subscribes to an MQTT topic.
     */
    fun subscribe(
        topic: String = MqttConstants.DEFAULT_TOPIC,
        qos: Int = MqttConstants.QOS_AT_LEAST_ONCE,
    ) {
        val client = mqttClient
        if ((client == null) || (!client.isConnected)) {
            listener?.onSubscriptionFailed(
                topic,
                IllegalStateException("Cannot subscribe. Client is not connected to MQTT broker.")
            )
            return
        }

        try {
            client.subscribe(topic, qos, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    listener?.onSubscribed(topic, qos)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    listener?.onSubscriptionFailed(
                        topic,
                        exception ?: Exception("Subscription failed for topic: $topic")
                    )
                }
            })
        } catch (e: Exception) {
            listener?.onSubscriptionFailed(topic, e)
        }
    }

    /**
     * Unsubscribes from an MQTT topic.
     */
    fun unsubscribe(topic: String = MqttConstants.DEFAULT_TOPIC) {
        val client = mqttClient
        if ((client == null) || (!client.isConnected)) {
            return
        }

        try {
            client.unsubscribe(topic, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    listener?.onUnsubscribed(topic)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    // Handle failure if needed
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Publishes a text message to a given MQTT topic.
     */
    fun publish(
        topic: String = MqttConstants.DEFAULT_TOPIC,
        message: String,
        qos: Int = MqttConstants.QOS_AT_LEAST_ONCE,
        retained: Boolean = false,
    ) {
        val client = mqttClient
        if ((client == null) || (!client.isConnected)) {
            listener?.onPublishFailed(
                topic,
                IllegalStateException("Cannot publish. Client is not connected to MQTT broker.")
            )
            return
        }

        try {
            val mqttMessage = MqttMessage(message.toByteArray(Charsets.UTF_8)).apply {
                this.qos = qos
                this.isRetained = retained
            }

            client.publish(topic, mqttMessage, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    val messageId = asyncActionToken?.messageId ?: 0
                    listener?.onPublishSuccess(topic, messageId)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    listener?.onPublishFailed(
                        topic,
                        exception ?: Exception("Publish failed for topic: $topic")
                    )
                }
            })
        } catch (e: Exception) {
            listener?.onPublishFailed(topic, e)
        }
    }

    /**
     * Disconnects from the MQTT Broker.
     */
    fun disconnect() {
        try {
            if (mqttClient?.isConnected == true) {
                mqttClient?.disconnect(null, object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken?) {
                        listener?.onDisconnected(null)
                    }

                    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                        listener?.onDisconnected(exception)
                    }
                })
            }
        } catch (e: Exception) {
            listener?.onDisconnected(e)
        }
    }
}
