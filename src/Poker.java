import java.util.Scanner;

/**
 * Description: The Poker program inputs a poker hand from each player,
 *              display and determine the type of hand, and declare the
 *              winner
 * compile:     javac Poker.java
 * run:         java Poker
 *
 * @author:     Jeremy Park
 */

/**
 * The Poker class contains methods that will be implemented into
 * the main method to run the program
 */
public class Poker {
    /**
     * Convert the hand to face cards and display that player's poker hand
     * @param card    value of the cards
     * @param suit    suit of the cards
     * @param player  certain player who has this hand
     */
    public static void printCards(int[] card, char[] suit, int player) {
        int i;

        // Convert certain cards into face cards
        System.out.print("Player " + player  + " hand: ");
        for(i = 0; i < card.length; i++) {
            if(card[i] == 1) {
                System.out.print('A');
                System.out.print(suit[i] + " ");
            }
            else if(card[i] == 11) {
                System.out.print('J');
                System.out.print(suit[i] + " ");
            }
            else if(card[i] == 12) {
                System.out.print('Q');
                System.out.print(suit[i] + " ");
            }
            else if(card[i] == 13) {
                System.out.print('K');
                System.out.print(suit[i] + " ");
            }

            // display all cards and suit of the hand
            else {
                System.out.print(card[i]);
                System.out.print(suit[i] + " ");
            }
        }
        System.out.println("");

        return;
    }

    /**
     * Determine if the hand is a straight flush
     * @param card    value of the cards
     * @param suit    suit of the cards
     * @return check  value indicating a straight flush
     */
    public static int straightFlush(int[] card, char[] suit) {
        int i;
        int straightCount = 0;
        int suitCount = 0;
        int check = 0;

        // check if certain card and next card are increasing respectively
        for(i = 0; i < card.length - 1; i++) {
            if(card[i + 1] == card[i] + 1 || card[0] == 1 &&
                    card[i + 1] == 10 + i) {
                straightCount += 1;
            }
            if(suit[i + 1] == suit[i]) { // check if suits are all the same
                suitCount += 1;
            }
        }
        if(straightCount == 4 && suitCount == 4) {
            check = 8;
        }
        return check;
    }

    /**
     * Determine if the hand is a four of a kind
     * @param card     value of the cards
     * @return check   value indicating a four of a kind
     */
    public static int fourKind(int[] card) {
        int i;
        int j;
        int matchCount = 0;
        int check = 0;

        for(i = 0; i < card.length; i++) {
            matchCount = 0;
            for(j = 0 + i; j < card.length; j++) {
                if(i == j) {             // skip if card is comparing with itself
                    continue;
                }
                if(card[i] == card[j]) { // check if there are four of a certain card
                    matchCount += 1;
                }
            }
            if(matchCount == 3) {      // if four of a kind found, exit
                check = 7;
                break;
            }
        }
        return check;
    }

    /**
     * Determine if the hand is a full house
     * @param card     value of the cards
     * @return check   value indicating a full house
     */
    public static int fullHouse(int[] card) {
        int i;
        int j;
        int matchCount = 0;
        boolean threeKindCheck = false;
        boolean pairCheck = false;
        int check = 0;

        for(i = 0; i < card.length; i++) {
            matchCount = 0;
            for(j = 0 + i; j < card.length; j++) {
                if(i == j) { // skip if card is comparing with itself
                    continue;
                }
                if(card[i] == card[j]) {
                    matchCount += 1;
                }
            }
            if(matchCount == 2) { // indicate three of a certain card is found
                threeKindCheck = true;
                i += 2;
            }
            else if(matchCount == 1) { // indicate two of a certain card is found
                pairCheck = true;
                i += 1;
            }
        }
        if(threeKindCheck && pairCheck) {
            check = 6;
        }
        return check;
    }

    /**
     * Determine if the hand is a flush
     * @param suit     suit of the cards
     * @return check   value indicating a straight flush
     */
    public static int flush(char[] suit) {
        int i;
        int suitCount = 0;
        int check = 0;

        for(i = 0; i < suit.length - 1; i++) {
            if(suit[i + 1] == suit[i]) { // check card and next card have same suit
                suitCount += 1;
            }
        }
        if(suitCount == 4) {
            check = 5;
        }
        return check;
    }

    /**
     * Determine if the hand is a straight
     * @param card    value of the cards
     * @return check  value indicating a straight
     */
    public static int straight(int[] card) {
        int i;
        int straightCount = 0;
        int check = 0;

        // check if certain card and next card are increasing respectively
        for(i = 0; i < card.length - 1; i++) {
            if(card[i + 1] == card[i] + 1 || card[0] == 1 &&
                    card[i + 1] == 10 + i) {
                straightCount += 1;
            }
        }
        if(straightCount == 4) {
            check = 4;
        }
        return check;
    }

    /**
     * Determine if the hand is a three of a kind
     * @param card     value of the cards
     * @return check   value indicating a three of a kind
     */
    public static int threeKind(int[] card) {
        int i;
        int j;
        int matchCount = 0;
        boolean threeKindCheck = false;
        int check = 0;

        for(i = 0; i < card.length; i++) {
            matchCount = 0;
            for(j = 0 + i; j < card.length; j++) {
                if(i == j) {
                    continue;
                }
                if(card[i] == card[j]) { // skip if card is comparing with itself
                    matchCount += 1;
                }
            }
            if(matchCount == 2) { // indicate three of a kind is found
                threeKindCheck = true;
            }
        }
        if(threeKindCheck) {
            check = 3;
        }
        return check;
    }

