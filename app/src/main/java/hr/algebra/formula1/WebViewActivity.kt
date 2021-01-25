package hr.algebra.formula1

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.formula1.databinding.ActivityWebViewBinding
import hr.algebra.formula1.extensions.startShareIntent

const val URL = "hr.algebra.formula1.url_name"
private lateinit var binding: ActivityWebViewBinding

private var currentUrl: String? = ""

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWebView()
        initSwipeRefreshLayout()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webView.loadUrl(intent.getStringExtra(URL))
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBarLayout.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBarLayout.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
                currentUrl = url
                super.onPageFinished(view, url)
            }
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progressBar.progress = newProgress
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                supportActionBar?.title = title
            }
        }
    }

    private fun initSwipeRefreshLayout() =
        binding.swipeRefreshLayout.setOnRefreshListener { binding.webView.reload() }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.web_view_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                startShareIntent(currentUrl)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
