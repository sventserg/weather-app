package com.example.weather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather.App
import com.example.weather.R
import com.example.weather.databinding.FragmentChooseCityBinding
import com.example.weather.entity.dto.City
import com.example.weather.presentation.adapter.CityAdapter
import com.example.weather.presentation.adapter.CityCardDecoration
import com.example.weather.presentation.viewModel.MainViewModel
import kotlinx.coroutines.launch

/**
 * ChooseCityFragment - fragment for searching city by name
 */
class ChooseCityFragment : Fragment() {

    private var _binding: FragmentChooseCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel = App.daggerComponent.mainViewModel()
    private val itemDecoration = CityCardDecoration(30)

    private fun onClickCity(item: City) {
        viewModel.saveLastCity(item)
        viewModel.saveCity(item)
        findNavController().navigate(R.id.action_chooseCityFragment_to_mainFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseCityBinding.inflate(inflater)
        return binding.root
    }

    /**
     * Visibility of fragment items during sending and receiving data from server
     */
    private fun loadingVision() {
        binding.loadingAnimation.visibility = View.VISIBLE
        binding.newCityList.visibility = View.GONE
    }

    /**
     * Visibility of fragment items after receiving data from server
     */
    private fun loadedVision() {
        binding.loadingAnimation.visibility = View.GONE
        binding.newCityList.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newCityList.addItemDecoration(itemDecoration)
        loadingVision()

        binding.editText.addTextChangedListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getCityList(binding.editText.text.toString())
                loadedVision()
                val selects = mutableListOf<Boolean>()
                val cities = viewModel.getCities()
                cities.forEach { _ ->
                    selects.add(false)
                }
                val cityAdapter = CityAdapter(requireContext(), cities, selects) {
                    onClickCity(it)
                }
                binding.newCityList.adapter = cityAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}