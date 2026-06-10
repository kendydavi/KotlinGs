package br.com.fiap.dkendy.agrosatsentinel.presentation.navigation

object AppRoutes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val FIELD_LIST = "field_list"
    const val FIELD_DETAIL = "field_detail/{fieldId}"
    const val MONITORING = "monitoring"
    const val ALERTS = "alerts"

    fun fieldDetail(fieldId: Int) = "field_detail/$fieldId"
}
