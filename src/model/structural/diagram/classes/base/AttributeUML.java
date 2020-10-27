package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of Model <b>AttributeUML</b>.</p>
 * <p>Class responsible for representing <b>Attribute UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.Entity
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class AttributeUML extends Element implements Encodable {
    private Entity  entity;
    private TypeUML typeUML;
    private String  visibility;
    private boolean static_;
    private boolean final_;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public AttributeUML(Diagram diagram) {
        super(diagram);
        this.id         = null;
        this.name       = "attribute";
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = "private";
        this.static_    = false;
        this.final_     = false;
        this.type       = "attribute";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public AttributeUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram);
        this.id         = element.getAttribute("id");
        this.name       = element.getAttribute("name");
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = element.getAttribute("visibility");
        this.static_    = element.getAttribute("static").trim().equals("true");
        this.final_     = element.getAttribute("final").trim().equals("true");
        this.type       = "attribute";
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        this.name = this.name.toLowerCase().trim();
        this.entity.setMinWidth();
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.name = this.getName().replaceAll(" ", "");
        this.entity.setMinWidth();
    }
    
    /**
     * Method responsible for returning Entity.
     * @return Entity.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Method responsible for defining Entity.
     * @param entity Entity.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Method responsible for returning Type UML.
     * @return Type UML.
     */
    public TypeUML getTypeUML() {
        return this.typeUML;
    }

    /**
     * Method responsible for defining Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        if (this.typeUML.equals(oldType))
            this.typeUML = newType;
    }

    @Override
    public String getVisibility() {
        return this.visibility;
    }

    /**
     * Method responsible for returning Visibility.
     * @param visibility Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public Boolean isStatic() {
        return this.static_;
    }

    /**
     * Method responsible for defining Static Flag.
     * @param static_ Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }
    
    @Override
    public Boolean isFinal() {
        return this.final_;
    }

    /**
     * Method responsible for defining Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }
    
    @Override
    public Boolean isAbstract() {
        return false;
    }
    
    @Override
    public String exportCode() {
        String code  = this.printVisibility();
               code += (this.static_)  ? " static" : "";
               code += (this.final_)   ? " final"  : "";
               code += " " + this.printTypeUML();
               code += " " + this.name + ";";
        return code;
    }
    
    /**
     * Method responsible for printing Visibility.
     * @return Visibility.
     */
    private String printVisibility() {
        if (this.visibility.equals("package"))
            return "";
        if (this.visibility.equals("default"))
            return "";
        return this.visibility;
    }
    
    /**
     * Method responsible for printing Import.
     * @return Import.
     */
    public String printImport() {
        if (this.typeUML.getPath().contains("."))
            return this.typeUML.getPath();
        return "";
    }
    
    /**
     * Method responsible for printing Type UML.
     * @return Type UML.
     */
    private String printTypeUML() {
        if (this.typeUML == null)
            return "Object";
        return this.typeUML.getName();
    }
    
    /**
     * Method responsible for returning Visibility Symbol.
     * @return Visibility Symbol.
     */
    private String getVisibilitySymbol() {
        if (this.visibility.equals("public"))
            return "+";
        if (this.visibility.equals("protected"))
            return "#";
        if (this.visibility.equals("private"))
            return "-";
        return "~";
    }
    
    /**
     * Method responsible for returning the Complete Signature.
     * @return Complete Signature.
     */
    public String getCompleteSignature() {
        return (this.entity.getStereotypes(this) + this.getSignature()).trim();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.getVisibilitySymbol() + " " + this.name + " : " + this.printTypeUML();
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "classes/attribute.png";
    }
    
    @Override
    public String getTitle() {
        return this.getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleAttributeUML" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
//               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR,mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, this.static_ ? 1 : mxConstants.FONT_UNDERLINE);
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FONTSIZE,  "12");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
        return style;
    }
    
    /**
     * Method responsible for exporting the Type UML.
     * @return Type UML.
     */
    private String exportType() {
        if (this.typeUML == null)
            return " type=\"TYPE#21\"";
        return " type=\"" + this.typeUML.getId() + "\"";
    }
    
    @Override
    public String export() {
        String export  = "      <"        + this.type;
               export += " id=\""         + this.id          + "\"";
               export += " name=\""       + this.name        + "\"";
               export += this.exportType();
               export += " visibility=\"" + this.visibility + "\"";
               export += " static=\""     + this.static_    + "\"";
               export += " final=\""      + this.final_     + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.getSignature();
    }
}