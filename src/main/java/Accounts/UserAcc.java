package Accounts;

public class UserAcc extends Account{
    
    private boolean isBorrower;
    
    public void showInfo() {
        System.out.println("=====[USER INFO]=====");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Password: " + getPassword());
        System.out.println("=====================");
    }
    
    public void setBorrower(boolean isBorrower) {
        this.isBorrower = isBorrower;
    }
    
    public boolean isBorrower() {
        return isBorrower;
    }
}
