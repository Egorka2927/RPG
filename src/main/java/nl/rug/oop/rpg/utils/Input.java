package nl.rug.oop.rpg.utils;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Input class.
 */
public class Input implements Serializable {

    private transient Scanner input;
    private static final long serialVersionUID = 5L;

    /**
     * Input constructor.
     */
    public Input() {
        input = new Scanner(System.in);
    }

    /**
     * User input method.
     * @param maxInput Maximum number of options.
     * @return Returns what the user inputted.
     */
    public int userInput(int maxInput) {
        int answer;

        while (true) {
            try {
                //input = new Scanner(System.in);
                answer = input.nextInt();
            } catch (InputMismatchException wrongInputException) {
                input.nextLine();
                answer = -1;
            }

            if (answer >= 0 && answer < maxInput) {
                //input.close();
                return answer;
            } else {
                System.out.println("Please enter a valid input!");
            }
        }
    }

    public Scanner getInput() {
        return input;
    }
}
