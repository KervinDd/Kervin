import org.junit.Test;

public class AnimalTest {

    @Test
    public void test() {

        HasName anHuman = new Human("kervin", 19, 123);
        anHuman.getNom();
    }
}
