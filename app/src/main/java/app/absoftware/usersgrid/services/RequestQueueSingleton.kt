package app.absoftware.usersgrid.services

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class RequestQueueSingleton private constructor(private var context: Context) {
    private var requestQueue: RequestQueue?
    fun getRequestQueue(): RequestQueue? {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context!!.getApplicationContext())
        }
        return requestQueue
    }

    companion object {
        private var requestQueueSingleton: RequestQueueSingleton? = null
        @Synchronized
        fun getInstance(context: Context): RequestQueueSingleton? {
            if (requestQueueSingleton == null) {
                requestQueueSingleton = RequestQueueSingleton(context)
            }
            return requestQueueSingleton
        }
    }

    init {
        requestQueue = getRequestQueue()
    }
} //END class
