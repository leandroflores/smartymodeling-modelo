package model.comparator.structural.base.variability;

import java.util.Comparator;
import model.structural.base.variability.Variability;

/**
 * <p>Class of Controller <b>ComparatorVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.base.variability.Variability
 */
public class ComparatorVariability implements Comparator<Variability> {

    @Override
    public int compare(Variability variability1, Variability variability2) {
        return variability1.getName().compareTo(variability2.getName());
    }
}
