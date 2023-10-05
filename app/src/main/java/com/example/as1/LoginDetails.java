package com.example.as1;

public class LoginDetails {
    private static final String[] usernames = {"khalid", "joyal", "nana", "daniel", "student5", "student6", "student7", "student8", "student9", "student10"};
    private static final String[] passwords = {"CSY1020", "CSY1018", "CSY1026", "CSY1027", "password5", "password6", "password7", "password8", "password9", "password10"};
    public static String loggedInUser = null;

    public static boolean isValidLogin(String username, String password) {
        for (int i = 0; i < usernames.length; i++) {
            if (username.equals(usernames[i]) && password.equals(passwords[i])) {
                loggedInUser = username;
                return true;
            }
        }
        return false;
    }
}
