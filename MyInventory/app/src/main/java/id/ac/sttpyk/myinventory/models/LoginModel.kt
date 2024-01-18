package id.ac.sttpyk.myinventory.models

data class LoginModel(
    val conntent: Conntent,
    val message: String,
    val response_code: Int
) {
    data class Conntent(
        val active: Int,
        val created_at: String,
        val id_role: Int,
        val id_user: Int,
        val updated_at: String,
        val username: String
    )
}