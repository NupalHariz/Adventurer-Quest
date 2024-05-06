public class Mage extends Character{

    public Mage(double hp, double atk, double mana) {
        super(hp, atk, mana);
    }

    public double getBasicAtkDamage(){
        return getBaseAtk();
    }

    public double getHardAtkDamage(){
        return getBaseAtk() * 1.2;
    }

    public double getSkillDamage(){
        return getBaseAtk() * 2.5;
    }

    @Override
    public void basicAtk(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - getBasicAtkDamage());
    }

    @Override
    public void hardAtk(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - getHardAtkDamage());
        setCurrentMana(getCurrentMana() - 10);
    }

    @Override
    public void skill(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - getSkillDamage());
        setCurrentMana(getCurrentMana() - 50);
    }
    
}
