package com.example.redditandroid.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.redditandroid.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    fun snackBarMessage(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    fun sendBroadcast(intent: Intent) {
        (requireActivity() as? MainActivity)?.sendBroadcast(intent)
    }

    fun popBackStack() {
        findNavController().popBackStack()
    }

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

}