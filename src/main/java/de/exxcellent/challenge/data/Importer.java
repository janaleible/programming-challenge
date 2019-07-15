package de.exxcellent.challenge.data;

import tech.tablesaw.api.Table;

import java.io.IOException;

public interface Importer {

    public Table get() throws IOException;
}
