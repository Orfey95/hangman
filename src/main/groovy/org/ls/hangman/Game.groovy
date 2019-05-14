package org.ls.hangman

import org.ls.hangman.dictionary.Difficulty
import org.ls.hangman.dictionary.Dictionary

class Game {
    static final List<Character> ALPHABET = (65 .. 90).collect { asciiValue ->
        (char)asciiValue
    }

    Player player
    String wordToGuess
    List<Character> charactersGuessed = []

    Game(Player player, Difficulty difficulty) {
        this(player, Dictionary.getRandomWord(difficulty))
    }

    Game(Player player, String wordToGuess) {
        this.player = player
        this.wordToGuess = wordToGuess.toUpperCase()
        charactersGuessed.addAll(this.wordToGuess.chars.toList().minus(ALPHABET))
    }

    String displayGuessedCharactersOnly() {
        wordToGuess.replaceAll("[${ALPHABET.minus(charactersGuessed).join('')}]", '_').chars.join(' ')
    }

    def play() {
        while (!gameFinished()) {
            println displayGuessedCharactersOnly()
            println "${player.name} failed guesses: ${charactersGuessed - (wordToGuess.chars).size()}"

            charactersGuessed << askPlayerForTheirGuess(player)
            println "Characters guessed so far: $charactersGuessed"
        }

        printWinOrLose()
    }

    Character askPlayerForTheirGuess(Player player) {
        print "${player.name}, whats your guess: "
        String playersGuess = System.in.newReader().readLine().toUpperCase()

        if (isValidGuess(playersGuess)) {
            playersGuess.getChars()[0]
        } else {
            askPlayerForTheirGuess(player)
        }
    }

    boolean isValidGuess(String guess) {
        guess.length() == 1 && !charactersGuessed.contains(guess.getChars()[0]) && ALPHABET.contains(guess.getChars()[0])
    }

    boolean wordGuessed() {
        wordToGuess.chars.toList().minus(charactersGuessed).size() == 0
    }

    int failedGuesses() {
        charactersGuessed.minus(wordToGuess.chars.toList()).size()
    }

    boolean hungTheMan() {
        failedGuesses() >= 12
    }

    boolean gameFinished() {
        wordGuessed() || hungTheMan()
    }

    def printWinOrLose() {
        if (wordGuessed()) {
            println 'You won!!!'
        } else {
            println '''
         ____________
         |   /       |
         |  /        |
         | /       /---\\
         |/        |   |
         |         \\---/
         |         __|__
         |           |
         |           |
        /|\\         / \\
       / | \\       /   \\
      /  |  \\
     /   |   \\
____/____|____\\____

Better luck next time!'''
        }
    }
}
