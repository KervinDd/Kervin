public class ElementFactory extends Factory {

    private static ElementFactory factory  = new ElementFactory();

    public static ElementFactory getInstance() {
        return factory;
    }

    @Override
    public  LevelComponent getElement(char c) {
         switch (c) {
             case '#':
                 return new Wall();
             case '-':
                 return new Floor();
             case 'K':
                 return new Key();
             case 'D':
                 return new Door();
             default:
                 throw new IllegalArgumentException();


         }

    }
}
