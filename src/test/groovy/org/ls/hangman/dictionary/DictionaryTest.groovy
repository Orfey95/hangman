package org.ls.hangman.dictionary

import spock.lang.Specification


class DictionaryTest extends Specification {
    def "dictionary should load all words from file"() {
        expect:
        Dictionary.words.size() == 10000
        Dictionary.words.contains('the')
        Dictionary.words.contains('groundwater')
    }

    def "getRandomWord should return a word from the dictionary within the range of complexity - EASY"() {
        when:
        String randomWord = Dictionary.getRandomWord(Difficulty.EASY)

        then:
        Dictionary.words.contains(randomWord)
        (3 .. 5).contains(randomWord.length())
    }

    def "getRandomWord should return a word from the dictionary within the range of complexity - MEDIUM"() {
        when:
        String randomWord = Dictionary.getRandomWord(Difficulty.MEDIUM)

        then:
        Dictionary.words.contains(randomWord)
        (6 .. 7).contains(randomWord.length())
    }

    def "getRandomWord should return a word from the dictionary within the range of complexity - HARD"() {
        when:
        String randomWord = Dictionary.getRandomWord(Difficulty.HARD)

        then:
        Dictionary.words.contains(randomWord)
        randomWord.length() >= 8
    }
}
