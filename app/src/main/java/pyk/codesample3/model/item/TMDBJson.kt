package pyk.codesample3.model.item

data class TMDBJson(val page: Int, val total_results: Int, val total_pages: Int,
                    val results: List<Movie>)