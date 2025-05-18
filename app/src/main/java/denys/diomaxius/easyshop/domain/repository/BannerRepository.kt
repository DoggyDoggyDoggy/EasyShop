package denys.diomaxius.easyshop.domain.repository

interface BannerRepository {
    suspend fun fetchBannerUrls(): List<String>
}