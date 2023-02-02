package com.example.redditandroid.ui.detail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.redditandroid.R
import com.example.redditandroid.databinding.FragmentDetailBinding
import com.example.redditandroid.ui.BaseFragment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(args.image)
        setupButtons()
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener{
            popBackStack()
        }
        binding.btnDownload.setOnClickListener{
            downloadImage()
        }
    }

    private fun setupView(image: String) {
        Glide.with(requireContext())
            .load(image)
            .into(binding.detailImage)
    }

    private fun downloadImage() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                saveImage()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun saveImage() {
        val dir = File(Environment.getExternalStorageDirectory(), "DownloadImage")
        if (!dir.exists()) {
            dir.mkdir()
        }
        val drawable = binding.detailImage.drawable as (BitmapDrawable)

        val file = File(dir, "${System.currentTimeMillis()}.jpg")
        try {
            val fos = FileOutputStream(file)
            drawable.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            val intent = Intent()
            intent.data = Uri.fromFile(file)
            sendBroadcast(intent)
            snackBarMessage("Download complete")
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            snackBarMessage(e.toString())
        }
    }

}