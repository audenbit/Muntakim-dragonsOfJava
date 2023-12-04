import java.util.Scanner;

class Entity {
    Scanner scanner = new Scanner(System.in);
    private int playerHealth;
    private int maxHp;
    private int addedHp;
    private int playerAttack;
    private int playerDefense;
    private int originalDefense;
    private int addedDefense;
    private int playerWepDmg;
    private int enemyHealth;
    private int eMaxHp;
    private int eHealed;
    private int enemyAttack;
    private int eADmg;
    private int enemyDefense;
    private int eDefend;
    private int difficulty;
    private int sp;
    private int rewardSp;
    private int effectiveness;
    private boolean pTurn;
    private boolean bossDead;

    // PLAYER
    public Entity(int playerHealth, int playerAttack, int playerDefense, int playerWepDmg, int difficulty, int sp) {
        this.playerHealth = playerHealth + 1;
        this.playerAttack = playerAttack + 1;
        this.playerDefense = playerDefense + 1;
        this.playerWepDmg = playerWepDmg + 1;
        this.difficulty = difficulty;
        this.sp = sp;
    }

    public void setHealth(int moreHealth) {
        this.playerHealth += moreHealth;
    }

    public int getHealth() {
        return playerHealth;
    }

    public void setAttack(int moreAttack) {
        this.playerAttack += moreAttack;
    }

    public int getAttack() {
        return playerAttack;
    }

    public void setDefense(int moreDefense) {
        this.playerDefense += moreDefense;
    }

    public int getDefense() {
        return playerDefense;
    }

    public void setWepDmg(int moreWepDmg) {
        this.playerWepDmg += moreWepDmg;
    }

    public int attack1() {
        effectiveness = (int) (Math.random() * 10 + 1);
        return (int) (playerWepDmg * (playerAttack / effectiveness));
    }

    public int defend1() {
        originalDefense = playerDefense;
        effectiveness = (int) (Math.random() * playerDefense + (int) (0.25 * playerDefense));
        addedDefense = playerDefense - originalDefense;
        return effectiveness;
    }

    public int heal1() {
        addedHp = (int) (Math.random() * (maxHp / 4) + 1);
        if (addedHp + playerHealth > maxHp) {
            return maxHp;
        } else {
            return addedHp + playerHealth;
        }
    }

    public void getPlayerStat() {
        System.out.println("\nHealth: " + playerHealth + "\nStrength: " + playerAttack + "\nDefense: " + playerDefense
                + "\nWeapon Damage: " + playerWepDmg);
    }

    public void setPTurn(boolean pTurn) {
        this.pTurn = pTurn;
    }

    public boolean getPTurn() {
        return pTurn;
    }

    // ENEMY
    // difficulty1 = 7, 2 = 9, 3 = 12
    // hp enemy
    public void setEnemy1Stats() {
        if (difficulty == 1) {
            enemyHealth = 4;
            enemyAttack = 2;
            enemyDefense = 1;
        } else if (difficulty == 2) {
            enemyHealth = 6;
            enemyAttack = 3;
            enemyDefense = 1;
        } else {
            enemyHealth = 8;
            enemyAttack = 3;
            enemyDefense = 1;
        }
    }

    // defense enemy
    public void setEnemy2Stats() {
        if (difficulty == 1) {
            enemyHealth = 2;
            enemyAttack = 1;
            enemyDefense = 4;
        } else if (difficulty == 2) {
            enemyHealth = 2;
            enemyAttack = 2;
            enemyDefense = 5;
        } else {
            enemyHealth = 2;
            enemyAttack = 3;
            enemyDefense = 7;
        }
    }

    // attack enemy
    public void setEnemy3Stats() {
        if (difficulty == 1) {
            enemyHealth = 3;
            enemyAttack = 3;
            enemyDefense = 1;
        } else if (difficulty == 2) {
            enemyHealth = 4;
            enemyAttack = 4;
            enemyDefense = 1;
        } else {
            enemyHealth = 5;
            enemyAttack = 6;
            enemyDefense = 1;
        }
    }

    public void setEHealth(int moreHealth) {
        this.enemyHealth += moreHealth;
    }

    public int getEHealth() {
        return enemyHealth;
    }

