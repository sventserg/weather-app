package com.example.weather.presentation.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather.App
import com.example.weather.R
import com.example.weather.databinding.FragmentSavedCitiesBinding
import com.example.weather.entity.dto.City
import com.example.weather.presentation.adapter.CityAdapter
import com.example.weather.presentation.adapter.CityCardDecoration
import com.example.weather.presentation.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * SavedCitiesFragment - fragment with saved cities list
 */

class SavedCitiesFragment : Fragment() {

    private var _binding: FragmentSavedCitiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel = App.daggerComponent.mainViewModel()
    private val itemDecoration = CityCardDecoration(30)

    private fun onClick(city: City) {
        viewModel.chooseCity(city)
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { it }) {
            viewModel.locationPermissionsGranted()
            viewModel.getDeviceLocation(requireContext())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedCitiesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewContainer.addItemDecoration(itemDecoration)

        var cityList = viewModel.getSavedCities()
        val selects = mutableListOf<Boolean>()
        cityList?.forEach { _ ->
            selects.add(false)
        }

        var adapter = CityAdapter(requireContext(), cityList, selects) {
            onClick(it)
        }
        binding.recyclerViewContainer.adapter = adapter

        //New city button
        binding.newCityButton.setOnClickListener {
            viewModel.clearChosenCity()
            findNavController().navigate(R.id.action_savedCitiesFragment_to_chooseCityFragment)
        }

        //Delete button
        binding.deleteButton.setOnClickListener {
            val city = viewModel.getChosenCity()
            if (city == null) {
                Snackbar.make(
                    requireView(),
                    R.string.choose_city_to_delete,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                viewModel.deleteCity(city)
                cityList = viewModel.getSavedCities()
                selects.clear()
                cityList?.forEach { _ ->
                    selects.add(false)
                }
                adapter = CityAdapter(requireContext(), cityList, selects) {
                    onClick(it)
                }
                binding.recyclerViewContainer.adapter = adapter
            }
        }

        //Chose button
        binding.chooseButton.setOnClickListener {
            val city = viewModel.getChosenCity()
            if (city == null) {
                Snackbar.make(
                    requireView(),
                    R.string.choose_city_to_show,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                viewModel.saveLastCity(city)
                viewModel.clearChosenCity()
                findNavController().navigate(R.id.action_savedCitiesFragment_to_mainFragment)
            }
        }

        //My location button
        binding.myLocationButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.clearChosenCity()
                checkPermission()
                viewModel.getCurrentCityWeather()
                if (viewModel.getIsLocationPermissionsGranted()) {
                    findNavController().navigate(R.id.action_savedCitiesFragment_to_mainFragment)
                } else {
                    Snackbar.make(
                        requireView(),
                        R.string.please_give_permission,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /**
     * Checking permission for getting device location
     */
    private fun checkPermission() {
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            viewModel.locationPermissionsGranted()
            viewModel.getDeviceLocation(requireContext())
        } else {
            launcher.launch(REQUIRED_PERMISSIONS)
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS: Array<String> = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
