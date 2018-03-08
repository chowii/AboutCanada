package au.com.cognizant.cognizantdev

import au.com.cognizant.cognizantdev.feature.model.AboutCanada
import au.com.cognizant.cognizantdev.feature.model.Fact
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AboutCanadaTest {

    private fun initAboutCanada(): AboutCanada {
        val title = "Some Title"
        val facts = mutableListOf(Fact(null, null, null))

        val aboutCanada = AboutCanada(title, facts)
        return aboutCanada
    }

    /**
     * Positive test.
     * Asserts true `isNull` returns true when all properties of a Fact object is null
     */
    @Test
    fun isNullTrueTest() {
        val aboutCanada = initAboutCanada()
        assertTrue(aboutCanada.facts.any { it.isNull })
    }

    /**
     * Negative test.
     * Asserts false `isNull` returns true when all properties of a Fact object is null
     */
    @Test
    fun isNullFalseTest() {
        val aboutCanada = initAboutCanada()
        assertFalse(aboutCanada.facts.any { !it.isNull })
    }

}
