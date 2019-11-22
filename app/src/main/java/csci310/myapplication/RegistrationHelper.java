package csci310.myapplication;

import java.util.regex.Pattern;

public class RegistrationHelper {
    private String user;
    private String name;
    private String email;
    private String password;
    private String password2;
    public int errorCode;
    /*
    -1 = no errors found
    0 = invalid username
    1 = invalid name
    2 = invalid password
    3 = invalid email address
     */

    public RegistrationHelper(String u, String n, String e, String p, String p2) {
        errorCode = -1;     // -1 will be the flag for no error
        user = u;
        name = n;
        email = e;
        password = p;
        password2 = p2;
    }
    public boolean isValid() {
        System.out.println(user);
        System.out.println("password: "+password);
        System.out.println("password2: "+password2);
        System.out.println("passwordlen: "+password.length());
        System.out.println("password2len: "+password2.length());
        System.out.println("passwordlenCheck: "+(password.length() < 8));

        String[] data = email.split("@", 2);
        System.out.println("data[1] "+data[1]);
        System.out.println("!equal usc.edu "+(!data[1].equalsIgnoreCase("usc.edu")));

        if (!data[1].equalsIgnoreCase("usc.edu"))
        {   errorCode = 3;
            System.out.println("entered email check");
            return false;
        }

        if (password.length() < 8)
        {
//            System.out.println("passwordlenCheck: "+(password.length() < 8));
            errorCode = 2;
            return false;
        }

        if (user == null || user.length() == 0) {
            errorCode = 0;
            System.out.println("failed at "+errorCode);
            return false;
        } else if (name == null || name.length() == 0) {
            errorCode = 1;
            System.out.println("failed at "+errorCode);
            return false;
        } else if (name != null) {
            char[] chars = name.toCharArray();
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    errorCode = 1;
                    System.out.println("failed at "+errorCode);
                    return false;
                }
            }
        } else if (password == null || password.length() < 8) {
            errorCode = 2;
            System.out.println("failed at "+errorCode);
            return false;
        } else if (password2 == null || password2.length() < 8) {
            errorCode = 2;
            System.out.println("failed at "+errorCode);
            return false;
        } else if (!password.contains(password2)) {
            errorCode = 4;
            System.out.println("failed at "+errorCode);
            return false;
        } else {
            errorCode = 3;
            System.out.println("failed at "+errorCode);
            if (email == null) {
                return false;
            }
            //Regex email verification
            String emailRegex = "^[a-zA-Z0-9_]+(?:\\." +
                    "[a-zA-Z0-9_]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";


        }
        return true;
    }
}
