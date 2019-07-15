package de.exxcellent.challenge;

import de.exxcellent.challenge.analysis.MinimumAbsoluteDifferenceTask;
import de.exxcellent.challenge.analysis.Task;
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

        String weatherFile = null;
        String footballFile = null;

        try {
            weatherFile = args[0];
            footballFile = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Expected exactly two arguments, " + args.length + " passed");
            return;
        }

        Importer importer = new CSVImporter(weatherFile);
        Table weatherData = null;

        try {
            weatherData = importer.get();
        } catch (IOException e) {
            System.out.println("Invalid filename supplied: " + weatherFile);
            return;
        }

        importer = new CSVImporter(footballFile);
        Table footballData = null;

        try {
            footballData = importer.get();
        } catch (IOException e) {
            System.out.println("Invalid filename supplied: " + footballFile);
            return;
        }

        Task<String> minWeatherDifference = new MinimumAbsoluteDifferenceTask("MxT", "MnT", "Day");

        Task<String> minGoalDifference = new MinimumAbsoluteDifferenceTask("Goals", "Goals Allowed", "Team");

        String dayWithSmallestTempSpread = minWeatherDifference.execute(weatherData);
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = minGoalDifference.execute(footballData);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
