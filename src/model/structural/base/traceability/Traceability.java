package model.structural.base.traceability;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Traceability</b>.</p>
 * <p>Class responsible for representing the <b>Traceability</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 */
public class Traceability implements Exportable {
    private String id;
    private String name;
    private String description;
    private List<Element> elements;
    
     /**
     * Default constructor method of Class.
     */
    public Traceability() {
        this.elements = new LinkedList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Traceability(org.w3c.dom.Element element) {
        this.id          = element.getAttribute("id");
        this.name        = element.getAttribute("name");
        this.description = "";
        this.elements    = new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Traceability Id.
     * @return Traceability Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Traceability Id.
     * @param id Traceability Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Traceability Name.
     * @return Traceability Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Traceability Name.
     * @param name Traceability Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Traceability Description.
     * @return Traceability Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method responsible for defining the Traceability Description.
     * @param description Traceability Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for returning the Traceability Elements.
     * @return Traceability Elements.
     */
    public List<Element> getElements() {
        return this.elements;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public String getElementsList() {
        if (this.elements.size() == 1)
            return "{" + this.elements.get(0).getName() + "}";
        String toReturn  = "{" + this.elements.get(0) + ", \n";
        for (int i = 1; i < this.elements.size() - 1; i++)
               toReturn += this.elements.get(i).getName() + ", \n";
        toReturn += this.elements.get(this.elements.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void addElement(Element element) {
        if (this.elements.contains(element) == false)
            this.elements.add(element);
    }
    
    /**
     * Method responsible for checking if Traceability contains an Element.
     * @param  element Element.
     * @return Element is Traceability.
     */
    public boolean contains(Element element) {
        return this.elements.contains(element);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        if (this.elements.contains(element))
            this.elements.remove(element);
    }
    
    /**
     * Method responsible for checking if Tracebility is Empty.
     * @return Traceability is Empty.
     */
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }
    
    /**
     * Method responsible for defining the Traceability Elements.
     * @param elements Traceability Elements.
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
    
    /**
     * Method responsible for returning the Traceability Icon.
     * @return Traceability Icon.
     */
    public String getIcon() {
        return "icons/traceability/traceability.png";
    }
    
    /**
     * Method responsible for exporting the Elements String.
     * @return Elements String.
     */
    private String exportElements() {
        String export  = "";
        for (Element element : this.elements)
               export += "    <element id=\"" + element.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <traceability";
               export += " id=\""   + this.id   + "\"";
               export += " name=\"" + this.name + "\">\n";
               export += "    <description>" + this.description + "</description>\n";
               export += this.exportElements();
               export += "  </traceability>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}