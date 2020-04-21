package xyz.tspace.hotelbolt.model

data class PixabayImage(
    val id: String,
    val previewURL: String
) {

    val webformatURL: String? = null
    val largeImageURL: String? = null
    val type: String? = null
}

data class Pixabay(
    val total: Int,
    val totalHits: Int,
    val hits: Array<PixabayImage>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Pixabay
        if (!hits.contentEquals(other.hits)) return false
        return true
    }

    override fun hashCode(): Int {
        return hits.contentHashCode()
    }
}


