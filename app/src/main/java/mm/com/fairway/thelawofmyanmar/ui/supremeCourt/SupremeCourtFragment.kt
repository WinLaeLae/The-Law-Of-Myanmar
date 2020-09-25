package mm.com.fairway.thelawofmyanmar.ui.supremeCourt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail_gov_web.*
import kotlinx.android.synthetic.main.fragment_supreme_court.*
import mm.com.fairway.thelawofmyanmar.R


class SupremeCourtFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supreme_court, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supreme_webView.loadUrl("http://www.unionsupremecourt.gov.mm")
        if (supreme_webView!!.canGoBack()){
            supreme_webView.goBack()
        }
    }

}