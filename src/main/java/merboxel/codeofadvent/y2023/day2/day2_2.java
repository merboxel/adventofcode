package merboxel.codeofadvent.y2023.day2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day2_2 {

    public static void main(String[] args) throws IOException {

        Scanner sc = readFileAsScanner("input2");

        int powerCubesGames = 0;

        while (sc.hasNext()) {

            String line = sc.nextLine();

            Game game = Game.builder()
                    .parseGame(line)
                    .build();

            powerCubesGames += game.powerMinCubes();
        }
        System.out.println(powerCubesGames);
    }

    private static Scanner readFileAsScanner(String fileName) throws IOException {

        URL resource = day2_2.class.getClassLoader().getResource(fileName);
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    private static class Game{

        final int gameNumber;
        final List<Set> sets;
        final int minRedCubes;
        final int minGreenCubes;
        final int minBlueCubes;

        private Game(List<Set> sets, int gameNumber, int minRedCubes, int minGreenCubes, int minBlueCubes) {
            this.gameNumber = gameNumber;
            this.sets = sets;
            this.minRedCubes = minRedCubes;
            this.minGreenCubes = minGreenCubes;
            this.minBlueCubes = minBlueCubes;
        }

        private static GameBuilder builder() {
            return new GameBuilder();
        }

        private static class GameBuilder{
            int gameNumber;
            List<Set> sets;
            int minRedCubes;
            int minGreenCubes;
            int minBlueCubes;

            private GameBuilder() {
                this.sets = new ArrayList<>();
            }
            public GameBuilder parseGame(String line) {

                String[] game = line.split(": ");
                gameNumber = Integer.parseInt(game[0].split(" ")[1]);

                for(String setData: game[1].split("; ")) {

                    Set set = Set.builder()
                            .parseSet(setData)
                            .build();

                    minRedCubes = Math.max(minRedCubes,set.getRedCubes());
                    minGreenCubes = Math.max(minGreenCubes,set.getGreenCubes());
                    minBlueCubes = Math.max(minBlueCubes,set.getBlueCubes());

                    sets.add(set);
                }
                return this;
            }

            public day2_2.Game build() {
                return new day2_2.Game(sets,gameNumber,minRedCubes,minGreenCubes,minBlueCubes);
            }
        }

        public boolean isValidGame() {

            for(Set set: sets)
            {
                if(!set.isValidGame())
                    return false;
            }
            return true;
        }

        public int gameNumber() {
            return gameNumber;
        }
        public int powerMinCubes() {
            return minRedCubes*minGreenCubes*minBlueCubes;
        }
    }

    private static class Set {
        private static final int maxRedCubes = 12;
        private static final String red = "red";
        private static final int maxGreenCubes = 13;
        private static final String green = "green";
        private static final int maxBlueCubes = 14;
        private static final String blue = "blue";

        private final int redCubes;
        private final int greenCubes;
        private final int blueCubes;

        private Set(int redCubes, int greenCubes, int blueCubes) {
            this.redCubes = redCubes;
            this.greenCubes = greenCubes;
            this.blueCubes = blueCubes;
        }

        private static SetBuilder builder() {
            return new SetBuilder();
        }

        private static class SetBuilder {

            int redCubes = 0;
            int greenCubes = 0;
            int blueCubes = 0;

            private SetBuilder() {}

            public SetBuilder parseSet(String line) {

                String[] setParts = line.split(", ");

                for(String setPart: setParts)
                {
                    String[] part = setPart.split(" ");
                    int cubes = Integer.parseInt(part[0]);
                    String color = part[1];

                    switch(color) {
                        case red -> {
                            redCubes += cubes;
                        }
                        case blue -> {
                            blueCubes += cubes;
                        }
                        case green -> {
                            greenCubes += cubes;
                        }
                    }
                }
                return this;
            }

            public day2_2.Set build() {
                return new day2_2.Set(redCubes, greenCubes, blueCubes);
            }
        }

        public boolean isValidGame() {
            return (redCubes <= maxRedCubes && greenCubes <= maxGreenCubes && blueCubes <= maxBlueCubes);
        }

        public int getRedCubes() {
            return redCubes;
        }
        public int getGreenCubes() {
            return greenCubes;
        }
        public int getBlueCubes() {
            return blueCubes;
        }
    }
}
