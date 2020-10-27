package model.structural.base.association;

import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Link</b>.</p>
 * <p>Class responsible for representing the <b>Link</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.Element
 * @see    model.structural.base.Stereotype
 * @see    model.structural.base.interfaces.Exportable
 */
public class Link implements Exportable {
    private Element    element;
    private Stereotype stereotype;
    
    /**
     * Default constructor method of Class.
     */
    public Link() {}
    
    /**
     * Alternative constructor method of Class.
     * @param element Element.
     * @param stereotype Stereotype.
     */
    public Link(Element element, Stereotype stereotype) {
        this.element    = element;
        this.stereotype = stereotype;
    }
    
    /**
     * Method responsible for returning Link Id.
     * @return Link Id.
     */
    public String getId() {
        return "LINK#" + this.element.getId() + "-" + this.stereotype.getId();
    }

    /**
     * Method responsible for returning Link Element.
     * @return Link Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning if Link contains the Element.
     * @param  element Element.
     * @return Link contains the Element.
     */
    public boolean contains(Element element) {
        return this.element.equals(element);
    }

    /**
     * Method responsible for setting Link Element.
     * @param element Link Element.
     */
    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * Method responsible for returning Link Stereotype.
     * @return Link Stereotype.
     */
    public Stereotype getStereotype() {
        return this.stereotype;
    }
    
    /**
     * Method responsible for returning if Link contains the Stereotype.
     * @param  stereotype Stereotype.
     * @return Link contains the Stereotype.
     */
    public boolean contains(Stereotype stereotype) {
        return this.stereotype.equals(stereotype);
    }

    /**
     * Method responsible for defining Link Stereotype.
     * @param stereotype Link Stereotype.
     */
    public void setStereotype(Stereotype stereotype) {
        this.stereotype = stereotype;
    }
    
    /**
     * Method responsible for returning the Link Signature.
     * @return Link Signature.
     */
    public String getSignature() {
        return this.stereotype.isPrimitive() + this.stereotype.getName();
    }
    
    @Override
    public String export() {
        String export  = "    <link";
               export += " element=\""    + this.element.getId()    + "\"";
               export += " stereotype=\"" + this.stereotype.getId() + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.element.toString() + " - " + this.stereotype.toString();
    }
}