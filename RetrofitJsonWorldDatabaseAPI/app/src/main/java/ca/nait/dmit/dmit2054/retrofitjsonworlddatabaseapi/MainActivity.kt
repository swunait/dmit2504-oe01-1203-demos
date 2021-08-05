package ca.nait.dmit.dmit2054.retrofitjsonworlddatabaseapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone1.app.dmitcapstone.ca/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val worldDatabaseApiService = retrofit.create(WorldDatabaseService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val countryList: List<Country> = worldDatabaseApiService.fetchCountries()
            val countryDistrictResponse = worldDatabaseApiService.fetchCountryDistrictData(
                "CAN",
                "Alberta"
            )
            val cityResponse = worldDatabaseApiService.fetchCityById(1812)
            withContext(Dispatchers.Main) {
                for (currentCountry in countryList) {
                    Log.i("MainActivity", currentCountry.toString())
                }
                Log.i("MainActivity", countryDistrictResponse.size.toString())
                for (currentCountryDistrictResponse in countryDistrictResponse) {
                    Log.i("MainActivity", currentCountryDistrictResponse.toString())
                }
                Log.i("MainActivity", cityResponse.toString())
            }
        }
    }
}