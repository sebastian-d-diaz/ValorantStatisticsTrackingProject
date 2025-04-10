package ca.ucalgary.part3groupprojectcpsc233.objects;

import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class Person {

    /**
     * username of Person
     */
    private final String username;

    /**
     * Nationality of Person
     */
    private final Nationality nationality;

    /**
     * age of Person
     */
    private final int age;

    /**
     * Constructor to set up basic attributes
     *
     * @param username username of Person
     * @param nationality Nationality of Person
     * @param age age of Person
     */
    public Person(String username, Nationality nationality, int age){
        this.username = username;
        this.nationality = nationality;
        this.age = age;
    }

    /**
     * Getter function for a Person's username
     *
     * @return Person's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter functions for a Person's Nationality
     *
     * @return Person's Nationality
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * Getter functions for a Person's age
     *
     * @return Person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Override for equals, to ensure that comparing two People compares their username, not refernece
     *
     * @param obj Object to compare
     * @return true if both objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Person other = (Person) obj;
        return this.getUsername().equals(other.getUsername());
    }

    /**
     * Basic toString for Person
     *
     * @return String representation of Person
     */
    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * Helper function for use in saving CSV files
     *
     * @return a CSV representation of a Person's information, in a CSV friendly format
     */
    public String getCSVInfo() { //uses getters from person object to convert data to strings, combines them into a csv format
        String usernameString = getUsername();
        String nationalityString = String.valueOf(getNationality());
        String age = String.valueOf(getAge());
        return usernameString + "," + nationalityString + "," + age;
    }
}
