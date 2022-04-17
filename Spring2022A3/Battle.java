package Spring2022A3;

import java.util.ArrayList;
import java.util.List;

public class Battle {
    private static final int ROUNDLIMIT = 50;

    public static Player tatakai(Player p1, Player p2) {
        List<Pokemon> pokemonList1 = new ArrayList<>(p1.pokemons);
        List<Pokemon> pokemonList2 = new ArrayList<>(p2.pokemons);

        int currentRound = 1;
        while (currentRound <= ROUNDLIMIT) {
            if (pokemonList1.isEmpty()) {
                return p2;
            }
            if (pokemonList2.isEmpty()) {
                return p1;
            }

            Pokemon player1Pokemon = pokemonList1.get(0);
            Pokemon player2Pokemon = pokemonList2.get(0);

            System.out.printf("-- Round %d --%n", currentRound);
            System.out.printf("%s.hp = %d; %s.hp = %d;%n", player1Pokemon.getName(), player1Pokemon.getHp(),
                    player2Pokemon.getName(), player2Pokemon.getHp());

            Pokemon faster, slower;
            List<Pokemon> fasterList, slowerList;

            if (player1Pokemon.isFaster(player2Pokemon)) {
                faster = player1Pokemon;
                slower = player2Pokemon;
                fasterList = pokemonList1;
                slowerList = pokemonList2;

            } else {
                faster = player2Pokemon;
                slower = player1Pokemon;
                fasterList = pokemonList2;
                slowerList = pokemonList1;
            }

            boolean fasterKill = faster.attackAndIsKilled(slower);
            if (faster.isNormal()) {
                System.out.printf("%s attack first, its attack value is normal attack damage: %d;%n",
                        faster.getName(), faster.getAtk());
            }else {
                System.out.printf("%s's skill is ready, its attack value is skill damage: %d;%n",
                        faster.getName(), faster.skill.getSkillAtk());
            }

            System.out.printf("%s.hp = %d;%n", slower.getName(), slower.getHp());


            if (fasterKill) {
                slower.initiate();
                slowerList.remove(0);
                currentRound = 1;
            } else {
                boolean slowerKill = slower.attackAndIsKilled(faster);

                if (slower.isNormal()) {
                    System.out.printf("Then it's %s's turn to attack first, the attack value is normal attack damage: %d;%n",
                            slower.getName(), slower.getAtk());
                }else {
                    System.out.printf("Then %s's skill is ready, it's attack value is skill damage: %d;%n",
                            slower.getName(), slower.skill.getSkillAtk());
                }

                System.out.printf("%s.hp = %d;%n", faster.getName(), faster.getHp());

                if(slowerKill){
                    faster.initiate();
                    fasterList.remove(0);
                    currentRound = 1;
                }
            }


            currentRound++;
        }

        return null;
    }
}
