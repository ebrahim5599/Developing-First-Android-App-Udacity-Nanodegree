package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ToolbarBindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeDetailContainerBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        viewModel.shoeDetail.observe(viewLifecycleOwner, Observer {
            Log.i("TAG", "setShoeDetail: observed")
            it.forEach{ Shoe ->
                val shoeDetailContainer = DataBindingUtil.inflate<ShoeDetailContainerBinding>(inflater, R.layout.shoe_detail_container, container, false)
                shoeDetailContainer.shoe = Shoe
                binding.linearLayoutShoeList.addView(shoeDetailContainer.root)
            }
        })


        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    private fun loggingOut(){
        Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack(R.id.loginFragment, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> loggingOut()
        }
        return super.onOptionsItemSelected(item)
    }
}