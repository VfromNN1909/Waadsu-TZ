package me.vlasoff.waadsu_tz.data.network

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)
        fun <T> error(data: T, message: String) =
            Resource(status = Status.ERROR, data = data, message)
    }
}