import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        List<Entity> coll = new LinkedList<>();
        coll.add(new Entity(3, "abc"));
        //System.out.println(r);
        coll.add(new Entity(2, "def"));
        coll.add(new Entity(3, "abc"));
        coll.add(new Entity(1, "xyz"));
        //coll.add(null);

        coll.forEach((e) -> {
            System.out.println(e);
        });
    }
}

class Entity implements Comparable {

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        Entity o = (Entity) obj;
        return this.id == o.id && (this.name == o.name || this.name.equals(o.name));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id + name);
    }

    @Override
    public int compareTo(Object o) {
        return this.id - ((Entity) o).id;
    }
}
