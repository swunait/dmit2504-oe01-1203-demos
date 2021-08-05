package ca.nait.dmit.dmit2054.retrofitjsonworlddatabaseapi

import com.squareup.moshi.Json

data class CountryDistrictResponse(
    // "countryCode":"CAN","district":"Alberta","id":1811,"name":"Calgary","population":768082
    val countryCode: String,
    val district: String,
    @field:Json(name="id") val cityId: Int,
    val name: String,
    val population: Int
)
