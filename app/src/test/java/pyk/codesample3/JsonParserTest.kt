package pyk.codesample3

import org.junit.Assert.assertEquals
import org.junit.Test
import pyk.codesample3.model.support.StaticValues
import pyk.codesample3.util.JsonParser

class JsonParserTest {
    @Test fun jsonParsedCorrectly() {
        assertEquals(JsonParser().movieListFromJson(StaticValues.rawShort),
                     StaticValues.expectedShortMovieList())
    }
}