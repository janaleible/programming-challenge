package de.exxcellent.challenge;

import de.exxcellent.challenge.data.CSVImporter;
import de.exxcellent.challenge.data.Importer;
import tech.tablesaw.api.Table;

import java.io.IOException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        String filename = args[0];

        Importer importer = new CSVImporter(filename);
        Table weatherData = null;

        try {
            weatherData = importer.get();
        } catch (IOException e) {
            System.out.println("Invalid filename supplied: " + filename);
            return;
        }

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
