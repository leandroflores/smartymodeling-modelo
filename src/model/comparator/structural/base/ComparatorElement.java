package model.comparator.structural.base;

import java.util.Comparator;
import model.structural.base.Element;

/**
 * <p>Class of Controller <b>ComparatorElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.base.Element
 */
public class ComparatorElement implements Comparator<Element> {

    @Override
    public int compare(Element element1, Element element2) {
        return element1.getType().equals(element2.getType())  ?
               element1.getName().compareTo(element2.getName()) :
               element1.getType().compareTo(element2.getType());
    }
}
