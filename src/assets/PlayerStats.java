package assets;

import model.GameResult;
import model.Player;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlayerStats {

    public static void printStats(Player player) {
        List<GameResult> history = player.getHistory();


        if(history.isEmpty()) {
            System.out.println("Nic nebylo odehrano");
        } else {
            System.out.println("=== Player Statistics ===");
            System.out.println("Hráč: " + player.getNickName());
            System.out.println("Současný stav: " + player.getBalance());
            System.out.println("Total Games Played: " + history.size());
            System.out.println("Počet vyhraných her: " + countWins(history));
            System.out.println("Prohry: " + countLosses(history));
            System.out.println("Zisk/ztráta: " + totalWinnings(history));
            System.out.println("Win rate: " + getWinRate(history));
        }
    }

    public static long countWins(List<GameResult> history) {
        return history.stream().filter(GameResult::isWin).count();
    }

    public static long countLosses(List<GameResult> history) {
        return history.size() - history.stream().filter(GameResult::isWin).count();
    }

    public static int totalWinnings(List<GameResult> history) {
        return history.stream().mapToInt(GameResult::getWinnings).sum();
    }

    public static String getWinRate(List<GameResult> history) {
        return String.format("%.2f",((double)(history.stream().filter(GameResult::isWin).count()) / (double)(history.size())) * 100) + "%";
    }
}
