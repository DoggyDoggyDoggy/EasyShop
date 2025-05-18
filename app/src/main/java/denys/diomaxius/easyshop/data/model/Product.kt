package denys.diomaxius.easyshop.data.model

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val actualPrice: String,
    val category: String,
    val images: List<String>
)
