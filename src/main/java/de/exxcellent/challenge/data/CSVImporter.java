package de.exxcellent.challenge.data;

import tech.tablesaw.api.Table;

import java.io.IOException;


public class CSVImporter implements Importer {

    private String filename;

    public CSVImporter(String filename) {
        this.filename = filename;
    }

    @Override
    public Table get() throws IOException {
        return Table.read().file(this.filename);
    }
}
