package Fall2021A4;

import java.util.ArrayList;

public class GameSystem {
    private ArrayList<Player> playerList;
    private ArrayList<Game> gameList;

    public GameSystem() {
        this.playerList = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean checkPlayer(int pid) {
        for (Player p : this.playerList) {
            if (p.getPid() == pid) {
                return true;
            }
        }

        return false;
    }

    public boolean checkGame(int gid) {
        for (Game g : this.gameList) {
            if (g.getGid() == gid) {
                return true;
            }
        }
        return false;
    }

    public boolean addPlayer(Player player) {
        if (checkPlayer(player.getPid())) {
            return false;
        }

        this.playerList.add(player);
        return true;
    }

    public boolean addGame(Game game) {
        if (checkGame(game.getGid())) {
            return false;
        } else if (!(checkPlayer(game.getBlackPlayer().getPid()) && checkPlayer(game.getWhitePlayer().getPid()))) {
            return false;
        } else {
            this.gameList.add(game);
            return true;
        }


    }

    public ArrayList<Game> listPlayerGame(int pid) {
        ArrayList<Game> list = new ArrayList<>();
        for (Game g : this.gameList) {
            if (g.isParticipate(pid)) {
                list.add(g);
            }
        }

        return list;
    }

    public float calculatePlayerWinRate(int pid) {
        if (!checkPlayer(pid)) {
            return 0.0f;
        }

        float total = 0;
        float win = 0;

        for (Game g : gameList) {
            if (g.isParticipate(pid)) {
                total++;
            }

            if (g.getWinner().getPid() == pid) {
                win++;
            }
        }

        return total == 0 ? 0 : win / total;
    }
}
