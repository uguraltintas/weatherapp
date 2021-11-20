package com.uguraltintas.weatherapp.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.uguraltintas.weatherapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionID = 1010
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        requestPermission()
    }

    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }

        return false

    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getLastLocation() {
        //TODO: Konum alınmıyor
        if (checkPermission()) {
            if (isLocationEnabled()) {
                GlobalScope.launch {
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                        location?.let {
                            val intent =
                                Intent(this@SplashScreenActivity, MainActivity::class.java)
                            val bundle = Bundle()
                            val geocoder = Geocoder(this@SplashScreenActivity, Locale.getDefault())
                            val address =
                                geocoder.getFromLocation(location.latitude, location.longitude, 1)
                            val cityName = if (address[0].locality == null) {
                                address[0].adminArea
                            } else {
                                address[0].locality
                            }
                            bundle.putDouble("lat", location.latitude)
                            bundle.putDouble("lon", location.longitude)
                            bundle.putString("cityName", cityName)
                            intent.putExtras(bundle)
                            startActivity(intent)
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            finish()
                        }
                    }
                    fusedLocationProviderClient.lastLocation.addOnFailureListener {
                        println(it)
                    }
                }
            } else {
                Toast.makeText(this, R.string.gps, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionID -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    getLastLocation()
                }
                return
            }
            else -> {
            }
        }
    }
}