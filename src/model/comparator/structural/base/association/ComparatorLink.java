package model.comparator.structural.base.association;

import java.util.Comparator;
import model.structural.base.association.Link;

/**
 * <p>Class of Controller <b>ComparatorLink</b>.</p>
 * <p>Class responsible for defining the <b>Link Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    model.structural.base.association.Link
 */
public class ComparatorLink implements Comparator<Link> {

    @Override
    public int compare(Link link1, Link link2) {
        return link1.getSignature().compareTo(link2.getSignature());
    }
}
