package Spring2022A3;

import java.util.ArrayList;
import java.util.Objects;

class Mail {
    String mail;

    public Mail(String mailNumber) {
        this.mail = mailNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(this.mail, mail.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}

class PhoneNumber {
    String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}

public class Player {
    private final String account = generateAccount();
    private String password;
    private Mail mail;
    private PhoneNumber phoneNumber;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public Player(Mail mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Player(PhoneNumber phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Player(Mail mail, PhoneNumber phoneNumber, String password) {
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public boolean checkIdentity(Mail mail, String password) {
        return Objects.equals(this.mail, mail) && Objects.equals(this.password, password);
    }

    public boolean checkIdentity(PhoneNumber phoneNumber, String password) {
        return Objects.equals(this.phoneNumber, phoneNumber) && Objects.equals(this.password, password);
    }

    public boolean setMail(PhoneNumber phoneNumber, String password, Mail mail) {
        if (checkIdentity(phoneNumber, password)) {
            this.mail = mail;
            return true;
        }

        return false;
    }

    public boolean setPhoneNumber(Mail mail, String password, PhoneNumber phoneNumber) {
        if (checkIdentity(mail, password)) {
            this.phoneNumber = phoneNumber;
            return true;
        }

        return false;
    }

    public String generateAccount() {
        return "1919810";
    }

    public String getAccount() {
        return account;
    }

    public Mail getMail() {
        return mail;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean changePassword(PhoneNumber phoneNumber, String oldPassword, String newPassword) {
        if (checkIdentity(phoneNumber, oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public boolean changePassword(Mail mail,String oldPassword, String newPassword){
        if (checkIdentity(mail, oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }
}
