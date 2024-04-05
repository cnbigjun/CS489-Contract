package edu.miu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.miu.model.Contact;
import edu.miu.model.EmailAddress;
import edu.miu.model.PhoneNumber;


import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactApp {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();

        // Sample data
        Contact contact1 = new Contact("David", "Sanger", "Argos LLC", "Sales Manager");
        contact1.addPhoneNumber(new PhoneNumber("240-133-0011", "Home"));
        contact1.addPhoneNumber(new PhoneNumber("240-112-0123", "Mobile"));
        contact1.addEmailAddress(new EmailAddress("dave.sang@gmail.com", "Home"));
        contact1.addEmailAddress(new EmailAddress("dsanger@argos.com", "Work"));

        Contact contact2 = new Contact("Carlos", "Jimenez", "Zappos", "Director");

        Contact contact3 = new Contact("Ali", "Gafar", "BMI Services", "HR Manager");
        contact3.addPhoneNumber(new PhoneNumber("412-116-9988", "Work"));
        contact3.addEmailAddress(new EmailAddress("ali@bmi.com", "Work"));

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        contacts.sort(Comparator.comparing(Contact::getLastName));

        // Method1 by GSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Contact contact : contacts) {
            System.out.println(gson.toJson(contact));
        }

        // Method2 by Java standard
        printContactsInJSON(contacts);

    }

    private static void printContactsInJSON(List<Contact> contacts) {
        System.out.println("Printed in JSON format");
        System.out.println("[");
        for (Contact contact : contacts) {
            String line = String.format("\t{\"firstName\": \"%s\", \"lastName\": \"%s\", \"company\": \"%s\", \"jobTitle\": \"%s\", \"phoneNumbers\": %s, \"emailAddresses\": %s},",
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getCompany(),
                    contact.getJobTitle(),
                    getPhoneNumbersJSON(contact.getPhoneNumbers()),
                    getEmailAddressesJSON(contact.getEmailAddresses()));
            System.out.println(line);
        }
        System.out.println("]");
    }

    private static String getPhoneNumbersJSON(List<PhoneNumber> phoneNumbers) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < phoneNumbers.size(); i++) {
            PhoneNumber phoneNumber = phoneNumbers.get(i);
            sb.append(String.format("{\"number\": \"%s\", \"label\": \"%s\"}%s",
                    phoneNumber.getNumber(),
                    phoneNumber.getLabel(),
                    i < phoneNumbers.size() - 1 ? "," : ""));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String getEmailAddressesJSON(List<EmailAddress> emailAddresses) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < emailAddresses.size(); i++) {
            EmailAddress emailAddress = emailAddresses.get(i);
            sb.append(String.format("{\"address\": \"%s\", \"label\": \"%s\"}%s",
                    emailAddress.getAddress(),
                    emailAddress.getLabel(),
                    i < emailAddresses.size() - 1 ? "," : ""));
        }
        sb.append("]");
        return sb.toString();
    }


}