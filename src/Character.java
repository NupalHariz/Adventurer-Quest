public abstract class Character {
    private double currentHp;
    private double maxHp;
    private double atk;
    private double currentMana;
    private double maxMana;

    public Character(){}

    public Character(double hp, double atk, double mana){
        this.maxHp = hp;
        this.currentHp = hp;
        this.atk = atk;
        this.currentMana = mana;
        this.maxMana = mana;
    }

    public double getCurrentHP(){
        return currentHp;
    }

    public void setCurrentHP(double hp){
        this.currentHp = hp;
    }

    public double getMaxHP(){
        return maxHp;
    }

    public void setMaxHP(double hp){
        this.maxHp = hp;
    }

    public double getBaseAtk(){
        return atk;
    }

    public void setBaseAtk(double atk){
        this.atk = atk;
    }

    public double getMaxMana(){
        return maxMana;
    }

    public void setMaxMana(double mana){
        this.maxMana = mana;
    }

    public double getCurrentMana(){
        return currentMana;
    }

    public void setCurrentMana(double mana){
        this.currentMana = mana;
        if (getCurrentMana() > getMaxMana()){
            this.currentMana = getMaxMana();
        }
    }

    public void heal(){
        setCurrentHP(getCurrentHP() + (getMaxHP() * 0.3));
        if (getCurrentHP() > getMaxHP()){
            setCurrentHP(getMaxHP());
        }
        setCurrentMana(getCurrentMana() - 5);
    }

    public abstract double getBasicAtkDamage();

    public abstract double getHardAtkDamage();

    public abstract double getSkillDamage();

    public abstract void basicAtk(Character player, Character enemy);

    public abstract void hardAtk(Character player, Character enemy);

    public abstract void skill(Character player, Character enemy);

    public String toString(){
        return "HP = " + getCurrentHP() + "\nMana = " + getCurrentMana() + "\nAttack = " + getBaseAtk();
    }
}
