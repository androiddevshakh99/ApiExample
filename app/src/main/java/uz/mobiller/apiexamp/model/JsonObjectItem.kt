package uz.mobiller.apiexamp.model

import java.io.Serializable

data class JsonObjectItem(
    val cb_price: String,
    val code: String,
    val date: String,
    val nbu_buy_price: String,
    val nbu_cell_price: String,
    val title: String
): Serializable