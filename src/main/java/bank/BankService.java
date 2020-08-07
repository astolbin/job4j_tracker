package bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);

        if (user != null) {
            List<Account> accounts = users.get(user);
            rsl = accounts.stream()
                    .filter(acc -> Objects.equals(acc, new Account(requisite, -1)))
                    .findFirst()
                    .orElse(null);
        }

        return rsl;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double transferAmount) {
        boolean rsl = false;

        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);

        if (srcAccount != null && destAccount != null) {
            double srcAmount = srcAccount.getBalance();
            double destAmount = destAccount.getBalance();

            if (srcAmount >= transferAmount) {
                srcAccount.setBalance(srcAmount - transferAmount);
                destAccount.setBalance(destAmount + transferAmount);
                rsl = true;
            }
        }

        return rsl;
    }
}
