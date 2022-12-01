package uz.rakhmonov.i.web_view

import android.app.ProgressDialog
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import uz.rakhmonov.i.web_view.databinding.ActivityMainBinding
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private  var url="https://"
  private  lateinit var binding: ActivityMainBinding
  private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Yuklanmoqda...")
        progressDialog.setMessage("Kuting...")

        binding.mySearchView.addTextChangedListener {
            Data.name = it.toString()
        }

        binding.btnSearch.setOnClickListener {
            if (Patterns.WEB_URL.matcher(url + binding.mySearchView.text.toString()).matches()) {
                binding.myWebView.loadUrl(url + binding.mySearchView.text.toString())

            } else {
                binding.myWebView.loadUrl("https://www.google.com/search?q=${binding.mySearchView.text.toString()}")

            }
        }


            binding.myWebView.webViewClient=object :WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressDialog.show()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressDialog.cancel()
                }

            }






    }
}