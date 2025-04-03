package modification;

import java.util.Arrays;

public class ModifierOptions {
    private int[] numbers;

    // *****************************************************
    // Getters

    public int[] getNumbers() {
        return numbers;
    }

    // *****************************************************
    // Builder patter implementation

    private ModifierOptions(Builder builder) {
        this.numbers = builder.numbers;
        
        Arrays.sort(numbers);
    }

    public static OptionsBuilder options() {
        return new Builder();
    }

    public interface OptionsBuilder {
        OptionsBuilder numeric(int... numbers);
        ModifierOptions build();
    }

    private static class Builder implements OptionsBuilder {
        private int[] numbers;

        @Override
        public OptionsBuilder numeric(int... numbers) {
            this.numbers = numbers;
            return this;
        }

        @Override
        public ModifierOptions build() {
            return new ModifierOptions(this);
        }
    }
}
