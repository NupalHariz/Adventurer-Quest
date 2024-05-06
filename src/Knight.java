public class Knight extends Character{
    private double armor;

    public Knight(){}

    public Knight(double baseHP, double baseAtk, double baseMana) {
        super(baseHP, baseAtk, baseMana);
        this.armor = baseHP * 0.3;
    }


    public double getArmor(){
        return armor;
    }

    public void setArmor(double armor){
        this.armor = armor;
        if(getArmor() > (getMaxHP() * 0.5)){
            this.armor = getMaxHP() * 0.5;
        }
    }

    public double getBasicAtkDamage(){
        return getBaseAtk();
    }

    public double getHardAtkDamage(){
        return getBaseAtk() * 1.3;
    }

    public double getSkillDamage(){
        return getBaseAtk() * 0.4;
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
        setArmor(player.getMaxHP() * 0.5);
    }

}
