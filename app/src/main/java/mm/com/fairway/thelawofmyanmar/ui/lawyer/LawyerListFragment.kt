package mm.com.fairway.thelawofmyanmar.ui.lawyer

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_lawyerlist.*
import mm.com.fairway.thelawofmyanmar.R
import mm.com.fairway.thelawofmyanmar.adapter.LawyerAdapter
import mm.com.fairway.thelawofmyanmar.viewModel.LawsDepartmentViewModel


class LawyerListFragment : Fragment(), LawyerAdapter.ClickListener {
    val TELEPHONE_SCHEMA = "tel:"
    val PRESERVED_CHARACTER = "+"
    val HK_COUNTRY_CODE = "95"
    private lateinit var lawyerAdapter: LawyerAdapter
    private lateinit var lawyerViewModel: LawsDepartmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lawyerlist, container, false)
    }

    override fun onResume() {
        super.onResume()
        lawyerViewModel.setLoadLawyers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lawyerAdapter = LawyerAdapter()
        lawyerViewModel = ViewModelProvider(this).get(LawsDepartmentViewModel::class.java)
        recyclerView_lawyer_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lawyerAdapter
        }
        lawyerAdapter.setOnClickListener(this)
        observerLawyers()

    }

    fun observerLawyers() {
        lawyerViewModel.getLoadLawyer()
            .observe(viewLifecycleOwner, Observer<LawyersListModel> { lawyer ->
                lawyerAdapter.updateLawywrList(
                    lawyer.lawyers as ArrayList<Lawyer>
                )
                Log.d("Lawyer2>>>>", lawyer.lawyers.get(0).toString())
            })


    }

    private var phCode = ""

    fun phoneCall(phoneNo: Long) {
        Log.d("phone>>", phoneNo.toString())

        val phoneCallUri =
            Uri.parse(
                TELEPHONE_SCHEMA + PRESERVED_CHARACTER +
                        HK_COUNTRY_CODE + phCode + phoneNo
            )
        val phoneCallIntent = Intent(Intent.ACTION_DIAL).also {
            it.setData(phoneCallUri)
        }
        startActivity(phoneCallIntent)

    }

    override fun onClick(lawyer: Lawyer) {
        var phone = lawyer.phone
        //var phone = "09-420042109"
        if (phone.take(2) == "09") {
            phCode = "9"
            getPhoneNo(lawyer.phone)

        } else if (phone.take(2) == "01") {
            phCode = "1"
            getLinePhone(lawyer.phone)

        } else {
            var builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("Can't Call Phone")
                setMessage(
                    "Can't call to this Lawyer! " +
                            "\n" + "Try again to call other Lawyer!"
                )
                setIcon(android.R.drawable.ic_menu_call)
                setPositiveButton("OK")
                { dialogInterface, i ->

                }
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        // phoneCall(9420042109)
    }

    private fun getLinePhone(phone: String) {
        var ph: Long
        when (phone.length) {
            9 -> {
                ph = phone.takeLast(6).toLong()
                phoneCall(ph)
            }
            else -> {
                if(phone.length<=11){
                ph = phone.takeLast(phone.length - 3).toLong()
                phoneCall(ph)
            }else{
                    Toast.makeText(context,"Can't Call!",Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    private fun getPhoneNo(phone: String) {
        var ph: Long

        when (phone.length) {
            12 -> {
                ph = phone.takeLast(9).toLong()
                phoneCall(ph)
            }
            11 -> {
                ph = phone.takeLast(8).toLong()
                phoneCall(ph)
            }
            10 -> {
                ph = phone.takeLast(7).toLong()
                phoneCall(ph)
            }
            else -> {
                ph = phone.takeLast(9).toLong()
                phoneCall(ph)
            }

        }
    }
}