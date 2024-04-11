package nl.rug.oop.rpg;

/**
 * Enemy class.
 */

public class Enemy extends NPC implements Interactable, Inspectable, Fightable {

    private Input input;

    private int enemyHealthPoints;
    private int enemyMinDamage;
    private int enemyMaxDamage;
    private int enemyDamage;
    private int coins;

    /**
     * Enemy constructor.
     * @param player The player who fights the enemy.
     * @param name Enemy's name.
     * @param description Enemy's description.
     * @param healthPoints Enemy's health points.
     * @param minDamage Minimum amount of damage that the enemy does.
     * @param maxDamage Maximum amount of damage that the enemy does.
     * @param coins Amount of coins dropped by an enemy.
     */
    public Enemy(Player player, String name, String description, int healthPoints,
                 int minDamage, int maxDamage, int coins) {
        super(player, name, description);
        this.enemyHealthPoints = healthPoints;
        this.enemyMinDamage = minDamage;
        this.enemyMaxDamage = maxDamage;
        this.coins = coins;
        this.enemyDamage = (int)(Math.random()*(maxDamage-minDamage+1)+minDamage);
    }

    @Override
    public void interact() {
        inspect();
        input = new Input();
        System.out.println("Do you want to fight the " + name + "?");
        System.out.println("(0) Yes");
        System.out.println("(1) No");
        int answer = input.userInput(2);
        if (answer == 0) {
            while (player.getHealthPoints() > 0 && this.enemyHealthPoints > 0) {
                fight();
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public int getEnemyHealthPoints() {
        return this.enemyHealthPoints;
    }

    public void setEnemyDamage() {
        this.enemyDamage = (int)(Math.random() * (this.enemyMaxDamage - this.enemyMinDamage + 1) + this.enemyMinDamage);
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public void fight() {
        System.out.println("These are your weapons:");
        for (Weapon w : player.getWeapons()) {
            System.out.println("(" + player.getWeapons().indexOf(w) + ") "
                    + w.getWeaponName() + " (" + w.getWeaponMinDamage() + " - " + w.getWeaponMaxDamage() + " damage)");
        }
        System.out.println("What do you want to fight with?");
        int answer = input.userInput(player.getWeapons().size());
        Weapon weapon = player.getWeapons().get(answer);
        this.enemyHealthPoints = this.enemyHealthPoints - weapon.getWeaponDamage();
        System.out.println("You have dealt " + weapon.getWeaponDamage() + " damage to the " + name);
        if (weapon instanceof Sword) {
            weapon.resetWeaponDamage();
        } else {
            player.getWeapons().remove(weapon);
        }
        player.setHealthPoints(player.getHealthPoints() - this.enemyDamage);
        System.out.println("You have received " + this.enemyDamage + " damage");
        if (player.getHealthPoints() < 0) {
            System.out.println("You have 0 HP left");
        } else {
            System.out.println("You have " + player.getHealthPoints() + " HP left");
        }
        if (this.enemyHealthPoints > 0) {
            System.out.println(name + " has " + this.enemyHealthPoints + " HP left");
            this.setEnemyDamage();
        } else {
            System.out.println("You have defeated the " + name);
        }
    }
}
