public class Spider extends Character {
    private int posionEffect;
    private boolean webs;

    public Spider(double hp, double atk, double mana) {
        super(hp, atk, mana);
        this.posionEffect = 0;
        this.webs = false;
    }

    public int getPosionEffect() {
        return posionEffect;
    }

    public void setPoisonEffect(int poisonEffect) {
        this.posionEffect = poisonEffect;
        if (poisonEffect < 0) {
            this.posionEffect = 0;
        } else if (poisonEffect > 3) {
            this.posionEffect = 3;
        }
    }

    
    @Override
    public double getBasicAtkDamage() {
        return getBaseAtk();
    }

    @Override
    public double getHardAtkDamage() {
        return getBaseAtk() * 0.85;
    }

    @Override
    public double getSkillDamage() {
        return getBaseAtk() * 1.25;
    }

    public boolean getWebs() {
        return webs;
    }

    public void setWebs() {
        this.webs = !webs;
    }

    public void poison(Character player) {
        player.setCurrentHP(player.getCurrentHP() - (player.getMaxHP() * 0.1));
        setPoisonEffect(getPosionEffect() - 1);
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
                player.setCurrentHP(player.getCurrentHP() - getBasicAtkDamage());
            }
        } else if (player instanceof Archer) {
            if (((Archer) player).getDodge() == true) {
                ((Archer) player).setDodge();
            } else {
                player.setCurrentHP(player.getCurrentHP() - getBasicAtkDamage());
            }
        }else {
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
        }else if (player instanceof Archer) {
            if (((Archer) player).getDodge() == true) {
                ((Archer) player).setDodge();
            } else {
                player.setCurrentHP(player.getCurrentHP() - (getHardAtkDamage()));
            }
        } else {
            player.setCurrentHP(player.getCurrentHP() - (getHardAtkDamage()));
        }
        setPoisonEffect(3);
        enemy.setCurrentMana(enemy.getCurrentMana() - 30);
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
        }else {
            player.setCurrentHP(player.getCurrentHP() - getSkillDamage());
        }
        setWebs();
        enemy.setCurrentMana(enemy.getCurrentMana() - 50);
    }
}
