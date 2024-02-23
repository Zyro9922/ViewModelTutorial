package com.alihasan.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.alihasan.quotify.model.Quote
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var quoteIndex = 0;


    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[quoteIndex]

    fun nextQuote() = quoteList[++quoteIndex % quoteList.size]

    fun previousQuote() = quoteList[(--quoteIndex + quoteList.size) % quoteList.size]
}