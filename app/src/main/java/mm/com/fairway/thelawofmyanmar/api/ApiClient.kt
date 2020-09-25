package mm.com.fairway.thelawofmyanmar.api

import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.ui.lawyer.LawyersListModel
import mm.com.fairway.thelawofmyanmar.ui.region.RegionsListModel
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.Court
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.CourtListModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {
    private  val BASE_URL= "http://lawofmyanmar.dsv.hoz.mybluehost.me/api/"
 companion object{
      var imageUrl="http://lawofmyanmar.dsv.hoz.mybluehost.me/"
 }
var apiInterface:LawApiInterface
    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(LawApiInterface::class.java)
    }
    fun getDepartmentList(): Call<DepartmentListModel>{
        return apiInterface.getDepartmentList()
    }
    fun getLawyerList():Call<LawyersListModel>{
        return apiInterface.getLawyerList()
    }
    fun getTspCourt(): Call<CourtListModel>{
        return  apiInterface.getTspCourtsList()
    }
    fun getDetailCourt( courtId : Int): Call<Court>{
        return apiInterface.getCourt(courtId)
    }
    fun getRegionsList():Call<RegionsListModel>{
        return apiInterface.getRegion()
    }
}