    public void setEAttack(int moreAttack) {
        this.enemyAttack += moreAttack;
    }

    public int getEAttack() {
        return enemyAttack;
    }

    public void setEDefense(int moreDefense) {
        this.enemyDefense += moreDefense;
    }

    public int getEDefense() {
        return enemyDefense;
    }

    public int enemyAttack1() {
        effectiveness = (int) (Math.random() * 10 + 1);
        eADmg = (int) ((enemyAttack / effectiveness) - playerDefense);
        return eADmg;
    }

    public int enemyDefend1() {
        eDefend = (int) (Math.random() * enemyDefense + (int) (0.25 * enemyDefense));
        return eDefend;
    }

    public int enemyHeal1() {
        eHealed = (int) (Math.random() * 5 + 1);
        return effectiveness;
    }

    public void getEnemyStats() {
        System.out.println("\nEnemy Health: " + enemyHealth + "\nEnemy Strength: " + enemyAttack + "\nEnemy Defense: "
                + enemyDefense);
    }

    // System.out.println("The enemy attacks you for " + enemyAttack + " damage!");
    // System.out.println("You have " + (playerHealth - enemyAttack) + " health
    // left!");

    // intialize game, next enemy, battle choices, battle rewards, stat
    // distribution, turns, scale the enemies with the player and the player's sp
    int enemyX;

    public int encounterEnemy() {
        enemyX = (int) (Math.random() * 5 + 1);
        if (enemyX == 1) {
            // System.out.println("You encountered Bob the healthy slime!");
            return 1;
        } else if (enemyX == 2) {
            // System.out.println("You encountered Josh the tanky slime!");
            return 2;
        } else if (enemyX == 3) {
            // System.out.println("You encountered Michael the spiky slime!");
            return 3;
        } else {
            return 4;
        }
    }

    public void enemyInitiate() {
        if (enemyX == 1) {
            // System.out.println("You encountered Bob the healthy slime!");
            setEnemy1Stats();
        } else if (enemyX == 2) {
            // System.out.println("You encountered Josh the tanky slime!");
            setEnemy2Stats();
        } else if (enemyX == 3) {
            // System.out.println("You encountered Michael the spiky slime!");
            setEnemy3Stats();
        } else {
            initiateBoss();
        }
    }

    int choice;
    int choice2;

    public void playerTurn() {
        System.out.println("\n!-----What would you like to do?------!");
        System.out.println("!-----(1) Attack ---- (2) Defend------!");
        System.out.println("!--------------(3) Heal---------------!");
        System.out.print("!> ");
        int choice2 = scanner.nextInt();
        while (choice2 != 1 && choice2 != 2 && choice2 != 3) {
            System.out.println("\n!----Invalid Option! Choose Again------!");
            System.out.println("!-----(1) Attack ---- (2) Defend------!");
            System.out.println("!--------------(3) Heal---------------!");
            choice2 = scanner.nextInt();
        }
        if (choice2 == 1) {
            playerHealth -= enemyAttack1();
            System.out.println("\nYou attacked the enemy for " + playerAttack + ".\nThe enemy has "
                    + enemyHealth + ".\nleft!");
            pTurn = false;
        } else if (choice2 == 2) {
            playerDefense = defend1();
            System.out.println("\nYou defended yourself for " + addedDefense + " defense points. You now have "
                    + playerDefense + " defense points.");
            pTurn = false;
        } else if (choice2 == 3) {
            {
                playerHealth = heal1();
                setHealth(heal1());
                System.out.println("\nYou healed yourself for " + addedHp + " health. You now have "
                        + playerHealth + " health.");
                pTurn = false;
            }
        }
    }

    public void enemyTurn() {
        if (!(pTurn)) {
            enemyX = (int) (Math.random() * 3);
            if (enemyX == 1) {
                System.out.println("The enemy attacks you for " + enemyAttack + " damage!");
                System.out.println("You have " + (playerHealth - enemyAttack) + " health left!");
                pTurn = true;
            } else if (enemyX == 2) {
                System.out.println("The enemy defends!");
                enemyDefense = enemyDefend1();
                System.out.println("The enemy defended for " + enemyDefense + " defense points.");
                pTurn = true;
            } else {
                eHealed = enemyHeal1();
                System.out.println("The enemy heals for " + eHealed + " health!");
                if (eHealed + enemyHealth > eMaxHp) {
                    enemyHealth = eMaxHp;
                    System.out.println("The enemy now has " + enemyHealth + " health!");
                    pTurn = true;
                } else {
                    enemyHealth += eHealed;
                    System.out.println("The enemy now has " + enemyHealth + " health!");
                    pTurn = true;
                }
            }
        }
    }

