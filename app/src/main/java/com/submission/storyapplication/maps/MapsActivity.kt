package com.submission.storyapplication.maps

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.databinding.ActivityMapsBinding
import com.submission.storyapplication.core.utils.Resources
import com.submission.storyapplication.core.utils.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val MapsViewModel: MapsViewModel by viewModel()
    private var itemMutableList: MutableLiveData<List<StoriesEntity>?> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getAllStoriesLocation()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

       itemMutableList.observe(this, {
           if (it != null) {
               it.forEach {
                   var location = LatLng(it.lat!!.toDouble(), it.lon!!.toDouble())
                   mMap.addMarker(
                       MarkerOptions()
                           .position(location)
                           .title(it.name)
                           .snippet(it.description)
                   )
               }
           }
       })
    }

    private fun getAllStoriesLocation(){
        val coroutineScope = MapsViewModel.viewModelScope
        coroutineScope.launch {
            MapsViewModel.maps(token = "Bearer ${Preferences.getToken()}").flowOn(
                Dispatchers.IO
            ).collect { result ->
                when (result) {
                    is Resources.Success -> {
                        itemMutableList.value= result.data
                    }
                    is Resources.Error -> {
                        Toast.makeText(applicationContext, result.message, Toast.LENGTH_SHORT)
                            .show()
                        Log.e("LoginActivity", result.message.toString())
                    }
                    is Resources.Loading -> {
                        Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}