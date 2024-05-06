public class Archer extends Character{
    private boolean dodge;

    public Archer(double hp, double atk, double mana) {
        super(hp, atk, mana);

    }

    public boolean getDodge(){
        return dodge;
    }

    public void setDodge(){
        this.dodge = !dodge;
    }

    public double getBasicAtkDamage(){
        return getBaseAtk();
    }

    public double getHardAtkDamage(){
        return getBaseAtk() * 1.4;
    }

    public double getSkillDamage(){
        return getBaseAtk() * 1.25;
    }

    @Override
    public void basicAtk(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - player.getBaseAtk());
    }

    @Override
    public void hardAtk(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - getHardAtkDamage());
        setCurrentMana(getCurrentMana() - 10);
    }

    @Override
    public void skill(Character player, Character enemy) {
        enemy.setCurrentHP(enemy.getCurrentHP() - getSkillDamage());
        setDodge();
        setCurrentMana(getCurrentMana() - 50);
    }  
}
