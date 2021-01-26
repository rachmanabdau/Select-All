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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.selectalldemo.R
import com.example.selectalldemo.databinding.OneTierItemBinding

class OneTierAdapter
    : ListAdapter<OneTier, OneTierViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneTierViewHolder {
        val viewHolderBinding = OneTierItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OneTierViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: OneTierViewHolder, position: Int) {
        val product = getItem(position)
        holder.onBind(product)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OneTier>() {
        override fun areItemsTheSame(oldItem: OneTier, newItem: OneTier): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OneTier, newItem: OneTier): Boolean {
            return oldItem == newItem
        }

    }
}

class OneTierViewHolder(
    private val binding: OneTierItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(product: OneTier) {
        binding.productName.text = product.productName
        binding.productDescription.text = product.productDescription
        binding.price.text =
            binding.root.context.getString(R.string.price_format, product.price.toString())

        binding.productName.setOnCheckedChangeListener(null)
        binding.productName.isChecked = product.isChecked
        binding.productName.setOnCheckedChangeListener { _, isChecked ->
            product.isChecked = isChecked
        }
    }

}
