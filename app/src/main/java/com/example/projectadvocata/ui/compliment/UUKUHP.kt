package com.example.projectadvocata.ui.compliment

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectadvocata.R

class UUKUHP :  AppCompatActivity(){
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uukuhp)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val webView = findViewById<WebView>(R.id.webViewUUKUHP)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:alert('Web yang berisi Kitab Undang-Undang Hukum Perdata berhasil dimuat')")
                view?.evaluateJavascript(
                    """
                    (function() {
                        // Sembunyikan semua elemen selain daftar PDF
                        document.body.innerHTML = document.querySelector('.pdf-list').outerHTML;
                    })();
                    """.trimIndent(), null
                )
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: android.webkit.JsResult): Boolean {
                Toast.makeText(this@UUKUHP, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }

        webView.loadUrl("https://www.hukumonline.com/pusatdata/detail/lt4c7b7fd88a8c3/wetboek-van-strafrecht-wvs/")
    }
}