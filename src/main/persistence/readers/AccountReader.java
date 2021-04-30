package persistence.readers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class AccountReader {

    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static AccountList readAccounts(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    public static Account readMostRecentAccount(File file) throws IOException {
        List<String> str = Files.readAllLines(file.toPath());
        return new Account(str.get(0), str.get(1));
    }

    // EFFECTS: returns content of file as a list of strings
    //          each string = one line of text
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of foods parsed from list of strings
    // where each string contains data for one food
    private static AccountList parseContent(List<String> fileContent) {
        AccountList accountList = new AccountList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            AccountList.add(parseUsername(lineComponents), parseAccounts(lineComponents));
        }

        return accountList;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 7
    // EFFECTS: returns a username
    private static String parseUsername(List<String> components) {
        return (components.get(0));

    }

    private static Account parseAccounts(List<String> components) {
        String username = (components.get(0));
        String password = (components.get(1));
        return new Account(username, password);
    }
}