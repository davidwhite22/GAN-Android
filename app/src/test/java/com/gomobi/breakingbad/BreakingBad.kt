package com.gomobi.breakingbad

import com.gomobi.breakingbad.model.BadCharacter
import com.gomobi.breakingbad.model.CharacterFilter
import com.google.gson.Gson
import org.junit.Test
import java.lang.Exception
import org.junit.Assert.*
/**
 * Project Name: BreakingBad
 * Author: David White
 * Email: gomobile.dw@gmail.com
 * Date: 19/11/2020
 */


class BreakingBad {

    @Test
    fun charactersFromJson() {
        var ls: List<BadCharacter>? = null
        val gson = Gson()
        try {
            ls = gson.fromJson(sampleJson, Array<BadCharacter>::class.java).toList()
            assertEquals(3, ls.size)
            return
        } catch (e: Exception) {
            // if we get here then we have a model problem
        }
        assertNotNull("Cannot parse JSON", ls)
    }

    private fun loadDummyData(): List<BadCharacter> {
        val gson = Gson()
        return gson.fromJson(sampleJson, Array<BadCharacter>::class.java).toList()
    }

    @Test
    fun characterNullCompliance() {
        val ls = loadDummyData()
        val character = ls[2] // Kimberly Wexler - appearance is null
        val filter = CharacterFilter("K", 1)
        assertFalse("Invalid compliance", character.compliesWith(filter))
    }

    @Test
    fun characterCompliance() {
        val ls = loadDummyData()
        val character = ls[0]
        val filter = CharacterFilter("W", 1)
        assertTrue("Invalid compliance", character.compliesWith(filter))
    }

    @Test
    fun characterFilter() {
        val ls = loadDummyData()
        val filtered = ArrayList<BadCharacter>()
        val filter = CharacterFilter("J", 1)
        for (character in ls) {
            if (character.compliesWith(filter)) {
                filtered.add(character)
            }
        }
        assertEquals("Incorrect filter results", 1, filtered.size)

    }

    @Test
    fun characterNameFilter() {
        val ls = loadDummyData()
        val filtered = ArrayList<BadCharacter>()
        val filter = CharacterFilter("W", 0)
        for (character in ls) {
            if (character.compliesWith(filter)) {
                filtered.add(character)
            }
        }
        assertEquals("Incorrect filter results", 1, filtered.size)
    }

    @Test
    fun characterAppearanceFilter() {
        val ls = loadDummyData()
        val filtered = ArrayList<BadCharacter>()
        val filter = CharacterFilter("", 1)
        for (character in ls) {
            if (character.compliesWith(filter)) {
                filtered.add(character)
            }
        }
        assertEquals("Incorrect filter results", 2, filtered.size)
    }

    @Test
    fun occupationString() {
        val ls = loadDummyData()
        val character = ls[0] // Walter White
        val occupStr = character.makeOccupationString()
        val expected = "High School Chemistry Teacher\nMeth King Pin"
        assertEquals("Invalid occupation format", expected, occupStr)
    }

    @Test
    fun appearanceNullString() {
        val ls = loadDummyData()
        val character = ls[2] // Kimberly Wexler - appearance is null
        val appStr = character.makeApperancesString()
        val expected = ""
        assertEquals("Invalid appearances format", expected, appStr)
    }

    @Test
    fun appearanceString() {
        val ls = loadDummyData()
        val character = ls[1] // Jesse Pinkman
        val appStr = character.makeApperancesString()
        val expected = "1, 2, 3, 4, 5"
        assertEquals("Invalid appearances format", expected, appStr)

    }
    companion object {
        private const val sampleJson = "[{'char_id':1,'name':'Walter White','birthday':'09-07-1958','occupation':['High School Chemistry Teacher','Meth King Pin'],'img':'https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg','status':'Presumed dead','nickname':'Heisenberg','appearance':[1,2,3,4,5],'portrayed':'Bryan Cranston','category':'Breaking Bad','better_call_saul_appearance':[1,2,3,4]}," +
                "{'char_id':2,'name':'Jesse Pinkman','birthday':'09-24-1984','occupation':['Meth Dealer'],'img':'https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441','status':'Alive','nickname':'Cap n Cook','appearance':[1,2,3,4,5],'portrayed':'Aaron Paul','category':'Breaking Bad','better_call_saul_appearance':[1,2,3,4]}," +
                "{'char_id': 112,'name': 'Kimberly Wexler','birthday': 'Unknown','occupation': ['Lawyer'],'img': 'https://vignette.wikia.nocookie.net/breakingbad/images/f/f7/BCS_S4_Kim_Wexler.jpg/revision/latest?cb=20180824195845','status': 'Alive','nickname': 'Kim','appearance': null,'portrayed': 'Rhea Seehorn','category': 'Better Call Saul','better_call_saul_appearance': [3, 4]}]"
    }
}