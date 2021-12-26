public class Human implements HasName {
    String nom;
    int age;
    int id;

    public Human(String nom, int age, int id) {
        this.nom = nom;
        this.age = age;
        this.id = id;
    }

    @Override
    public String getNom() {
        return nom;
    }

}
