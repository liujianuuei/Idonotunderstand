package builder;

public class SomeObject {
    private final int someProperty;
    private final int anotherProperty;
    private final int yetAnotherProperty;

    public static class Builder implements ObjectBuilder<SomeObject> {
        // Required parameters
        private final int someProperty;
        private final int anotherProperty;
        // Optional parameters - initialized to default values
        private int yetAnotherProperty = 0;

        public Builder(int someProperty, int anotherProperty) {
            this.someProperty = someProperty;
            this.anotherProperty = anotherProperty;
        }

        public Builder yetAnotherProperty(int p) {
            this.yetAnotherProperty = p;
            return this;
        }

        @Override
        public SomeObject build() {
            return new SomeObject(this);
        }
    }

    private SomeObject(Builder builder) {
        someProperty = builder.someProperty;
        anotherProperty = builder.anotherProperty;
        yetAnotherProperty = builder.yetAnotherProperty;
    }
}