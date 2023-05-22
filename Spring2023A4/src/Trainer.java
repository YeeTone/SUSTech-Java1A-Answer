package Spring2023A4;

import java.util.ArrayList;
import java.util.Arrays;

public class Trainer {
    private String name;
    private ArrayList<Pokemon> pokemons;
    private Pokemon activatePokemon;

    public Trainer(String name, Pokemon... pokemons) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.addPokemon(pokemons);
        this.activatePokemon = this.summon();
    }

    public void addPokemon(Pokemon... poke) {
        if(poke == null){
            return;
        }
        for (int i = 0; pokemons.size() < 6 && i < poke.length; i++) {
            boolean sameNameConflict = false;
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getName().equals(poke[i].getName())) {
                    sameNameConflict = true;
                    break;
                }
            }

            if (!sameNameConflict) {
                pokemons.add(poke[i]);
            }
        }
    }

    public void removePokemon(String... name) {
        if(name == null){
            return;
        }

        for (String n : name) {
            for (int i = 0; i < pokemons.size(); i++) {
                if (pokemons.get(i).getName().equals(n)) {
                    pokemons.remove(i);
                    break;
                }
            }
        }
    }

    public boolean isAlive(String name) {
        if (name == null) {
            return false;
        }

        for (Pokemon p : pokemons) {
            if (p.getName().equals(name)) {
                return p.isAlive();
            }
        }

        return false;
    }

    private boolean isAlive(Pokemon poke) {
        if (poke == null) {
            return false;
        }

        for (Pokemon p : pokemons) {
            if (p.equals(poke)) {
                return p.isAlive();
            }
        }

        return false;
    }

    public Pokemon getPokemon(String name){
        if(name == null){
            return null;
        }

        for (Pokemon p: pokemons){
            if(p.getName().equals(name)){
                return p;
            }
        }

        return null;
    }

    public Pokemon summon(){
        for (Pokemon p: pokemons){
            if(p.isAlive()){
                this.activatePokemon = p;
                return p;
            }
        }
        return null;
    }

    public Pokemon summon(String name){
        if(name == null){
            this.activatePokemon = null;
            return null;
        }

        for (Pokemon p: pokemons){
            if(p.isAlive() && p.getName().equals(name)){
                this.activatePokemon = p;
                return p;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Pokemon getActivatePokemon() {
        return activatePokemon;
    }

    public void setActivatePokemon(Pokemon activatePokemon) {
        this.activatePokemon = activatePokemon;
    }

    public boolean anyLive(){
        for(Pokemon p: pokemons){
            if(p.isAlive()){
                return true;
            }
        }

        return false;
    }
}
