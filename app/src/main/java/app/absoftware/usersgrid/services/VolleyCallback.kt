package app.absoftware.usersgrid.services

// una interface para usar el callback
interface VolleyCallback {
    fun onSuccessResponse(result: Any?)
    fun onErrorResponse(error: String?)
}