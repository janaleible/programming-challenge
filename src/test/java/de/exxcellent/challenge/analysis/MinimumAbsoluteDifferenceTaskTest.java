package de.exxcellent.challenge.analysis;

import de.exxcellent.challenge.data.CSVImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.tablesaw.api.Table;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class MinimumAbsoluteDifferenceTaskTest {

     private Task<String> minimumTask;

    @BeforeEach
    public void setUp() {
        this.minimumTask = new MinimumAbsoluteDifferenceTask("First", "Second", "Return");
    }

    @Test
    public void testSingleMinimum() {
        try {
            Table table = new CSVImporter("src/test/resources/de/exxcellent/challenge/simpleTable.csv").get();
            String expectedResult = "Minimum";
            assertEquals(expectedResult, this.minimumTask.execute(table));
        } catch (IOException e) {
            fail("Reading csv failed!");
        } catch (ColumnNotFoundException e) {
            fail("Column " + e.getInvalidColumnName() + " not found");
        }
    }

    @Test
    public void testMissingColumn() {
        try {
            final Table table = new CSVImporter("src/test/resources/de/exxcellent/challenge/MissingColumn.csv").get();
            assertThrows(ColumnNotFoundException.class, () -> this.minimumTask.execute(table));
        } catch (IOException e) {
            fail("Reading csv failed!");
        }
    }

    @Test
    public void testMultipleMinimums() {
        try {
            Table table = new CSVImporter("src/test/resources/de/exxcellent/challenge/multipleMinimumsTable.csv").get();
            String expectedResult = "Minimum, AnotherMinimum";
            assertEquals(expectedResult, this.minimumTask.execute(table));

        } catch (IOException e) {
            fail("Reading csv failed!");
        } catch (ColumnNotFoundException e) {
            fail("Column " + e.getInvalidColumnName() + " not found");
        }
    }
}