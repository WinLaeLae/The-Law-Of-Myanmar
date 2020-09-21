package mm.com.fairway.thelawofmyanmar.ui.lawyer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mm.com.fairway.thelawofmyanmar.R


class LawyerListFragment: Fragment() {
    val TELEPHONE_SCHEMA ="tel:"
    val PRESERVED_CHARACTER ="+"
    val HK_COUNTRY_CODE ="95"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lawyerlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
fun phoneCall(phoneNo: Int){

 // var HK_OBSERVATORY_PHONE_NUMBER  = phoneNo
    val phoneCallUri = Uri.parse(TELEPHONE_SCHEMA + PRESERVED_CHARACTER + HK_COUNTRY_CODE + phoneNo)
    val phoneCallIntent = Intent(Intent.ACTION_DIAL).also {
        it.setData(phoneCallUri)
    }
    startActivity(phoneCallIntent)

}
}