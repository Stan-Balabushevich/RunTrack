package id.slava.nt.analitycs.domain

interface AnalyticsRepository {
    suspend fun getAnalyticsValues(): AnalyticsValues
}