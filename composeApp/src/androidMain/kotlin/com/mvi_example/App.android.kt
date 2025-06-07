package com.mvi_example

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast


actual fun showToast(message: String, duration: ToastDuration) {
    AndroidToastUtil.show(message, duration)
}

internal object AndroidToastUtil { // 'internal' to this module (androidMain)

    private var applicationContext: Context? = null
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    /**
     * Initializes the ToastUtil with the application context.
     * Should be called once, typically from the Application class.
     */
    fun initialize(context: Context) {
        // Use applicationContext to avoid memory leaks with Activity/Service contexts
        applicationContext = context.applicationContext
    }

    /**
     * Shows a toast message. Ensures it runs on the main thread.
     *
     * @param message The message to display.
     * @param duration The common ToastDuration.
     */
    fun show(message: String, duration: ToastDuration) {
        val ctx = applicationContext
        if (ctx == null) {
            // Log an error or throw an exception if not initialized,
            // or queue the toast if you want to get more complex.
            // For simplicity, we'll just log and ignore if not initialized.
            System.err.println("AndroidToastUtil not initialized. Toast for '$message' not shown.")
            return
        }

        val androidDuration = when (duration) {
            ToastDuration.SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LONG -> Toast.LENGTH_LONG
        }

        // Ensure Toast is shown on the main thread, especially if this
        // could be called from a background thread (though LaunchedEffect is usually main).
        mainThreadHandler.post {
            Toast.makeText(ctx, message, androidDuration).show()
        }
    }
}

