package Example;

public class Test {
    public static void main(String args[]){
        Account a = new Account("1111",1000);
        new DrawMoney("小明",a,500).start();
        new DrawMoney("小可",a,400).start();
    }
}
