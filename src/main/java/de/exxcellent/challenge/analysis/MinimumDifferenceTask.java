package de.exxcellent.challenge.analysis;

import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.List;

import static tech.tablesaw.aggregate.AggregateFunctions.min;

public class MinimumDifferenceTask implements Task<String> {

    private String minuendCol;
    private String subtrahendCol;
    private String returnCol;

    public MinimumDifferenceTask(String minuendCol, String subtrahendCol, String returnCol) {
        this.minuendCol = minuendCol;
        this.subtrahendCol = subtrahendCol;
        this.returnCol = returnCol;
    }

    @Override
    public String execute(Table table) {

        NumberColumn difference = table.numberColumn(this.minuendCol).subtract(table.numberColumn(this.subtrahendCol));
        int minDiff = table.summarize(difference, min).apply().nCol(0).get(0).intValue();

        StringColumn results = table.column(this.returnCol).where(difference.isEqualTo(minDiff)).asStringColumn();

        return this.formatOutput(results.asList());
    }

    private String formatOutput(List<String> results) {

        StringBuilder output = new StringBuilder();

        for (String result : results.subList(0, results.size() - 1)) {
            output.append(result).append(", ");
        }

        output.append(results.get(results.size() - 1));

        return output.toString();
    }
}
