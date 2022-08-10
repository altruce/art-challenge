package ch.altruce.challenge;

public class Main {

    /*
    * Welcome to this year's IMS students challenge!
    * Please read the comments bellow for instructions
    */
    public static void main(String[] args) {

        // TODO: modify these two functions in this file
        solveTheChallenge();
        createYourArt();

        // Print the canvas to the console
        Canvas.getInstance().print();

        // If you are happy with what you have created, please submit your full code on GitHub! (So that we know you didn't cheat ;)
        // https://github.com/altruce/art-challenge/
        // Please do not hesitate to submit your code, even if it's not perfect!
        // Good Luck and have a wonderful day :)
    }

    public  static  void solveTheChallenge(){
        // Get the canvas instance
        Canvas canvas = Canvas.getInstance();

        // TODO: write a function that Bruteforces every possible combination of these characters:
        char[] possibleChars = {'a', 'w', 'q', 'i', '0', '@', '#'};

        // The string you input into the SHA function should only be 10 characters or fewer

        // Write an expression like so:
        String password = ("random string");
        canvas.compareCombination(password);
        // To hash your string and compare your hash to the correct one
        // If your hash was correct, the 'compareHash' function will return TRUE

        // Hint: Your program should not run for longer than a couple of minutes maximum! If it takes much longer, you have made a mistake somewhere
        // On that note, make sure your code is optimised, else it will take much, much longer
    }

    public static void createYourArt() {
        // TODO: use this function to draw on the canvas

        // Get the instance for the canvas you'll use to draw
        Canvas canvas = Canvas.getInstance();

        // Use statements like this one and get creative!
        canvas.drawCharacter('-', 10, 10);
    }
}