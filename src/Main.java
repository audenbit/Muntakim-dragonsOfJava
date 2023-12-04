import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String REDBKG = "\u001B[41m";
        String BLUE = "\u001B[34m";
        String GREEN = "\u001B[32m";

        System.out.println(RED + "\n!---------- DRAGONS OF JAVA ----------!" + RESET);
        System.out.println(BLUE + "\n!-------- NAME YOUR CHARACTER --------!" + RESET);
        System.out.print("!> ");
        String name = sc.nextLine();

        // asks the player for difficulty, based on the difficulty allow the player
        // different amounts of skillpoints they can allocate. Then use those numbers on
        // the constructor.
        // difficulty 1=easy, 2=normal, 3=hard
        // 1 = 9 points, 2 = 6 points, 3 = 3 points
        // (#, #, #, #) for player stats if .substring(#, #+1) + .substring(#+3,#+4)...
        // != difficulty points then tell the player the stats don't add up

        System.out.println(BLUE + "\n!------ CHOOSE YOUR DIFFICULTY! ------!" + RESET);
        System.out.println("!--- EASY (e) MEDIUM (m) HARD (h) ----!");
        System.out.print("!> ");
        String difficulty = sc.nextLine();
        while (!(difficulty.equals("e") || difficulty.equals("m") || difficulty.equals("h"))) {
            System.out.println("\n!--------- INVALID DIFFICULTY -----------!");
            System.out.println("!-CHOOSE A DIFFICULTY WITHIN THE OPTIONS-!");
            System.out.println("!---- EASY (e) MEDIUM (m) HARD (h) ------!");
            System.out.print("!> ");
            difficulty = sc.nextLine();
        }

        int sp;
        if (difficulty.equals("e")) {
            sp = 9;
        } else if (difficulty.equals("m")) {
            sp = 6;
        } else {
            sp = 3;
        }
        int diff;
        if (difficulty.equals("e")) {
            diff = 1;
        } else if (difficulty.equals("m")) {
            diff = 2;
        } else {
            diff = 3;
        }
        System.out.println(BLUE + "\n!--------- CHOOSE YOUR STATS ---------!" + RESET);
        System.out.println("!---- (Health, Strength, Defense) ----!");
        System.out.println("!-------- FORMAT IS (#, #, #) --------!");
        System.out.println("!--------- You have " + sp + " points ---------!");
        System.out.print("!> ");

        String stats = sc.nextLine();
        int inputSp = Integer.parseInt(stats.substring(1, 2))
                + Integer.parseInt(stats.substring(4, 5)) + Integer.parseInt(stats.substring(7, 8));
        while (inputSp != sp) {
            System.out.println(BLUE + "\n!----- INVALID STAT DISTRIBUTION -----!" + RESET);
            System.out.println("!----- PLEASE DISTRIBUTE PROPERLY ----!");
            System.out.println("!-------- FORMAT IS (#, #, #) --------!");
            System.out.println("!---- (Health, Strength, Defense) ----!");
            System.out.println("!-------- YOU HAVE " + sp + " POINTS ----------!");
            System.out.print("!> ");
            stats = sc.nextLine();
            inputSp = Integer.parseInt(stats.substring(1, 2)
                    + Integer.parseInt(stats.substring(4, 5)) + Integer.parseInt(stats.substring(7, 8)));
        }

        Entity player = new Entity(Integer.parseInt(stats.substring(1, 2)), Integer.parseInt(stats.substring(4, 5)),
                Integer.parseInt(stats.substring(7, 8)), 0, diff, sp);
        System.out.print(BLUE + "\n!------------ PLAYER STATS -----------!" + RESET);
        player.getPlayerStat();

        System.out.println(RED + "\n!------------- GAME START -------------!" + RESET);
        int enemy = player.encounterEnemy();
        if (enemy == 1) {
            System.out.println("You encountered Bob the healthy slime!");
        } else if (enemy == 2) {
            System.out.println("You encountered Josh the tanky slime!");
        } else if (enemy == 3) {
            System.out.println("You encountered Michael the spiky slime!");
        } else {
            System.out.println("Time for the final boss.");
        }
        if (enemy < 4) {
            player.enemyInitiate();
            player.getEnemyStats();
            player.setPTurn(true);
            while (player.getHealth() > 0 && player.getEHealth() > 0) {
                while (player.getPTurn()) {
                    player.playerTurn();
                }
                player.enemyTurn();
                if (player.getHealth() < 0) {
                    System.out.println("You died.");
                    System.exit(0);
                }
            }
        } else {
            player.enemyInitiate();
            player.getEnemyStats();
            player.setPTurn(true);
            while (player.getHealth() > 0 && player.getEHealth() > 0) {
                while (!player.getPTurn()) {
                    player.playerTurn();
                }
                player.bossTurn();
                if (player.getHealth() < 0) {
                    System.out.println("You died.");
                    System.exit(0);
                }
            }
        }
        System.out.println(RED + "\n!----------- YOU WIN! ------------!" + RESET);
        System.out.println(RED + "!------------- THE END -------------!" + RESET);
    }
}