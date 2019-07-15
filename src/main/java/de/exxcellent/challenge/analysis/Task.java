package de.exxcellent.challenge.analysis;

import tech.tablesaw.api.Table;

public interface Task<T> {

    public T execute(Table table);
}
