package ca.nait.dmit.dmit2054.retrofitjsonworlddatabaseapi

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WorldDatabaseService {

    @GET("world/country")
    suspend fun fetchCountries() : List<Country>

    @GET("world/city/byCountryAndDistrict")
    suspend fun fetchCountryDistrictData(
        @Query("countryCode") countryCode: String,
        @Query("district") district: String
    ) : Array<CountryDistrictResponse>

    @GET("world/city/{id}")
    suspend fun fetchCityById(@Path("id") cityId: Int) : CountryDistrictResponse

}