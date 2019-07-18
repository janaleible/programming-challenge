package de.exxcellent.challenge;

import de.exxcellent.challenge.analysis.ColumnNotFoundException;
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
     * @param args pass <filepath> <first column> <second column> <result column>
     */
    public static void main(String... args) {

        String csvPath;
        String firstColumn;
        String secondColumn;
        String resultColumn;

        try {
            csvPath = args[0];
            firstColumn = args[1];
            secondColumn = args[2];
            resultColumn = args[3];

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Expected exactly four arguments, " + args.length + " passed");
            System.out.println("Please pass <filepath> <first column> <second column> <result column>");
            return;
        }

        Importer importer = new CSVImporter(csvPath);
        Table table;

        try {
            table = importer.get();
        } catch (IOException e) {
            System.out.println("Invalid filename supplied: " + csvPath);
            return;
        }

        Task<String> minimumAbsoluteDifferenceTask = new MinimumAbsoluteDifferenceTask(
                firstColumn, secondColumn, resultColumn
        );

        try {
            String result = minimumAbsoluteDifferenceTask.execute(table);
            System.out.printf("Minimum absolut difference occurs for: %s%n", result);
        } catch (ColumnNotFoundException exception) {
            System.out.println("Invalid column name supplied: " + exception.getInvalidColumnName());
        }
    }
}
