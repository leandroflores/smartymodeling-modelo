package model.comparator.structural.base.association;

import java.util.Comparator;
import model.structural.base.association.Association;

/**
 * <p>Class of Controller <b>ComparatorAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Association Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.base.association.Association
 */
public class ComparatorAssociation implements Comparator<Association> {

    @Override
    public int compare(Association association1, Association association2) {
        return association1.getType().equals(association2.getType()) ?
                association1.getId().compareTo(association2.getId()) :
                association1.getType().compareTo(association2.getType());
    }
}
