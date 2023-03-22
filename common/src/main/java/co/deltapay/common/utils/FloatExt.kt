package co.deltapay.common.utils

fun Float.toCurrencyFormat(symbol: String = "KES"): String {
    return "$symbol %.2f".format(this)
}
