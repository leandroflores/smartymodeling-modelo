package model.structural.base.requirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Requirement</b>.</p>
 * <p>Class responsible for representing the <b>Requirement</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    model.structural.base.interfaces.Exportable
 */
public class Requirement implements Exportable {
    private String  id;
    private String  code;
    private String  type;
    private String  name;
    private String  description;
    private HashMap objects;
    
     /**
     * Default constructor method of Class.
     */
    public Requirement() {
        this.code        = "";
        this.type        = ControllerRequirement.TYPES[0];
        this.name        = "";
        this.description = "";
        this.objects     = new HashMap();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Requirement(org.w3c.dom.Element element) {
        this.id          = element.getAttribute("id");
        this.code        = element.getAttribute("code");
        this.type        = element.getAttribute("type");
        this.name        = element.getAttribute("name");
        this.description = "";
        this.objects     = new HashMap();
    }
    
    /**
     * Method responsible for returning the Requirement Id.
     * @return Requirement Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for setting the Requirement Id.
     * @param id Requirement Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Requirement Code.
     * @return Requirement Code.
     */
    public String getCode() {
        return this.code;
    }
    
    public String getHtmlCode() {
        String html  = "<html>";
        for (String string : this.code.split(""))
               html += string + "<br>";
        return html += "</html>";
    }

    /**
     * Method responsible for setting the Requirement Code.
     * @param code Requirement Code.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Method responsible for returning the Requirement Type.
     * @return Requirement Type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Method responsible for setting the Requirement Type.
     * @param type Requirement Type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Method responsible for returning the Requirement Name.
     * @return Requirement Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Requirement Name.
     * @param name Requirement Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Requirement Description.
     * @return Requirement Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method responsible for setting the Requirement Description.
     * @param description Requirement Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for adding the Element.
     * @param element Element.
     */
    public void addElement(Element element) {
        this.objects.put(element.getId(), element);
    }
    
    /**
     * Method responsible for returning if Requirement contains a Element.
     * @param  element Element.
     * @return Requirement contains a Element.
     */
    public boolean contains(Element element) {
        return this.objects.values().contains(element);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        this.objects.remove(element.getId());
    }
    /**
     * Method responsible for returning the All Elements List.
     * @return All Elements List.
     */
    public List<Element> getAllElements() {
        List   elements = new ArrayList<>();
               elements.addAll(this.objects.values());
        return elements;
    }
    
    /**
     * Method responsible for returning the Requirement Icon.
     * @return Requirement Icon.
     */
    public String getIcon() {
        return "icons/requirement/types/" + this.type.toLowerCase().trim() + ".png";
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement.
     * @return Traceability Requirement.
     */
    private String exportTraceability() {
        String export  = "";
               export += this.exportTraceability("Feature");
               export += this.exportTraceability("UseCase");
               export += this.exportTraceability("Class");
               export += this.exportTraceability("Component");
               export += this.exportTraceability("Sequence");
               export += this.exportTraceability("Activity");
        return export;
    }
    
    /**
     * Method responsible for exporting the Traceability Requirement by Diagram Type.
     * @param  type Diagram Type.
     * @return Traceability Requirement.
     */
    private String exportTraceability(String type) {
        String export  = "";
        for (Element element : this.getAllElements())
               export += "    <element=\"" + element.getId() +  "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <requirement";
               export += " id=\""   + this.id   + "\"";
               export += " code=\"" + this.code + "\"";
               export += " type=\"" + this.type + "\"";
               export += " name=\"" + this.name + "\">\n";
               export += "    <description>" + this.description + "</description>\n";
               export += this.exportTraceability();
               export += "  </requirement>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.code + " - " + this.name;
    }
}