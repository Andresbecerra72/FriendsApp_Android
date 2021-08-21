package app.absoftware.usersgrid.user

class ItemUser {

    var fullname: String? = null
    var country: String? = null
    var addres: String? = null
    var city: String? = null
    var state: String? = null
    var email: String? = null
    var cell: String? = null
    var urlImage: String? = null

    constructor(
        fullname: String?,
        country: String?,
        addres: String?,
        city: String?,
        state: String?,
        email: String?,
        cell: String?,
        urlImage: String?
    ) {
        this.fullname = fullname
        this.country = country
        this.addres = addres
        this.city = city
        this.state = state
        this.email = email
        this.cell = cell
        this.urlImage = urlImage
    }
}