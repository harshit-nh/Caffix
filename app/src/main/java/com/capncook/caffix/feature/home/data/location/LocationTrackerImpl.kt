package com.capncook.caffix.feature.home.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.home.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {

    override suspend fun getCurrentAddress(): Resource<String> {

        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasCoarseLocationPermission && !hasFineLocationPermission) {
            return Resource.Error("Please grant location permission")
        }

        if(!isGpsEnabled) {
            return Resource.Error("Select Delivery Location")
        }


        return suspendCancellableCoroutine { cont ->

            locationClient.lastLocation.apply {

                if(isComplete) {
                    if(isSuccessful && result != null) {
                        cont.resume(getAddressFromCoordinates(result.latitude, result.longitude))
                    }else{
                        cont.resume(Resource.Error("Select Delivery Location"))
                    }
                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener { location ->
                    if(location != null) {
                        cont.resume(getAddressFromCoordinates(location.latitude, location.longitude))
                    }else{
                        cont.resume(Resource.Error("Select Delivery Location"))
                    }
                }
                addOnFailureListener {
                    cont.resume(Resource.Error(it.message ?: "Unknown location error"))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }


    }



    private fun getAddressFromCoordinates(lat: Double, lon: Double): Resource<String> {

        return try {

            val geocoder = Geocoder(application, Locale.getDefault())

            val addresses = geocoder.getFromLocation(lat, lon, 1)

            if(!addresses.isNullOrEmpty()) {

                val address = addresses[0]

                val addressText = listOfNotNull(
                    address.subLocality,
                    address.locality,
                    address.adminArea
                ).joinToString(", ")


                if(addressText.isNotEmpty()) Resource.Success(addressText) else Resource.Error("Address not found")

            }else{
                Resource.Error("Address not found")
            }
        }catch(e: Exception) {
            Resource.Error("Select Delivery Location")
        }
    }


}