package ch.altruce.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AutomatedTest {

    @org.junit.jupiter.api.Test
    void solveTheChallenge() {
        // Arrange
        Canvas canvas = Canvas.getInstance();

        // Act
        Main.solveTheChallenge();

        // Assert
        assertTrue(canvas.compareCombination(canvas.getUserInput()));
    }

    @org.junit.jupiter.api.Test
    void checkCanvasFileIntegrity(){
        // Arrange
        Canvas canvas = Canvas.getInstance();
        String checksum = "cc7d362d8abb2ce2e557283b5d61b505";

        // Act
        String userChecksum = canvas.checksum();

        // Assert
        assertEquals(checksum, userChecksum);

    }
}