    /**
     * Determine if the hand is a two pair
     * @param card     value of the cards
     * @return check   value indicating a two pair
     */
    public static int twoPair(int[] card) {
        int i;
        int j;
        int matchCount = 0;
        boolean pairCheck = false;
        boolean twoPairCheck = false;
        int check = 0;

        for(i = 0; i < card.length; i++) {
            matchCount = 0;
            for(j = 0 + i; j < card.length; j++) {
                if(i == j) { // skip if card is comparing with itself
                    continue;
                }
                if(card[i] == card[j]) {
                    matchCount += 1;
                }
            }
            if(matchCount == 1 && !pairCheck) { // indicate one pair is found
                pairCheck = true;
            }
            else if(matchCount == 1 && pairCheck) { // indicate second pair is found
                twoPairCheck = true;
            }
        }
        if(pairCheck && twoPairCheck) {
            check = 2;
        }
        return check;
    }

    /**
     * Determine if the hand is a one pair
     * @param card    value of the cards
     * @return check  value indicating a one pair
     */
    public static int onePair(int[] card) {
        int i;
        int j;
        int matchCount = 0;
        int check = 0;

        for(i = 0; i < card.length; i++) {
            matchCount = 0;
            for(j = 0 + i; j < card.length; j++) {
                if(i == j) { // skip if card is comparing with itself
                    continue;
                }
                if(card[i] == card[j]) {
                    matchCount += 1;
                }
            }
            if(matchCount == 1) {
                check = 1;
            }
        }
        return check;
    }

    /**
     * Print out the type of hand the certain player has
     * @param hand    value and the suit of the cards
     * @param player  certain player who has this hand
     */
    public static void printHand(int[][] hand, int player) {

        System.out.print("Best hand: ");
        if(hand[player][0] == 8) {
            System.out.println("STRAIGHT FLUSH");
        }
        else if(hand[player][1] == 7) {
            System.out.println("FOUR OF A KIND");
        }
        else if(hand[player][2] == 6) {
            System.out.println("FULLHOUSE");
        }
        else if(hand[player][3] == 5) {
            System.out.println("FLUSH");
        }
        else if(hand[player][4] == 4) {
            System.out.println("STRAIGHT");
        }
        else if(hand[player][5] == 3) {
            System.out.println("THREE OF A KIND");
        }
        else if(hand[player][6] == 2) {
            System.out.println("TWO PAIR");
        }
        else if(hand[player][7] == 1) {
            System.out.println("ONE PAIR");
        }
        else {
            System.out.println("HIGH CARD");
        }
    }

    /**
     * Print out the type of hand the certain player has
     * @param cardHand  an empty array to hold input of number value of the card
     * @param player    an empty array to hold input of suit of the card
     */
    public static void enterCards(int[] cardHand, char[] suitHand) {
        int i;
        Scanner scnr = new Scanner(System.in);

        for(i = 0; i < 5; i++) {
            cardHand[i] = scnr.nextInt();
            suitHand[i] = scnr.next().charAt(0);
        }
    }

    /**
     * The main method starts at program execution, take the user input of the
     * first player, display the hand, and determine the hand of that player;
     * repeat for player 2 and then print the winning hand or tie
     * @param args  array of strings
     */
    public static void main(String[] args) {
        int[] cardHand = new int[5];
        char[] suitHand = new char[5];
        int[][] discoverHand = new int[2][8];
        int i;
        int j;

        System.out.print("Enter Player 1's cards: ");
        enterCards(cardHand, suitHand);

        printCards(cardHand, suitHand, 1);

        discoverHand[0][0] = straightFlush(cardHand, suitHand);
        discoverHand[0][1] = fourKind(cardHand);
        discoverHand[0][2] = fullHouse(cardHand);
        discoverHand[0][3] = flush(suitHand);
        discoverHand[0][4] = straight(cardHand);
        discoverHand[0][5] = threeKind(cardHand);
        discoverHand[0][6] = twoPair(cardHand);
        discoverHand[0][7] = onePair(cardHand);

        printHand(discoverHand, 0);


        System.out.print("Enter Player 2's cards: ");
        enterCards(cardHand, suitHand);

        printCards(cardHand, suitHand, 2);

        discoverHand[1][0] = straightFlush(cardHand, suitHand);
        discoverHand[1][1] = fourKind(cardHand);
        discoverHand[1][2] = fullHouse(cardHand);
        discoverHand[1][3] = flush(suitHand);
        discoverHand[1][4] = straight(cardHand);
        discoverHand[1][5] = threeKind(cardHand);
        discoverHand[1][6] = twoPair(cardHand);
        discoverHand[1][7] = onePair(cardHand);

        printHand(discoverHand, 1);

        for(i = 0; i < 8; i++) {
            if(discoverHand[0][i] == 0 && discoverHand[1][i] == 0) {
                if(i == 7) {  // if reached end of the list with two high card
                    System.out.println("Tie!");
                }
                else {
                    continue; // skip invalid comparisons
                }
            }
            else if(discoverHand[0][i] < discoverHand[1][i]) {
                System.out.println("Player 2 wins!");
                break;
            }
            else if(discoverHand[0][i] > discoverHand[1][i]) {
                System.out.println("Player 1 wins!");
                break;
            }
            else if(discoverHand[0][i] == discoverHand[0][i]) {
                System.out.println("Tie!");
                break;
            }
        }
        return;
    }
}


