package com.ankur.mqttlibrary

/**
 * Listener interface for handling MQTT connection, subscription, and message events.
 */
interface MqttEventListener {

    /**
     * Triggered when client successfully connects to the MQTT broker.
     *
     * @param serverUri Broker URL
     * @param reconnect True if connection was re-established automatically
     */
    fun onConnected(serverUri: String, reconnect: Boolean) {}

    /**
     * Triggered when connection attempt fails.
     *
     * @param cause Throwable detailing the error
     */
    fun onConnectionFailed(cause: Throwable) {}

    /**
     * Triggered when connection to the broker is lost or closed.
     *
     * @param cause Throwable detailing why connection was lost, or null if intentional
     */
    fun onDisconnected(cause: Throwable?) {}

    /**
     * Triggered when successfully subscribed to a topic.
     *
     * @param topic Subscribed topic name
     * @param qos Granted Quality of Service level
     */
    fun onSubscribed(topic: String, qos: Int) {}

    /**
     * Triggered when subscription to a topic fails.
     *
     * @param topic Topic attempt failed on
     * @param cause Exception detail
     */
    fun onSubscriptionFailed(topic: String, cause: Throwable) {}

    /**
     * Triggered when unsubscribed from a topic.
     *
     * @param topic Unsubscribed topic name
     */
    fun onUnsubscribed(topic: String) {}

    /**
     * Triggered when a message arrives on a subscribed topic.
     *
     * @param topic Topic message arrived on
     * @param message Message payload as String
     */
    fun onMessageReceived(topic: String, message: String) {}

    /**
     * Triggered when a message is published successfully.
     *
     * @param topic Target topic
     * @param messageId MQTT message ID
     */
    fun onPublishSuccess(topic: String, messageId: Int) {}

    /**
     * Triggered when publishing a message fails.
     *
     * @param topic Target topic
     * @param cause Exception detailing why publish failed
     */
    fun onPublishFailed(topic: String, cause: Throwable) {}
}
