package com.example.benjohnson.tootootflickerdemo.fragment.profile

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.benjohnson.tootootflickerdemo.R
import com.example.benjohnson.tootootflickerdemo.databinding.FragmentProfileBinding
import com.example.benjohnson.tootootflickerdemo.fragment.BaseFragment

/**
 * TODO: I intended to add a profile page although due to time restrictions I was unable to complete it
 */
class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

}