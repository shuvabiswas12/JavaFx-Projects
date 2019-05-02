package sample.model;

import com.google.gson.Gson;

import java.io.*;

public class Settings {

    public static final String CONFIG_FILE = "config.txt";

    private int no_of_days;
    private String uPassword;
    private float uFine;
    private String uName;
    private String email;
    private String location;

    public Settings() {
        uFine = 2.0f;
        uPassword = "admin";
        uName = "admin";
        no_of_days = 14;
        email = "admin@gmail.com";
        location = "Milky_Way";

    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public float getuFine() {
        return uFine;
    }

    public void setuFine(float uFine) {
        this.uFine = uFine;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public static void initConfig() {
        Settings settings = new Settings();
        Gson gson = new Gson();

        Writer writer = null;
        try {
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(settings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static void setConfig(Settings settings) {
        Gson gson = new Gson();

        Writer writer = null;
        try {
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(settings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static Settings getSettings() {
        Gson gson = new Gson();
        Settings settings = new Settings();
        try {
            settings = gson.fromJson(new FileReader(CONFIG_FILE), Settings.class);
        } catch (FileNotFoundException e) {
            initConfig();
            e.printStackTrace();
        }

        return settings;
    }
}
