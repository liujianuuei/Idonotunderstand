package builder;

public class Main {
    public static void main(String[] args) {
        SomeObject someObject = new SomeObject.Builder(10, 20).yetAnotherProperty(100).build();
    }
}
