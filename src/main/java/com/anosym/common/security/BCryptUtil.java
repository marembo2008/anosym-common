package com.anosym.common.security;

import java.util.Scanner;

/**
 *
 * @author marembo
 */
public final class BCryptUtil {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter password to encrypt: ");
        final String password = scanner.nextLine();
        System.out.println();
        System.out.format("Encrypted Password: {%s}", BCrypt.hashpw(password, BCrypt.gensalt()));
        System.out.println();
    }
}
