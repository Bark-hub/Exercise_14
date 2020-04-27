package com.myname;

import com.google.gson.*;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();

        // below is the data for the tasks
        String jsonData = "{\"toDo\": [ {\"body\" : \"Walk the dog\", \"done\" : \"false\", \"id\" : 0, \"priority\" : 3, \"title\" : \"dog\"}, { \"body\" : \"Pay the bills\", \"done\" : \"false\", \"id\" : 1, \"priority\" : 1, \"title\" : \"bills\"} ] }";

        JsonParser parser = new JsonParser();
        // here I change the data into objects using the instance of the JsonParser class
        JsonObject jsonObject = parser.parse(jsonData).getAsJsonObject();

        // Below, the program goes through each element in the data starting by recognizing that it is Key/value
        for(Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {

            // here, the program recognizes that the value in the key/value pair is an array of objects/tasks
            if (entry.getValue().isJsonArray()) {
                System.out.println(entry.getKey() + ":");

                JsonArray tasks = entry.getValue().getAsJsonArray();  //Turn it into an array

                // here, the program prints the tasks on individual lines
                for (JsonElement task : tasks) {
                    Task job = gson.fromJson(task, Task.class);  // Deserializer using Gson
                    System.out.println(job);
                }

            }

        }

        // the program is serialized
        System.out.println("\n" + gson.toJson(jsonData));
    }

}
