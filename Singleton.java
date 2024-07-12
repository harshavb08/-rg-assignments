public class Singleton {
    private static Singleton instance;
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public void showMessage() {
        System.out.println("Hello, I am a singleton instance!");
    }
    public static void main(String[] args) {
        Singleton singletonInstance = Singleton.getInstance();
        singletonInstance.showMessage();
    }
}
