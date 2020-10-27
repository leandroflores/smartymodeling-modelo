package model.comparator.structural.base;

import java.util.Comparator;
import model.structural.base.Diagram;

/**
 * <p>Class of Controller <b>ComparatorDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.base.Diagram
 */
public class ComparatorDiagram implements Comparator<Diagram> {

    @Override
    public int compare(Diagram diagram1, Diagram diagram2) {
        return diagram1.getType().equals(diagram2.getType())  ?
               diagram1.getName().compareTo(diagram2.getId()) :
               diagram1.getType().compareTo(diagram2.getType());
    }
}
