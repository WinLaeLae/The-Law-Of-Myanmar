package mm.com.fairway.thelawofmyanmar.ui.lawsList

data class Law(
    val department: Department,
    val law_id: Int,
    val law_no: String,
    val law_type: LawType,
    val main: String,
    val name: String,
    val published_date: String,
    val region: Any,
    val release_date: String,
    val type: String
)