public class Orc extends Character {
    public int angerMode;

    public Orc(double hp, double atk, double mana) {
        super(hp, atk, mana);
        this.angerMode = 0;
    }

    public int getAngerMode() {
        return angerMode;
    }

    public void setAngerMode(int angerMode) {
        this.angerMode = angerMode;
        if(getAngerMode() < 0) {
            this.angerMode = 0;
        } else if (getAngerMode() > 3){
            this.angerMode = 3;
        }
    }

    public double getBasicAtkDamage(){
        if (getAngerMode() > 0){
            return getBaseAtk() * 2.4;
        } else {
            return getBaseAtk() * 1.2;
        }
    }

    @Override
    public double getHardAtkDamage() {
        if (getAngerMode() > 0){
            return getBaseAtk() * 3.5;
        } else {
            return getBaseAtk() * 2;
        }
    }

    @Override
    public double getSkillDamage() {
        return getBaseAtk() * 0.3;
    }

    @Override
    public void basicAtk(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = 0;

                if (getAngerMode() > 0) {
                    damage = enemy.getBaseAtk() * 2.4;
                } else {
                    damage = enemy.getBaseAtk() * 1.2;
                }

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else {
                if (getAngerMode() > 0) {
                    player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 2.4));
                } else {
                    player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 1.2));
                }
            }
        } else {
            if (getAngerMode() > 0) {
                player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 2.4));
            } else {
                player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 1.2));
            }
        }
        setAngerMode(getAngerMode() - 1);
    }

    @Override
    public void hardAtk(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = 0;

                if (getAngerMode() > 0) {
                    damage = enemy.getBaseAtk() * 3.5;
                } else {
                    damage = enemy.getBaseAtk() * 2;
                }

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else if (player instanceof Archer) {
                if (((Archer) player).getDodge() == true) {
                    ((Archer) player).setDodge();
                } else {
                    player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 2));
                }
            }else {
                if (getAngerMode() > 0) {
                    player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 3.5));
                } else {
                    player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 2));
                }
            }
        } else {
            if (getAngerMode() > 0) {
                player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 3.5));
            } else {
                player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 2));
            }
        }
        setAngerMode(0);
        enemy.setCurrentMana(enemy.getCurrentMana() - 40);
    }

    @Override
    public void skill(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = enemy.getBaseAtk() * 0.3;

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else {
                player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 0.3));
            }
        } else {
            player.setCurrentHP(player.getCurrentHP() - (enemy.getBaseAtk() * 0.3));
        }

        setAngerMode(3);
        enemy.setCurrentMana(enemy.getCurrentMana() - 70);
    }
}
