package ch.altruce.challenge;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Random;
import java.util.Scanner;

public class Canvas {
    private final int blockHeight = 50;
    private final int blockWidth = 100;

    private final int sectionHeight = 25;
    private final int sectionWidth = 52;
    private char[][] blockCharacterArray = new char[sectionHeight][sectionWidth];
    private char[][] characterArray;
    private String userInput;

    private final String hash;
    private static Canvas instance = null;
    private SHA sha = new SHA();

    Canvas() {
        characterArray = new char[blockHeight][blockWidth];
        fillCharacterArrayBlank();
        hash = generatePassword();

        try {
            File secretBlockFile = new File("src/main/resources/secretblock.txt");
            Scanner fileScanner = new Scanner(secretBlockFile);
            for (int i = 0; fileScanner.hasNext(); i++) {
                blockCharacterArray[i] = fileScanner.nextLine().toCharArray();
            }

            for (int y = 0; y < blockCharacterArray.length; y++) {
                for (int i = 0; i < blockCharacterArray[y].length; i++) {
                    char c = blockCharacterArray[y][i];
                    int changed = ((int) c) - i;
                    blockCharacterArray[y][i] = (char) changed;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Generate the middle part of the image
    private void generateLines() {
        int startWidth = ((blockWidth - sectionWidth) / 2);
        int startHeight = ((blockHeight - sectionHeight) / 2) + 1;

        int xInArray = 0;
        int yInArray = 0;

        if (new SHA().getSHA(userInput).equals(hash)) {
            for (int y = 0; y < sectionHeight; y++) {
                for (int x = 0; x < sectionWidth; x++) {
                    characterArray[startHeight + y][startWidth + x] = blockCharacterArray[yInArray][xInArray];
                    xInArray++;
                }
                yInArray++;
                xInArray = 0;
            }
        } else {
            for (int y = 0; y < sectionHeight; y++) {
                for (int x = 0; x < sectionWidth; x++) {
                    characterArray[startHeight + y][startWidth + x] = blockCharacterArray[new Random().nextInt(sectionHeight)][new Random().nextInt(sectionHeight)];
                }
            }
        }
    }

    /*
     * Generate the top and bottom borderlines and write them to the array
     */
    private void border() {
        // Top Border
        for (int x = 0; x < blockWidth; x++) {
            characterArray[0][x] = '#';
            characterArray[blockHeight - 1][x] = '#';
        }

        // Either side
        for (int y = 1; y < blockHeight - 1; y++) {
            characterArray[y][0] = '#';
            characterArray[y][blockWidth - 1] = '#';
        }
    }

    /*
     * Output the generated image to the standard output
     */
    public void print() {
        border();
        generateLines();
        System.out.flush();
        for (int y = 0; y < blockHeight; y++) {
            for (int x = 0; x < blockWidth; x++) {
                System.out.print(characterArray[y][x]);
            }
            System.out.println();
        }
    }

    /*
     * Empty the array after initialisation
     */
    private void fillCharacterArrayBlank() {
        for (int y = 0; y < blockHeight; y++) {
            for (int x = 0; x < blockWidth; x++) {
                characterArray[y][x] = ' ';
            }
        }
    }

    /*
     * Draw a single char to the array
     */
    public void drawCharacter(char character, int posX, int posY) {
        boolean validPosition = (posX < blockWidth - 1 && posX >= 1) && (posY < blockHeight - 1 && posY >= 1);
        if (validPosition) {
            characterArray[posY][posX] = character;
        }
    }

    public String generatePassword() {
        String output = "";
        int length = 10;
        char[] possibleChars = {'a', 'w', 'q', 'i', '0', '@', '#'};

        for (int i = 0; i < length; i++) {
            output += possibleChars[new Random().nextInt(possibleChars.length - 1)];
        }
        return sha.getSHA(output);
    }


    /*
     * Singleton instance
     */
    public static Canvas getInstance() {
        if (instance == null)
            instance = new Canvas();
        return instance;
    }

    /*
     * Compare a user's hash with the correct one
     */
    public boolean compareCombination(String userInput) {
        this.userInput = userInput;
        return (sha.getSHA(userInput).equals(this.hash));
    }

    public String getUserInput() {
        return userInput;
    }

    public String checksum() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = Files.newInputStream(Paths.get("src/main/java/ch/altruce/challenge/Canvas.java"));
            DigestInputStream dis = new DigestInputStream(is, md);
            byte[] digest = md.digest(dis.readAllBytes());
            return new SHA().generateHexString(digest);
        } catch (Exception e) {
            System.out.println(e);
            return "error: " + e;
        }
    }
}
