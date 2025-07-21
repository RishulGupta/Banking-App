package com.uilover.project2322.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uilover.project2322.Adapter.FriendsAdapter
import com.uilover.project2322.ViewModel.ProfileViewModel
import com.uilover.project2322.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPopular()


    }

    private fun initPopular() {
        binding.progressBar.visibility = View.VISIBLE
        binding.scrollView2.visibility = View.GONE
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.profileLiveData.observe(this) { profile ->
            if (profile != null) {
                binding.scrollView2.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.nameTxt.text = profile.profileName
                binding.totalTxt.text = profile.totalbalance
                binding.incomeTxt.text = profile.income
                binding.outcomeTxt.text = profile.outcome

                Glide.with(this@MainActivity)
                    .load(profile.profileImage)
                    .into(binding.profileImg)

                Glide.with(this@MainActivity)
                    .load(profile.banner)
                    .into(binding.banner)

                binding.friendsList.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL, false
                )
                binding.friendsList.adapter = (FriendsAdapter(profile.friend))

                binding.detailLayout.setOnClickListener {
                    val intent = Intent(this@MainActivity, OverviewActivity::class.java)
                    intent.putExtra("object", profile)
                    startActivity(intent)
                }
            }
        }
    }
}