    String stats;
    int inputedSp;

    public void distributeStats() {
        rewardSp = (int) (Math.random() * (sp / 5) + 1);
        System.out.println("\n!--------- CHOOSE YOUR STATS ---------!");
        System.out.println("!------ (HEALTH, STR, DEF, WEP) ------!");
        System.out.println("!------- FORMAT IS (#, #, #, #) ------!");
        System.out.println("!------- You have " + rewardSp + " points ------!");
        System.out.print("!> ");
        stats = scanner.nextLine();
        inputedSp = (Integer.parseInt(stats.substring(1, 2)) + Integer.parseInt(stats.substring(4, 5))
                + Integer.parseInt(stats.substring(7, 8)) + Integer.parseInt(stats.substring(11, 12)));
        while (inputedSp != rewardSp) {
            System.out.println("\n!----- INVALID STAT DISTRIBUTION -----!");
            System.out.println("!----- PLEASE DISTRIBUTE PROPERLY ----!");
            System.out.println("!-------- FORMAT IS (#, #, #) --------!");
            System.out.println("!------ (HEALTH, STR, DEF, WEP) ------!");
            System.out.println("!-------- YOU HAVE " + rewardSp + " POINTS ----------!");
            System.out.print("!> ");
            stats = scanner.nextLine();
            inputedSp = (Integer.parseInt(stats.substring(1, 2)) + Integer.parseInt(stats.substring(4, 5))
                    + Integer.parseInt(stats.substring(7, 8)) + Integer.parseInt(stats.substring(11, 12)));
        }
        playerHealth += Integer.parseInt(stats.substring(1, 2));
        playerAttack += Integer.parseInt(stats.substring(4, 5));
        playerDefense += Integer.parseInt(stats.substring(7, 8));
        playerWepDmg += Integer.parseInt(stats.substring(11, 12));
    }

    public void setEstats(int hp, int str, int def, int sp) {
        if (sp > 20) {
            enemyHealth *= (int) (sp * .5);
            enemyAttack *= (int) (sp * .5);
            enemyDefense *= (int) (sp * .5);
        }
    }

    public void initiateBoss() {
        if (enemyX == 4) {
            System.out.println("YOU ENCOUNTERED THE BOSS, LEVIATHAN THE DRAGON OF DEVOURING!");
            enemyHealth = (int) (1.25 * playerHealth);
            enemyAttack = (int) (1.25 * playerAttack);
            enemyDefense = (int) (1.25 * playerDefense);
            bossDead = false;
        }
    }

    public void bossTurn() {
        enemyX = (int) (Math.random() * 3);
        if (getEHealth() < 0) {
            System.out.println("You have slayed the Boss!");
            bossDead = true;
        }
        if (!bossDead) {
            if (enemyX == 1) {
                System.out.println("The boss attacks you for " + enemyAttack + " damage!");
                System.out.println("You have " + (playerHealth - enemyAttack) + " health left!");
                pTurn = true;
            } else if (enemyX == 2) {
                System.out.println("The boss defends!");
                enemyDefense = enemyDefend1();
                System.out.println("The boss defended for " + enemyDefense + " defense points.");
                getEnemyStats();
                pTurn = true;
            } else {
                eHealed = enemyHeal1();
                System.out.println("The boss heals for " + eHealed + " health!");
                if (eHealed + enemyHealth > eMaxHp) {
                    enemyHealth = eMaxHp;
                    System.out.println("The boss now has " + enemyHealth + " health!");
                    pTurn = true;
                } else {
                    enemyHealth += eHealed;
                    System.out.println("The boss now has " + enemyHealth + " health!");
                    pTurn = true;
                }
            }
        }
    }

    public String toString() {
        String returnString = "!> ";
        return returnString;
    }

}
