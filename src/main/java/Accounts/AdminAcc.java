package Accounts;

public class AdminAcc extends Account{
    
    public boolean isAdmin = true;
    
    public void showInfo() {
        System.out.println("=====[ADMIN INFO]=====");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Password: " + getPassword());
        System.out.println("=====================");
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
}
