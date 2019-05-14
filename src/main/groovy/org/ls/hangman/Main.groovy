package org.ls.hangman

import org.ls.hangman.dictionary.Difficulty

class Main {
    static void main(String... args) {
        new Game(setPlayer(), getDifficultyLevel()).play()
    }

    static Player setPlayer() {
        print 'Player name: '
        new Player(name: System.in.newReader().readLine())
    }

    static Difficulty getDifficultyLevel() {
        print 'Select your difficulty: (EASY, MEDIUM, HARD)'
        try {
            System.in.newReader().readLine() as Difficulty
        } catch (IllegalArgumentException e) {
            getDifficultyLevel()
        }
    }
}
