import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level extends AbstractLevel{

    public Level(String input) {
        this.size = input.replace("\n", "").length();

        int nbLines = input.length() - this.size + 1;
        this.components = new LevelComponent[nbLines][];

        int lineCount = 0;
        for (String line:  input.split("\n")) {
            Stream<LevelComponent> components = line.chars().mapToObj(c -> new Wall());
            LevelComponent[] row = components.toArray(LevelComponent[]::new);
            this.components[lineCount] = row;
            lineCount ++;
        }

    }

    @Override
    public String toString() {
        List<String> builder = new ArrayList<>();
        for (int i = 0; i<this.components.length; i++) {
            String row = Arrays.stream(this.components[i]).map(LevelComponent::draw).collect(Collectors.joining());
            builder.add(row);
        }
        return builder.stream().collect(Collectors.joining("\\n"));
    }

    @Override
    LevelComponent[][] getComponents() {
        return this.components;
    }

    @Override
    int getSize() {
        return this.size;
    }
}
