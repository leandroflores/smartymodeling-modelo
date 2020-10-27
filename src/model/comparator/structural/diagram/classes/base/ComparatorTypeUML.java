package model.comparator.structural.diagram.classes.base;

import java.util.Comparator;
import model.structural.diagram.classes.base.TypeUML;

/**
 * <p>Class of Controller <b>ComparatorTypeUML</b>.</p>
 * <p>Class responsible for defining the <b>Type UML Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class ComparatorTypeUML implements Comparator<TypeUML> {

    @Override
    public int compare(TypeUML type1, TypeUML type2) {
        return type1.getName().compareTo(type2.getName());
    }
}
