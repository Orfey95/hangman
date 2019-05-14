package org.ls.hangman

import spock.lang.Specification

class GameTest extends Specification {
    def "displayGuessedCharactersOnly should return _'s if no guessed characters"() {
        setup:
        Game game = new Game(new Player(), 'CALENDAR')

        when:
        String hiddenWord = game.displayGuessedCharactersOnly()

        then:
        hiddenWord == '_ _ _ _ _ _ _ _'
    }

    def "displayGuessedCharactersOnly should return _'s for characters not guessed"() {
        setup:
        Game game = new Game(new Player(), 'CALENDAR')
        game.charactersGuessed << 'A'.chars[0]
        game.charactersGuessed << 'A'.chars[0]
        game.charactersGuessed << 'E'.chars[0]

        when:
        String hiddenWord = game.displayGuessedCharactersOnly()

        then:
        hiddenWord == '_ A _ E _ _ A _'
    }

    def "wordGuessed should return true if all characters in the word have been guessed"() {
        given:
        Game game = new Game(new Player(), 'CALENDAR')
        game.charactersGuessed = charactersGuessed

        expect:
        game.wordGuessed() == expectedResult

        where:
        charactersGuessed   || expectedResult
        []                  || false
        'AA'.chars          || false
        'CALENDA'.chars     || false
        'CALENDAR'.chars    || true
    }

    def "hungTheMan should return true if the number of failed character guesses >= 12"() {
        given:
        Game game = new Game(new Player(), 'CALENDAR')
        game.charactersGuessed = charactersGuessed

        expect:
        game.hungTheMan() == expectedResult

        where:
        charactersGuessed       || expectedResult
        []                      || false
        'AA'.chars              || false
        'CALENDA'.chars         || false
        'CALENDAR'.chars        || false
        'ABCDEFGHIJKL'.chars    || false
        'BFGHIJKMOPQS'.chars    || true
        'BFGHIJKMOPQST'.chars   || true
    }

    def "isValidGuess"() {
        given:
        Game game = new Game(new Player(), 'CALENDAR')
        game.charactersGuessed = charactersGuessed

        expect:
        game.isValidGuess(characterGuess) == expectedResult

        where:
        charactersGuessed       | characterGuess || expectedResult
        []                      | ''             || false
        []                      | 'AA'           || false
        []                      | 'A'            || true
        'A'.chars               | ''             || false
        'A'.chars               | 'AA'           || false
        'A'.chars               | 'A'            || false
        'A'.chars               | 'B'            || true
        'A'.chars               | '-'            || false
    }

    def "failedGuesses"() {
        given:
        Game game = new Game(new Player(), 'CALENDAR')
        game.charactersGuessed = charactersGuessed

        expect:
        game.failedGuesses() == expectedResult

        where:
        charactersGuessed       || expectedResult
        []                      || 0
        'A'.chars               || 0
        'AB'.chars              || 1
        'B'.chars               || 1
    }
}
