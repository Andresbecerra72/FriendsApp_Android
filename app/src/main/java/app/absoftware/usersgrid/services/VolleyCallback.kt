package app.absoftware.usersgrid.services

interface VolleyCallback {
    fun onSuccessResponse(result: Any?)
    fun onErrorResponse(error: String?)
}