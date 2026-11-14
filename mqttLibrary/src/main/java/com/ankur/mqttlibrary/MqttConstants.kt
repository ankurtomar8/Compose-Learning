package com.ankur.mqttlibrary

import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import java.util.UUID

/**
 * Constants and default configuration values for MQTT operations.
 */
object MqttConstants {

    // Server URI & Host Configuration
    const val DEFAULT_HOST = "broker.hivemq.com"
    const val DEFAULT_PORT_TCP = 1883
    const val DEFAULT_PORT_SSL = 8883
    const val DEFAULT_PORT_WS = 8000
    const val DEFAULT_PORT_WSS = 8084

    const val SCHEME_TCP = "tcp://"
    const val SCHEME_SSL = "ssl://"
    const val SCHEME_WS = "ws://"
    const val SCHEME_WSS = "wss://"

    const val DEFAULT_SERVER_URI = "$SCHEME_TCP$DEFAULT_HOST:$DEFAULT_PORT_TCP"

    // Client Identification
    const val DEFAULT_CLIENT_ID_PREFIX = "AndroidClient_"
    val DEFAULT_CLIENT_ID: String
        get() = "$DEFAULT_CLIENT_ID_PREFIX${UUID.randomUUID().toString().take(8)}"

    // Topics
    const val DEFAULT_TOPIC = "mqtt/demo/topic"
    const val TEST_TOPIC = "mqtt/demo/test"
    const val STATUS_TOPIC = "mqtt/demo/status"
    const val DATA_TOPIC = "mqtt/demo/data"
    const val COMMAND_TOPIC = "mqtt/demo/command"

    const val WILDCARD_SINGLE_LEVEL = "+"
    const val WILDCARD_MULTI_LEVEL = "#"

    // QoS Levels (Quality of Service)
    const val QOS_AT_MOST_ONCE = 0
    const val QOS_AT_LEAST_ONCE = 1
    const val QOS_EXACTLY_ONCE = 2

    // Connection Timeouts & Intervals
    const val DEFAULT_CONNECTION_TIMEOUT_SECONDS = 30
    const val DEFAULT_KEEP_ALIVE_INTERVAL_SECONDS = 60
    const val DEFAULT_AUTOMATIC_RECONNECT = true
    const val DEFAULT_CLEAN_SESSION = true

    /**
     * Generates a unique client ID with optional custom prefix.
     */
    fun generateClientId(prefix: String = DEFAULT_CLIENT_ID_PREFIX): String {
        return "$prefix${System.currentTimeMillis()}"
    }

    /**
     * Builds a full broker server URI from scheme, host, and port.
     */
    fun buildServerUri(
        host: String = DEFAULT_HOST,
        port: Int = DEFAULT_PORT_TCP,
        scheme: String = SCHEME_TCP,
    ): String {
        return "$scheme$host:$port"
    }

    /**
     * Creates default MqttConnectOptions with automatic reconnect and clean session enabled.
     */
    fun createDefaultOptions(
        keepAliveInterval: Int = DEFAULT_KEEP_ALIVE_INTERVAL_SECONDS,
        connectionTimeout: Int = DEFAULT_CONNECTION_TIMEOUT_SECONDS,
        automaticReconnect: Boolean = DEFAULT_AUTOMATIC_RECONNECT,
        cleanSession: Boolean = DEFAULT_CLEAN_SESSION,
    ): MqttConnectOptions {
        return MqttConnectOptions().apply {
            this.keepAliveInterval = keepAliveInterval
            this.connectionTimeout = connectionTimeout
            this.isAutomaticReconnect = automaticReconnect
            this.isCleanSession = cleanSession
        }
    }
}
