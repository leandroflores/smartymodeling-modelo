package model.structural.diagram.classes.base;

import model.structural.base.interfaces.Exportable;
import model.structural.diagram.classes.Entity;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>TypeUML</b>.</p>
 * <p>Class responsible for representing <b>Type UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class TypeUML implements Comparable<TypeUML>, Exportable {
    private String  id;
    private String  path;
    private String  name;
    private String  value;
    private boolean primitive;
    private boolean standard;
    
    /**
     * Default constructor method of Class.
     */
    public TypeUML() {
        this.primitive = false;
        this.standard  = false;
        this.value     = "null";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param id Type Id.
     * @param path Type Path.
     * @param name Type Name.
     * @param primitive Type Primitive. 
     */
    public TypeUML(String id, String path, String name, boolean primitive) {
        this.id        = id;
        this.path      = path;
        this.name      = name;
        this.value     = "null";
        this.primitive = primitive;
        this.standard  = true;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param id Type Id.
     * @param path Type Path.
     * @param name Type Name.
     * @param value Type Value.
     * @param primitive Type Primitive. 
     */
    public TypeUML(String id, String path, String name, String value, boolean primitive) {
        this(id, path, name, primitive);
        this.value = value;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public TypeUML(Element element) {
        this.id        = element.getAttribute("id");
        this.path      = element.getAttribute("path");
        this.name      = element.getAttribute("name");
        this.value     = element.getAttribute("value");
        this.primitive = element.getAttribute("primitive").trim().toLowerCase().equals("true");
        this.standard  = element.getAttribute("standard").trim().toLowerCase().equals("true");
    }
    
    /**
     * Alternative constructor method of Class.
     * @param entity Entity.
     */
    public TypeUML(Entity entity) {
        this();
        this.id   = entity.getId();
        this.path = entity.getFullPath();
        this.name = entity.getName();
    }

    /**
     * Method responsible for returning Type Id.
     * @return Type Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining Type Id.
     * @param id Type Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning Type Path.
     * @return Type Path.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Method responsible for defining Type Path.
     * @param path Type Path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Method responsible for returning Type Name.
     * @return Type Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining Type Name.
     * @param name Type Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning Type Primitive.
     * @return Type Primitive.
     */
    public boolean isPrimitive() {
        return this.primitive;
    }

    /**
     * Method responsible for defining Type Primitive.
     * @param primitive Type Primitive.
     */
    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    /**
     * Method responsible for returning Type Standard.
     * @return Type Standard.
     */
    public boolean isStandard() {
        return this.standard;
    }

    /**
     * Method responsible for defining Type Standard.
     * @param standard Type Standard.
     */
    public void setStandard(boolean standard) {
        this.standard = standard;
    }
    
    /**
     * Method responsible for returning Type Signature.
     * @return Type Signature.
     */
    public String getSignature() {
        if (this.primitive)
            return "";
        return this.path.equals("") ? "" : this.path + "." + this.name;
    }
    
    /**
     * Method responsible for returning if Type is Void.
     * @return Type is Void.
     */
    public boolean isVoid() {
        return this.getName().trim().equalsIgnoreCase("void");
    }
    
    /**
     * Method responsible for returning the Body Code.
     * @return Body Code.
     */
    public String getBodyCode() {
        return "return " + this.value + ";";
    }
    
    @Override
    public String export() {
        String export  = "    <type ";
               export += "id=\""        + this.id        + "\" ";
               export += "path=\""      + this.path      + "\" ";
               export += "name=\""      + this.name      + "\" ";
               export += "value=\""     + this.value     + "\" ";
               export += "primitive=\"" + this.primitive + "\" ";
               export += "standard=\""  + this.standard  + "\"/>\n";
        return export;
    }
    
    @Override
    public int compareTo(TypeUML type) {
        return this.getName().compareTo(type.getName());
    }
    
    @Override
    public int hashCode() {
        int    hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof TypeUML == false)
            return false;
        return this.id.equals(((TypeUML) object).getId());
    }
    
    @Override
    public String toString() {
        if (this.primitive)
            return this.name;
        if (this.path.equals(""))
            return this.name + " - Model";
        return this.name + " - " + this.path + "." + this.name;
    }
}