package su1cat.jsonexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import su1cat.jsonexample.classes.Person;

import java.io.*;

public class Program {
    public static void main(String[] args) {
        Person person = new Person("Ivan", "Ivanov", 25);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithModifiers().setPrettyPrinting().create();

        localPersonGson(person, gson);
        System.out.println("---");
        filePersonGson(person, gson);
    }

    private static void localPersonGson(Person person, Gson gson) {
        String personJson = gson.toJson(person);

        System.out.println("Serialised person JSON:");
        System.out.println(personJson);

        Person fromJson = gson.fromJson(personJson, Person.class);

        System.out.println("Deserialised person object:");
        System.out.println(fromJson.toString());

        System.out.println("Verifying correctness...");
        if (person.equals(fromJson)) {
            System.out.println("Data is correct after deserialisation!");
        } else {
            System.out.println("Data is incorrect!");
            System.out.println("Original data: ");
            System.out.println(person);
            System.out.println("New data: ");
            System.out.println(fromJson);
        }
    }

    private static void filePersonGson(Person person, Gson gson) {
        try (Writer writer = new FileWriter("person.json")) {
            gson.toJson(person, writer);
            System.out.println("Person serialised to file!");
        } catch (IOException ex) {
            System.out.println("Could not serialise person to file because of exception: ");
            ex.printStackTrace();
            return;
        }

        Person fromFile;

        try (Reader reader = new FileReader("person.json")) {
            fromFile = gson.fromJson(reader, Person.class);
        } catch (IOException ex) {
            System.out.println("Could not deserialise person to file because of exception: ");
            ex.printStackTrace();
            return;
        }

        System.out.println("Person data from file: ");
        System.out.println(fromFile.toString());

        System.out.println("Verifying correctness...");
        if (person.equals(fromFile)) {
            System.out.println("Data is correct after deserialisation!");
        } else {
            System.out.println("Data is incorrect!");
            System.out.println("Original data: ");
            System.out.println(person);
            System.out.println("New data: ");
            System.out.println(fromFile);
        }
    }
}
