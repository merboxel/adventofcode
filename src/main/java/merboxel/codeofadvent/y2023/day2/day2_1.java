package merboxel.codeofadvent.y2023.day2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day2_1 {

    public static void main(String[] args) throws IOException {

        Scanner sc = readFileAsScanner("input2");

        int sumValidGameNumbers = 0;

        while (sc.hasNext()) {

            String line = sc.nextLine();

            Game game = Game.builder()
                    .parseGame(line)
                    .build();

            if(game.isValidGame())
                sumValidGameNumbers += game.gameNumber();
        }
        System.out.println(sumValidGameNumbers);
    }

    private static Scanner readFileAsScanner(String fileName) throws IOException {

        URL resource = day2_1.class.getClassLoader().getResource(fileName);
        assert resource != null;
        return new Scanner(resource.openStream());
    }

    private static class Game{

        int gameNumber;
        List<Set> sets;

        private Game(List<Set> sets, int gameNumber) {
            this.gameNumber = gameNumber;
            this.sets = sets;
        }

        private static GameBuilder builder() {
            return new GameBuilder();
        }

        private static class GameBuilder{
            int gameNumber;
            List<Set> sets;

            private GameBuilder() {
                this.sets = new ArrayList<>();
            }
            public GameBuilder parseGame(String line) {

                String[] game = line.split(": ");
                gameNumber = Integer.parseInt(game[0].split(" ")[1]);

                for(String set: game[1].split("; ")) {
                    sets.add(Set.builder()
                            .parseSet(set)
                            .build());
                }
                return this;
            }

            public Game build() {
                return new Game(sets,gameNumber);
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

            public Set build() {
                return new Set(redCubes, greenCubes, blueCubes);
            }
        }

        public boolean isValidGame() {
            return (redCubes <= maxRedCubes && greenCubes <= maxGreenCubes && blueCubes <= maxBlueCubes);
        }
    }
}
