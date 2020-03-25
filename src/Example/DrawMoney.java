package Example;

public class DrawMoney extends Thread {
    //模拟账户
    private Account account;
    //取钱数
    private double drawAccount;
    public DrawMoney(String name,Account account,double drawAccount){
        super(name);
        this.account=account;
        this.drawAccount=drawAccount;
    }

    @Override
    public void run() {
        synchronized(account){
            //账户余额大于取钱数
            if(account.getBalance()>=drawAccount){
                //取出钞票
                System.out.println("取出钞票："+drawAccount);
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
                //修改余额
                account.setBalance(account.getBalance()-drawAccount);
                System.out.println("余额为："+account.getBalance());
            }
            else{
                System.out.println("余额不足");
            }
        }
    }
}
