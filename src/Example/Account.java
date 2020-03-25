package Example;

public class Account {
    /*
        账户编号
        账户余额
     */
    private String accountNo;
    private double balance;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Account(String accountNo,double balance){
        this.accountNo=accountNo;
        this.balance=balance;
    }

    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj!=null&&obj.getClass()==Account.class){
            Account a = (Account) obj;
            return a.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
