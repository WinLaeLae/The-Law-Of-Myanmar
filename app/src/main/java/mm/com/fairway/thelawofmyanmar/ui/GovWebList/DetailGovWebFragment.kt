package mm.com.fairway.thelawofmyanmar.ui.GovWebList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import kotlinx.android.synthetic.main.fragment_detail_gov_web.*
import mm.com.fairway.thelawofmyanmar.R

class DetailGovWebFragment : Fragment() {

    ///private  val webView:WebView?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_gov_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataItem = arguments?.let {
            DetailGovWebFragmentArgs.fromBundle(it)
        }

        var  url = dataItem!!.govlink
        Log.d("GovLink>>>",url.toString())
        govWeb_webView.loadUrl("https://"+url)
        val weSettings= govWeb_webView.settings
        weSettings.javaScriptEnabled= true
        if (govWeb_webView!!.canGoBack()){
            govWeb_webView.goBack()
        }

    }

}