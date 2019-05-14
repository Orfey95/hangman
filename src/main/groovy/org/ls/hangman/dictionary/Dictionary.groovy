package org.ls.hangman.dictionary

class Dictionary {
    static final List<String> words = new File(getClass().getResource('/dictionary.txt').toURI()).readLines()

    static String getRandomWord(Difficulty difficulty) {
        List<String> eligibleWords

        switch (difficulty) {
            case Difficulty.EASY:
                eligibleWords = words.findAll { word ->
                    (3 .. 5).contains(word.length())
                }
                break
            case Difficulty.MEDIUM:
                eligibleWords = words.findAll { word ->
                    (6 .. 7).contains(word.length())
                }
                break
            case Difficulty.HARD:
                eligibleWords = words.findAll { word ->
                    word.length() >= 8
                }
                break
        }

        Collections.shuffle(eligibleWords)
        eligibleWords.first()
    }
}
