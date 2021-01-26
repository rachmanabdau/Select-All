/**
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.example.selectalldemo.one_tier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.selectalldemo.R
import com.example.selectalldemo.databinding.ActivityOneTierBinding

class OneTierActivity : AppCompatActivity() {

    private lateinit var viewModel: OneTierViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityOneTierBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_one_tier)
        val adapter = OneTierAdapter().apply {
            submitList(generateOneTierSample())
        }

        val viewModelFactory = OneTierViewModel.Factory(adapter as Selector)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OneTierViewModel::class.java)

        viewModel.oneTierItems.observe(this) { item ->
            binding.quantityCount.text = item.size.toString()
            binding.selectAllOneTier.isChecked = item.containsAll(adapter.getAllList())

            val price = item.sumByDouble { it.price }
            binding.totalCount.text = getString(R.string.price_format, price.toString())
        }

        binding.oneTierRv.adapter = adapter
    }

    private fun generateOneTierSample(): MutableList<OneTier> {
        return mutableListOf(
            OneTier(1, "Donut", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(2, "Eclair", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(3, "Frozen Yogurt", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(4, "Ginger Bread", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(5, "Ice Cream Sandwich", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(6, "Jelly bean", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(7, "KitKat", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(8, "Lollipop", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(9, "Marshmallow", getString(R.string.lorem_ipsum), 50000.0),
            OneTier(10, "Nougat", getString(R.string.lorem_ipsum), 50000.0)
        )
    }
}