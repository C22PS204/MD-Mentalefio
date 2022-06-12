package bangkit.project.myapplication

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("images")
	val images: List<ImagesItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("external_urls")
	val externalUrls: ExternalUrls
)

data class ImagesItem(

	@field:SerializedName("width")
	val width: Any,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("height")
	val height: Any
)

data class ExternalUrls(

	@field:SerializedName("spotify")
	val spotify: String
)
