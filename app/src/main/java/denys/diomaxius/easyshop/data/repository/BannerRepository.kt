package denys.diomaxius.easyshop.data.repository

interface BannerRepository {
    suspend fun fetchBannerUrls(): List<String>
}