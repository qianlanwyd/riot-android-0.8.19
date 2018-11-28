package im.vector.analytics

/**
 * Defines the available tracking methods.
 */
interface Analytics {

    /**
     * Method to track a screen in the analytic solution
     * @param screen the path of the screen
     * @param title the optional title of the screen
     */
    fun trackScreen(screen: String, title: String? = null)

    /**
     * Method to track an event
     * @param event the event to track
     */
    fun trackEvent(event: TrackingEvent)

    /**
     * Method to add custom variable to the session
     * @param index the index of the variable
     * @param name the name of the variable
     * @param value the value of the variable
     */
    fun visitVariable(index: Int, name: String, value: String)

    /**
     * Method to dispatch immediately the previously not dispatched metrics
     */
    fun forceDispatch()

}