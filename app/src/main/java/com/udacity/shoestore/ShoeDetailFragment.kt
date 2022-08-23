package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.shoe = Shoe("", "", "", "")

        binding.saveButton.setOnClickListener {
            Toast.makeText(context, getString(R.string.save), Toast.LENGTH_SHORT).show()
            viewModel.setShoeDetail(
                (binding.shoe as Shoe).name,
                (binding.shoe as Shoe).size,
                (binding.shoe as Shoe).company,
                (binding.shoe as Shoe).description
            )

            findNavController().popBackStack()
        }
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
            Toast.makeText(context, getString(R.string.cancel), Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}