package mm.com.fairway.thelawofmyanmar.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mm.com.fairway.thelawofmyanmar.api.ApiClient
import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.ui.lawyer.LawyersListModel
import mm.com.fairway.thelawofmyanmar.ui.region.RegionsListModel
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.Court
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.CourtListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LawsDepartmentViewModel:ViewModel() {
    private  var apiClient=ApiClient()
    private var lawDepartment:MutableLiveData<DepartmentListModel> = MutableLiveData()
    private var lawyers:MutableLiveData<LawyersListModel> = MutableLiveData()
    private var courts: MutableLiveData<CourtListModel> = MutableLiveData()
    private  var courtdetail:MutableLiveData<Court> = MutableLiveData()
    private  var region:MutableLiveData<RegionsListModel> = MutableLiveData()
    fun getRegion():LiveData<RegionsListModel> = region
    fun setRegionList(){
        var regionApiCall = apiClient.getRegionsList()
        regionApiCall.enqueue(object : Callback<RegionsListModel>{
            override fun onResponse(
                call: Call<RegionsListModel>,
                response: Response<RegionsListModel>
            ) {
                region.value=response.body()
            }

            override fun onFailure(call: Call<RegionsListModel>, t: Throwable) {
                Log.d("region>>>>",t.toString())
            }

        })
    }
    fun setDetailCourt(courtID: Int){
        var detaiCourtApiCall= apiClient.getDetailCourt(courtID)
        detaiCourtApiCall.enqueue(object : Callback<Court>{
            override fun onResponse(call: Call<Court>, response: Response<Court>) {
                courtdetail.value=response.body()
            }

            override fun onFailure(call: Call<Court>, t: Throwable) {
                Log.d("DeCourt>>>>",t.toString())
            }

        })
    }
    fun getDetailCourt():LiveData<Court> = courtdetail
    fun setLoadCourts(){
        var courtApiCall=apiClient.getTspCourt()
        courtApiCall.enqueue(object : Callback<CourtListModel>{
            override fun onResponse(
                call: Call<CourtListModel>,
                response: Response<CourtListModel>
            ) {
               courts.value= response.body()
                Log.d("Courtlist>>>",response.toString())
            }

            override fun onFailure(call: Call<CourtListModel>, t: Throwable) {
                Log.d("court>>>>",t.toString())
            }

        })
    }
    fun getCourtsList():LiveData<CourtListModel> = courts
    fun setLoadLawyers(){
      var lawyerApiCall= apiClient.getLawyerList()
        lawyerApiCall.enqueue(object :Callback<LawyersListModel>{
            override fun onResponse(
                call: Call<LawyersListModel>,
                response: Response<LawyersListModel>
            ) {
                lawyers.value=response.body()
                Log.d("Lawyerlist>>>",response.toString())
            }

            override fun onFailure(call: Call<LawyersListModel>, t: Throwable) {
                Log.d("Lawyer>>>>",t.toString())
            }

        })

    }
    fun getLoadLawyer():LiveData<LawyersListModel> = lawyers
    fun setLoadDepartments(){
 // var apiClient= ApiClient()
    var apiCall=apiClient.getDepartmentList()
apiCall.enqueue(object : Callback<DepartmentListModel>{
    override fun onResponse(
        call: Call<DepartmentListModel>,
        response: Response<DepartmentListModel>
    ) {
        lawDepartment.value= response.body()
        Log.d("respone>>>",response.toString())
    }

    override fun onFailure(call: Call<DepartmentListModel>, t: Throwable) {
        Log.d("LawDepart>>>>",t.toString())
    }

})
}
    fun getLoadDepartments():LiveData<DepartmentListModel> = lawDepartment

}