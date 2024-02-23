package test;

import com.example.bank.models.Account;
import com.example.bank.models.Bank;
import com.example.bank.models.SavingsAccount;
import com.example.bank.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void testTransferFundsSufficientBalance() {
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        Account account1 = new Account("ACC001", user1, 1000);
        Account account2 = new Account("ACC002", user2, 500);

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        bank.transferFunds(account1, account2, 500);

        assertEquals(500, account1.getBalance());
        assertEquals(1000, account2.getBalance());
    }

    @Test
    public void testTransferFundsInsufficientBalance() {
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");

        Account account1 = new Account("ACC001", user1, 1000);
        Account account2 = new Account("ACC002", user2, 500);

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        bank.transferFunds(account1, account2, 1500);

        assertEquals(1000, account1.getBalance());
        assertEquals(500, account2.getBalance());
    }

    @Test
    public void testDepositFunds() {
        User user = new User("user", "password");
        Account account = new Account("ACC001", user, 1000);

        Bank bank = new Bank();
        bank.addAccount(account);

        bank.deposit(account, 500);

        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testWithdrawFundsSufficientBalance() {
        User user = new User("user", "password");
        Account account = new Account("ACC001", user, 1000);

        Bank bank = new Bank();
        bank.addAccount(account);

        bank.withdraw(account, 500);

        assertEquals(500, account.getBalance());
    }

    @Test
    public void testWithdrawFundsInsufficientBalance() {
        User user = new User("user", "password");
        Account account = new Account("ACC001", user, 1000);

        Bank bank = new Bank();
        bank.addAccount(account);

        bank.withdraw(account, 1500);

        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testAddInterestToSavingsAccounts() {
        User user = new User("user", "password");
        SavingsAccount savingsAccount = new SavingsAccount("SAV001", user, 1000, 5); // Interest rate: 5%

        Bank bank = new Bank();
        bank.addAccount(savingsAccount);

        bank.addInterestToSavingsAccounts(5); // Add interest with the same rate

        assertEquals(1050, savingsAccount.getBalance()); // 5% of 1000 is 50
    }

    @Test
    public void testTransferFunds() {
        // Write your test for transferFunds method here
    }

    @Test
    public void testGetTransactionHistory() {
        // Write your test for getTransactionHistory method here
    }
}
