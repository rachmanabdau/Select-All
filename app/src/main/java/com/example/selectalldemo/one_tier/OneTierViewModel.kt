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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OneTierViewModel(private val selector: Selector) : ViewModel() {

    private val listHolder = mutableListOf<OneTier>()

    private val _oneTierItems = MutableLiveData<List<OneTier>>(listHolder)
    val oneTierItems: LiveData<List<OneTier>> = _oneTierItems

    fun selectAll() {
        _oneTierItems.value = selector.getAllList().distinctBy { it }.toMutableList()
        selector.getAllList().forEach {
            selectItem(it)
        }
    }

    fun removeAll() {
        selector.getAllList().forEach {
            removeItem(it)
        }
        _oneTierItems.value = listHolder.also { it.clear() }
    }

    fun selectItem(product: OneTier) {
        if (listHolder.contains(product).not()) {
            selector.selectItem(product)
            _oneTierItems.value = listHolder.also {
                it.add(product)
            }
        }
    }

    fun removeItem(product: OneTier) {
        if (listHolder.contains(product)) {
            selector.removeItem(product)
            _oneTierItems.value = listHolder.apply {
                this.remove(product)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val selector: Selector) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OneTierViewModel::class.java)) {
                return OneTierViewModel(selector) as T
            }

            throw IllegalArgumentException("Unknown viewmodel class.")
        }
    }
}