package demo.webService;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;
import static java.lang.Thread.sleep;

public class PasswordEncoderApplication {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        try {
            System.out.println("Input password to encode or -STOP to exit:");
            boolean stop = false;
            while(!stop) {
                if (in.hasNext()) {
                    String input = in.nextLine();
                    if (input.toUpperCase().equals("#STOP")) stop = true;
                    else System.out.println(encoder.encode(input));
                }
                sleep(1);
            }
        } catch (InterruptedException err) {
            System.out.println(err.getMessage());
        }
    }
}
