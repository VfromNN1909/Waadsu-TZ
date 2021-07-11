package me.vlasoff.waadsu_tz.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.vlasoff.waadsu_tz.R
import me.vlasoff.waadsu_tz.data.network.Status
import me.vlasoff.waadsu_tz.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getData().observe(this) { data ->
            data?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val coords = resource.data?.features?.map { feature ->
                            feature.geometry.coordinates
                        }?.get(1).toString()
                        Log.d("GEO_DATA", coords)
                        binding.textView.text = coords
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Чото не так", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}