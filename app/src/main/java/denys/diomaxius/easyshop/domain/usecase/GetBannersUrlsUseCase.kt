package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.data.repository.BannerRepository
import javax.inject.Inject

class GetBannersUrlsUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    suspend operator fun invoke(): List<String> = bannerRepository.fetchBannerUrls()
}