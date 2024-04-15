package com.example.mtl_clothes.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityLocationBinding
import com.example.mtl_clothes.viewmodel.CommonVM
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import java.util.Locale


class LocationActivity : BaseActivity<ActivityLocationBinding, CommonVM>(), OnMapReadyCallback {

    var currentLocation: Location? = null
    var mMap: GoogleMap? = null
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
            }

            else -> {
                // No location access granted.
            }
        }
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun initView() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        }
        var task = fusedLocationClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                var mapFragment =
                    supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        }
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityLocationBinding {
        return ActivityLocationBinding.inflate(layoutInflater)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var lat: Double = currentLocation?.latitude!!
        var long: Double = currentLocation?.longitude!!
        var sydney = LatLng(lat, long)
        var marker = MarkerOptions().position(sydney).title("Your Location")
        var address = getAddress(lat, long)
        marker.snippet(address)
        mMap?.addMarker(marker)
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap?.animateCamera(CameraUpdateFactory.zoomIn());
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null);
        getDeliveryPersonLocation()
    }

    fun getAddress(lat: Double, long: Double): String {
        try {
            val geocoder = Geocoder(this, Locale.getDefault())

            val listResult: List<Address>? = geocoder.getFromLocation(
                lat,
                long,
                1
            )

            if (!listResult.isNullOrEmpty()) {
                val address: String = listResult[0].getAddressLine(0)
                Log.d("address==", address) // Thêm dòng log ở đây
                return address
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("address==", "UnKnown") // Thêm dòng log ở đây để biết khi nào trả về UnKnown
        return "UnKnown"
    }

    private fun drawRouteOnMap(startLocation: LatLng, endLocation: LatLng) {
        // Xác định đường line từ startLocation đến endLocation
        val polylineOptions = PolylineOptions()
            .add(startLocation, endLocation)
            .width(5f)
            .color(Color.RED)

        // Thêm đường line vào bản đồ
        mMap?.addPolyline(polylineOptions)
    }

    private fun getDeliveryPersonLocation() {
        val deliveryAddress =
            "8A Tôn Thất Thuyết, Mỹ Đình, Nam Từ Liêm, Hà Nội 100000" // Thay bằng tên địa chỉ thực tế của người giao hàng
        val deliveryLocation = getLocationFromAddress(deliveryAddress)
        var lat: Double = currentLocation?.latitude!!
        var long: Double = currentLocation?.longitude!!
        if (deliveryLocation != null) {
            mMap?.addMarker(MarkerOptions().position(deliveryLocation).title("My Location"))

            drawRouteOnMap(
                LatLng(lat, long),
                deliveryLocation
            ) // currentLocation là vị trí hiện tại của bạn
        } else {
        }
    }

    private fun getLocationFromAddress(address: String): LatLng? {
        val geocoder =
            Geocoder(this) // Thay context bằng context của hoạt động hoặc ứng dụng của bạn
        val addressList = geocoder.getFromLocationName(address, 1)
        if (addressList != null && addressList.isNotEmpty()) {
            val location = addressList[0]
            return LatLng(location.latitude, location.longitude)
        }
        return null
    }
}