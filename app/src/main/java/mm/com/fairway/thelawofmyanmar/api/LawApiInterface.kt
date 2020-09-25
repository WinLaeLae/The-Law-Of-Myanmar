package mm.com.fairway.thelawofmyanmar.api

import mm.com.fairway.thelawofmyanmar.ui.GovWebList.DepartmentListModel
import mm.com.fairway.thelawofmyanmar.ui.lawyer.LawyersListModel
import mm.com.fairway.thelawofmyanmar.ui.region.RegionsListModel
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.Court
import mm.com.fairway.thelawofmyanmar.ui.townshipCourt.CourtListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LawApiInterface {
    @GET("department")
    fun getDepartmentList(): Call<DepartmentListModel>

    @GET("lawyer")
    fun getLawyerList(): Call<LawyersListModel>

    @GET("court")
    fun getTspCourtsList(): Call<CourtListModel>

    @GET("court/{id}")
    fun getCourt(
        @Path("id") court_id: Int
    ): Call<Court>

    @GET("region")
    fun getRegion(): Call<RegionsListModel>


}