public class Slime extends Character {

    public Slime() {
    }

    public Slime(double baseHP, double baseAtk, double baseMana) {
        super(baseHP, baseAtk, baseMana);
    }

    
    @Override
    public double getBasicAtkDamage() {
        return getBaseAtk() * 0.3;
    }

    @Override
    public double getHardAtkDamage() {
        return getBaseAtk() * 0.75;
    }

    @Override
    public double getSkillDamage() {
        return getBaseAtk() * 2;
    }

    @Override
    public void basicAtk(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = getBasicAtkDamage();

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else {
                player.setCurrentHP(player.getCurrentHP() - (getBasicAtkDamage()));
            }
        } else if (player instanceof Archer) {
            if (((Archer) player).getDodge() == true) {
                ((Archer) player).setDodge();
            } else {
                player.setCurrentHP(player.getCurrentHP() - getBasicAtkDamage());
            }
        } else {
            player.setCurrentHP(player.getCurrentHP() - getBasicAtkDamage());

        }
    }

    @Override
    public void hardAtk(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = getHardAtkDamage();

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else {
                player.setCurrentHP(player.getCurrentHP() - (getHardAtkDamage()));
            }
        } else if (player instanceof Archer) {
            if (((Archer) player).getDodge() == true) {
                ((Archer) player).setDodge();
            } else {
                player.setCurrentHP(player.getCurrentHP() - getHardAtkDamage());
            }
        } else {
            player.setCurrentHP(player.getCurrentHP() - getHardAtkDamage());
        }
        enemy.setCurrentMana(enemy.getCurrentMana() - 10);
    }

    @Override
    public void skill(Character player, Character enemy) {
        if (player instanceof Knight) {
            if (((Knight) player).getArmor() > 0) {
                double armor = ((Knight) player).getArmor();
                double damage = getSkillDamage();

                armor = armor - damage;

                if (armor < 0) {
                    ((Knight)player).setArmor(0);
                    player.setCurrentHP(player.getCurrentHP() - Math.abs(armor));
                } else {
                    ((Knight) player).setArmor(armor);
                }
            } else {
                player.setCurrentHP(player.getCurrentHP() - (getSkillDamage()));
            }
        } else if (player instanceof Archer) {
            if (((Archer) player).getDodge() == true) {
                ((Archer) player).setDodge();
            } else {
                player.setCurrentHP(player.getCurrentHP() - getSkillDamage());
            }
        } else {
            player.setCurrentHP(player.getCurrentHP() - getSkillDamage());
        }
        enemy.setCurrentMana(enemy.getCurrentMana() - 30);
    }
}
