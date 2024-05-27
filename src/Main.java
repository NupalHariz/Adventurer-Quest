import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        double playerBaseHP;
        double playerBaseATK;
        double playerBaseMana;

        double enemyBaseHP = 0;
        double enemyBaseATK = 0;
        double enemyBaseMana = 0;

        boolean validCharacter = false;
        int chooseCharacter;
        Character player = new Knight();
        Character enemy = new Slime();

        System.out.printf("\n||" + "=".repeat(55) + "%s" + "=".repeat(56) + "||", "ADVENTURER QUEST");
        System.out.printf("\n||%-127s||", "Selamat datang di kota Syrup.");
        System.out.printf("\n||%-127s||", "Aku lihat kamu ingin mendaftar sebagai seorang petualang.");
        System.out.printf("\n||%-127s||", "Sebelum jadi petualang, silahkan pilih role berikut.");
        System.out.printf("\n||" + "=".repeat(127) + "||");
        System.out.printf("\n||%-42s%-42s%-43s||", "1. Knight", "2. Archer", "3. Wizard");
        System.out.println("\n||" + "=".repeat(127) + "||");

        // Choosing Character

        do {
            System.out.print("||Role yang dipilih: ");
            chooseCharacter = input.nextInt();

            switch (chooseCharacter) {
                case 1:
                    playerBaseHP = 400;
                    playerBaseATK = 50;
                    playerBaseMana = 100;

                    player = new Knight(playerBaseHP, playerBaseATK, playerBaseMana);
                    validCharacter = false;
                    break;
                case 2:
                    playerBaseHP = 300;
                    playerBaseATK = 60;
                    playerBaseMana = 100;

                    player = new Archer(playerBaseHP, playerBaseATK, playerBaseMana);
                    validCharacter = false;
                    break;
                case 3:
                    playerBaseHP = 300;
                    playerBaseATK = 50;
                    playerBaseMana = 150;

                    player = new Mage(playerBaseHP, playerBaseATK, playerBaseMana);
                    validCharacter = false;
                    break;
                default:
                    System.out.println("||Tolong inputkan angka yang benar");
                    validCharacter = true;
                    break;
            }
        } while (validCharacter);

        System.out.printf("||" + "=".repeat(60) + "PROFILE" + "=".repeat(60) + "||");
        printProfile(chooseCharacter);
        System.out.printf("\n||" + "=".repeat(127) + "||");
        System.out.printf("\n||" + "=".repeat(127) + "||");

        // Pilih Musuh
        boolean killOrc = false;
        int killCounter = 0;
        int slimeKillCounter = 0;

        enemy = printQuest(enemy, enemyBaseHP, enemyBaseATK, enemyBaseMana, killCounter);
        int[] turn = { 1, 1 };

        while (!killOrc) {
            printStatus(player, enemy);

            // Pilih Aksi
            if ((turn[0] + turn[1]) % 2 == 0) {
                playerAction(turn, player, enemy);

            } else {
                enemyAction(turn, player, enemy);
            }

            if (enemy.getCurrentHP() <= 0) {
                if (enemy instanceof Slime) {
                    slimeKillCounter++;
                }

                System.out.printf("\n||" + "=".repeat(127) + "||");
                killCounter++;
                System.out.printf("\n||%-127s||",
                        "Selamat datang kembali petualang. Kau berhasil kembali dengan selamat");

                if (slimeKillCounter % 2 == 0 && enemy instanceof Slime) {
                    upgStat(player);
                    System.out.printf("\n||%-127s||",
                            "Terima kasih, kamu telah menyelesaikan misimu, istirahatlah dan kembali esok hari petualang");
                    System.out.printf("\n||" + "=".repeat(127) + "||");
                    enemy = printQuest(enemy, enemyBaseHP, enemyBaseATK, enemyBaseMana, killCounter);
                } else if (enemy instanceof Spider) {
                    upgStat(player);
                    System.out.printf("\n||%-127s||",
                            "Terima kasih, kamu telah menyelesaikan misimu, istirahatlah dan kembali esok hari petualang");
                    System.out.printf("\n||" + "=".repeat(127) + "||");
                    enemy = printQuest(enemy, enemyBaseHP, enemyBaseATK, enemyBaseMana, killCounter);
                } else if (enemy instanceof Orc) {
                    killOrc = true;
                    System.out.printf("\n||%-127s||",
                            "Pertarungan yang sangat berat telah kamu lalui. Kamu telah membunuh monster terkuat yang ada.");
                    System.out.printf("\n||%-127s||",
                            "Aku ucapkan selamat kepadamu petualang. Kamu telah menjadi pahlawan yang terkuat disini.");
                    System.out.printf("\n||%-127s||",
                            "Sudah tidak ada lagi yang bisa kamu lakukan di tempat ini. Kamu perlu berpetualang lebih jauh lagi agar bisa menjadi lebih kuat");
                    System.out.printf("\n||%-127s||",
                            "Saat kamu sudah menjadi petualang terkuat, jangan lupa kembali ke kota pertamamu ini");
                    System.out.printf("\n||%-127s||", "Akan kutunggu kisah-kisah mu selama berpetualang nanti");
                } else {
                    System.out.printf("\n||%-127s||",
                            "Terima kasih, kamu telah menyelesaikan misimu, istirahatlah dan kembali esok hari petualang");
                    System.out.printf("\n||" + "=".repeat(127) + "||");
                    enemy = printQuest(enemy, enemyBaseHP, enemyBaseATK, enemyBaseMana, killCounter);
                }

                player.setCurrentHP(player.getMaxHP());
                player.setCurrentHP(player.getMaxHP());
                player.setCurrentMana(player.getMaxMana());
                if (player instanceof Knight) {
                    if (((Knight) player).getArmor() < player.getMaxHP() * 0.3) {
                        ((Knight) player).setArmor(player.getMaxHP() * 0.3);
                    }
                }
            }

            if (player.getCurrentHP() <= 0) {
                killOrc = true;
                System.out.printf("\n||%-127s||", "Kamu telah gugur...");
                System.out.printf("\n||%-127s||", "Petualanganmu akan selalu dikenang petualang.");
                System.out.printf("\n||%-127s||",
                        "Jangan patah semangat, petualang yang hebat adalah petualang yang gugur saat pertempuran");
            }
        }
    }

    public static void printProfile(int chooseCharacter) {
        switch (chooseCharacter) {
            case 1:
                System.out.printf("\n||Selamat datang petualang, aku lihat role yang kamu pilih adalah %s%59s",
                        "Knight",
                        "||");
                System.out.printf("\n||Knight adalah seseorang yang gagah dan berani.%83s", "||");
                System.out.printf("\n||Knight memiliki pedang dan memakai zirah besi!!!%81s", "||");
                System.out.printf(
                        "\n||Dengan zirah besi tersebut, knight dapat untuk menahan serangan musuh.%59s",
                        "||");
                System.out.printf("\n||Namun ingatlah, zirah besi bukanlah zirah yang tak bisa dihancurkan.%61s", "||");
                break;
            case 2:
                System.out.printf("\n||Selamat datang petualang, aku lihat role yang kamu pilih adalah %s%59s",
                        "Archer",
                        "||");
                System.out.printf("\n||Archer adalah seseorang yang sangat lincah.%86s", "||");
                System.out.printf("\n||Dengan kecepatan seorang Archer, kamu bisa menghindari serangan musuh.%59s",
                        "||");
                System.out.printf("\n||Tidak hanya itu, Archer juga bisa menyerang musuh berkali-kali%67s", "||");
                System.out.printf("\n||Archer menyerang musuh dari jarak jauh menggunakan panah dan busur%63s", "||");
                System.out.printf(
                        "\n||Meskipun dapat bergerak cepat dan menyerang dari jauh, bukan berarti archer tidak dapat disentuh.%32s",
                        "||");
                break;
            case 3:
                System.out.printf("\n||Selamat datang petualang, aku lihat role yang kamu pilih adalah %s%61s", "Mage",
                        "||");
                System.out.printf(
                        "\n||Mage selalu membawa sebuah tongkat yang biasa digunakan untuk mengeluarkan sihirnya.%45s",
                        "||");
                System.out.printf("\n||Kamu memiliki sebuah sihir yang sangat mematikan. sihir EXPLOSION!!!%61s", "||");
                System.out.printf(
                        "\n||Mage adalah job yang membutuhkan banyak mana.Jangan sampai kamu kehabisan mana.%50s",
                        "||");
                System.out.printf("\n||Namun, kamu memiliki mana yang jauh lebih besar daripada orang lain.%61s", "||");
                break;

        }
    }

    public static void printStatus(Character player, Character enemy) {
        System.out.printf("\n||" + "=".repeat(127) + "||");
        System.out.printf("\n||==========Player Status" + "=".repeat(82) + "Enemy Status==========||");
        System.out.printf(
                "\n||HP: %-3.0f/%-3.0f         Mana: %3.0f/%-3.0f||" + " ".repeat(58)
                        + "||HP: %3.0f/%-3.0f        Mana: %3.0f/%-3.0f" + "||",
                player.getCurrentHP(), player.getMaxHP(), player.getCurrentMana(), player.getMaxMana(),
                enemy.getCurrentHP(), enemy.getMaxHP(), enemy.getCurrentMana(), enemy.getMaxMana());
        System.out.printf("\n||%35s" + " ".repeat(58) + "||%34s", "||", "||");
        if (player instanceof Knight) {
            System.out.printf(
                    "\n||Armor: %3.0f/%-3.0f           Atk: %-3.0f||" + " ".repeat(58) + "||" + " ".repeat(20)
                            + "Atk: %-3.0f    ||",
                    ((Knight) player).getArmor(), (player.getMaxHP() * 0.5), player.getBaseAtk(), enemy.getBaseAtk());
        } else {
            System.out.printf("\n||                         Atk: %-3.0f||" + " ".repeat(58) + "||" + " ".repeat(24)
                    + "Atk: %-3.0f||", player.getBaseAtk(), enemy.getBaseAtk());
        }
        System.out.printf("\n||%35s" + " ".repeat(58) + "||%34s", "||", "||");
        System.out.print("\n||" + "=".repeat(127) + "||");
        System.out.printf("\n||%-32s%-32s%-32s%-31s||", "1. Basic Attack", "2. Hard Attack", "3. Skill", "4. Heal");
        System.out.printf("\n||" + "=".repeat(127) + "||");
    }

    public static Character printQuest(Character enemy, double enemyBaseHP, double enemyBaseATK, double enemyBaseMana,
            int killCounter) {
        // Scanner input = new Scanner(System.in);

        boolean validEnemy = true;
        int quest;

        System.out.printf("\n||Hari ini kami memiliki beberapa misi yang dapat kamu lakukan.%68s\n", "||");
        System.out.println("||1. Membunuh Slime" + " ".repeat(110) + "||");
        System.out.println("||2. Membunuh Spider" + " ".repeat(109) + "||");
        System.out.println("||3. Membunuh Orc" + " ".repeat(112) + "||");
        System.out.println("||" + "=".repeat(127) + "||");

        while (validEnemy) {
            System.out.print("||Misi yang dipilih: ");
            quest = input.nextInt();
            switch (quest) {
                case 1:
                    enemyBaseHP = 250 + (2.5 * killCounter);
                    enemyBaseATK = 50 + (0.625 * killCounter);
                    enemyBaseMana = 100 + (5 * killCounter);

                    enemy = new Slime(enemyBaseHP, enemyBaseATK, enemyBaseMana);
                    validEnemy = false;
                    System.out.printf("||%-127s||", "Ada beberapa slime yang berkeliaran di hutan dekat kota");
                    System.out.printf("\n||%-127s||", "Basmi slime yang kamu temui lalu kembalilah dengan selamat");
                    break;
                case 2:
                    enemyBaseHP = 300 + (5 * killCounter);
                    enemyBaseATK = 55 + (0.75 * killCounter);
                    enemyBaseMana = 100 + (5 * killCounter);

                    enemy = new Spider(enemyBaseHP, enemyBaseATK, enemyBaseMana);
                    validEnemy = false;
                    System.out.printf("||%-127s||", "Spider tiba-tiba keluar dari habitatnya!!!");
                    System.out.printf("\n||%-127s||", "Hati-hati dengan racun dan jaring-jaring spider");
                    System.out.printf("\n||%-127s||", "Semoga kamu dapat kembali dengan selamat");
                    break;
                case 3:
                    enemyBaseHP = 500;
                    enemyBaseATK = 80;
                    enemyBaseMana = 130;

                    enemy = new Orc(enemyBaseHP, enemyBaseATK, enemyBaseMana);
                    validEnemy = false;
                    System.out.printf("||%-127s||", "Orc...");
                    System.out.printf("\n||%-127s||", "Semoga kamu beruntung petualang. Doa ku menyertaimu");
                    break;
                default:
                    System.out.printf("||%-127s||\n", "Tolong inputkan angka yang benar");
                    break;
            }
        }

        return enemy;
    }

    public static void playerAction(int[] turn, Character player, Character enemy) {
        // Scanner input = new Scanner(System.in);
        boolean validAction = true;
        int action = 0;
        double hardAtkDamage = player.getHardAtkDamage();
        double skillDamage = player.getSkillDamage();

        while (validAction) {
            System.out.print("\n||Pilih Aksi: ");
            action = input.nextInt();

            if (action < 1 || action > 4) {
                System.out.printf("||%-127s||", "Tolong inputkan angka yang benar");
            } else if (action == 2 && player.getCurrentMana() < 10) {
                System.out.printf("||%-127s||", "Mana tidak cukup");
            } else if (action == 3 && player.getCurrentMana() < 50) {
                System.out.printf("||%-127s||", "Mana tidak cukup");
            } else if (action == 4 && player.getCurrentMana() < 5) {
                System.out.printf("||%-127s||", "Mana tidak cukup");
            } else {
                validAction = false;
            }
        }

        switch (action) {
            case 1:
                player.basicAtk(player, enemy);
                System.out.printf(
                        "||Kamu menyerang menggunakan basic attack, kamu memberikan kerusakan sebesar %6.2f %-45s||",
                        player.getBaseAtk(), "kepada musuh");
                break;
            case 2:
                player.hardAtk(player, enemy);
                System.out.printf(
                        "||Kamu menyerang menggunakan hard attack, kamu memberikan kerusakan sebesar %6.2f %-46s||",
                        hardAtkDamage, "kepada musuh");
                System.out.printf("\n||%-127s||", "Kamu mengonsumsi 10 mana");
                break;
            case 3:
                player.skill(player, enemy);
                System.out.printf(
                        "||Kamu menyerang menggunakan skill, kamu memberikan kerusakan sebesar %6.2f %-52s||",
                        skillDamage, "kepada musuh");
                if (player instanceof Archer) {
                    System.out.printf("\n||%-127s||",
                            "Dengan kemampuan spesial archer, kamu akan menghindari serangan musuh pada giliran berikut");
                } else if (player instanceof Knight) {
                    System.out.printf("\n||%-127s||",
                            "Dengan kemampuan spesial knight, kamu membuat armor yang dapat menahan serangan musuh");
                } else {
                    System.out.printf("\n||%-127s||",
                            "Dengan kemampuan spesial mage, kamu menggunakan sihir EXPLOSION yang sangat destruktif");
                }
                System.out.printf("\n||%-127s||", "Kamu mengonsumsi 50 mana");
                break;
            case 4:
                player.heal();
                System.out.printf("||Kamu memulihkan dirimu sendiri, kamu memulihkan %6.2f %-72s||",
                        player.getMaxHP() * 0.3, "HP");
                System.out.printf("\n||%-127s||", "Kamu mengonsumsi 5 mana");
                break;
        }

        if (player instanceof Archer) {
            if (turn[0] % 3 == 0) {
                turn[0]++;
                System.out.printf("\n||%-127s||",
                        "Kamu bergerak sangat cepat hingga musuh tidak mengetahui keberadaanmu");
            }
        }

        if (enemy instanceof Spider) {
            if (((Spider) enemy).getPosionEffect() > 0) {
                ((Spider) enemy).poison(player);
                System.out.printf("\n||%-127s||", "Kamu kehilangan 10% HP akibat efek racun");
                if (((Spider) enemy).getPosionEffect() == 0) {
                    System.out.printf("\n||%-127s||", "Efek racun telah menghilang");
                }
            }
        }

        if (player instanceof Mage) {
            player.setCurrentMana(player.getCurrentMana() + (player.getMaxMana() * 0.15));
        } else {
            player.setCurrentMana(player.getCurrentMana() + (player.getMaxMana() * 0.1));
        }
        turn[0]++;
        // input.close();
    }

    public static void enemyAction(int[] turn, Character player, Character enemy) {
        boolean validAction = true;
        int action = 0;
        ;

        int hardAttMana;
        int skillMana;

        if (player instanceof Slime) {
            hardAttMana = 10;
            skillMana = 30;
        } else if (player instanceof Spider) {
            hardAttMana = 30;
            skillMana = 50;
        } else {
            hardAttMana = 40;
            skillMana = 70;
        }

        while (validAction) {
            action = (int) (Math.random() * 4) + 1;

            if (action == 2 && enemy.getCurrentMana() < hardAttMana) {
                validAction = true;
            } else if (action == 3 && enemy.getCurrentMana() < skillMana) {
                validAction = true;
            } else if (action == 4 && enemy.getCurrentMana() < 5) {
                validAction = true;
            } else {
                validAction = false;
            }
        }

        switch (action) {
            case 1:
                enemy.basicAtk(player, enemy);
                System.out.printf(
                        "\n||Monster menyerang menggunakan basic attack, musuh memberikan kerusakan sebesar %6.2f %-41s||",
                        enemy.getBasicAtkDamage(), "kepada mu");
                break;
            case 2:
                enemy.hardAtk(player, enemy);
                System.out.printf(
                        "\n||Monster menyerang menggunakan hard attack, musuh memberikan kerusakan sebesar %6.2f %-42s||",
                        enemy.getHardAtkDamage(), "kepada mu");
                if (enemy instanceof Spider) {
                    System.out.printf("\n||%-127s||",
                            "Spider menggunakan racunnya untuk menyerangmu, kamu terkena efek racun selama beberapa giliran");
                }
                break;
            case 3:
                enemy.skill(player, enemy);
                System.out.printf(
                        "\n||Monster menyerang menggunakan skill, musuh memberikan kerusakan sebesar %6.2f %-48s||",
                        enemy.getSkillDamage(), "kepada mu");
                if (enemy instanceof Spider) {
                    System.out.printf("\n||%-127s||",
                            "Spider menggunakan jaringnya untuk menyerangmu. Kamu tidak dapat bergerak selama 1 giliran");
                    turn[1]++;
                } else if (enemy instanceof Orc) {
                    System.out.printf("\n||%-127s||",
                            "Orc berteriak sangat kencang. Badan dan ototnya bertambah besar");
                    System.out.printf("\n||%-127s||",
                            "Serangan orc selama beberapa turn berikutnya akan menjadi jauh lebih kuat. BERTAHAN!!!");
                }
                break;
            case 4:
                enemy.heal();
                System.out.printf("\n||Monster memulihkan dirinya sendiri, musuh memulihkan %6.2f %-68s||",
                        enemy.getMaxHP() * 0.3, "HP");
                break;
        }

        if (enemy instanceof Orc) {
            enemy.setCurrentMana(enemy.getCurrentMana() + (enemy.getMaxMana() * 0.2));
        } else {
            enemy.setCurrentMana(enemy.getCurrentMana() + (enemy.getMaxMana() * 0.1));
        }
        turn[1]++;
    }

    public static void upgStat(Character player) {
        System.out.printf("\n||%-127s||", "Kamu telah cukup kuat untuk mengembangkan stats mu.");
        System.out.printf("\n||" + "=".repeat(127) + "||");
        System.out.printf("\n||%-42s%-42s%-43s||", "1. HP", "2. Atk", "3. Mana");
        System.out.printf("\n||" + "=".repeat(127) + "||");
        int upgStats;

        do {
            System.out.print("\n||Pilih stats yang ingin dikembangkan: ");
            upgStats = input.nextInt();

            switch (upgStats) {
                case 1:
                    player.setMaxHP(player.getMaxHP() * 1.1);
                    System.out.printf("||%-127s||", "HP mu telah bertambah sebesar 10%");
                    break;
                case 2:
                    player.setBaseAtk(player.getBaseAtk() * 1.1);
                    System.out.printf("||%-127s||", "Atk mu telah bertambah sebesar 10%");
                    break;
                case 3:
                    player.setMaxMana(player.getMaxMana() * 1.1);
                    System.out.printf("||%-127s||", "Mana mu telah bertambah sebesar 10%");
                    break;
                default:
                    System.out.printf("||%-127s||", "Tolong input angka yang sesuai");
                    break;
            }
        } while (upgStats < 1 || upgStats > 3);
    }
}
