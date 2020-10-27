package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.diagram.classes.Encodable;
import model.structural.diagram.classes.Entity;

/**
 * <p>Classe de Modelo <b>MethodUML</b>.</p>
 * <p>Class responsible for representing <b>Method UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.diagram.classes.Encodable
 * @see    model.structural.diagram.classes.base.ParameterUML
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class MethodUML extends Element implements Encodable {
    private Entity  entity;
    private TypeUML return_;
    private String  visibility;
    private boolean constructor;
    private boolean static_;
    private boolean final_;
    private boolean abstract_;
    private List<ParameterUML> parameters;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public MethodUML(Diagram diagram) {
        super(diagram);
        this.id          = null;
        this.name        = "method";
        this.entity      = null;
        this.return_     = null;
        this.visibility  = "public";
        this.constructor = false;
        this.static_     = false;
        this.final_      = false;
        this.abstract_   = false;
        this.parameters  = new ArrayList<>();
        this.type        = "method";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public MethodUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram);
        this.id          = element.getAttribute("id");
        this.name        = element.getAttribute("name");
        this.entity      = null;
        this.return_     = null;
        this.visibility  = element.getAttribute("visibility");
        this.constructor = element.getAttribute("constructor").equals("true");
        this.static_     = element.getAttribute("static").equals("true");
        this.final_      = element.getAttribute("final").equals("true");
        this.abstract_   = element.getAttribute("abstract").equals("true");
        this.parameters  = new ArrayList<>();
        this.setReturn(element);
        this.type        = "method";
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        this.name = this.name.toLowerCase().trim();
        this.entity.setMinWidth();
    }
    
    @Override
    public String getName() {
        return this.constructor ? this.entity.getName() : this.name;
    }
    
    @Override
    public void setName(String name) {
        if (!this.constructor) {
            super.setName(name);
            this.name = this.name.trim();
            this.entity.setMinWidth();
        }
    }
    
    /**
     * Method responsible for returning if the Method is Getter.
     * @return Method is Getter.
     */
    public boolean isGetter() {
        return this.name.toLowerCase().startsWith("get");
    }
    
    /**
     * Method responsible for returning if the Method is Setter.
     * @return Method is Setter.
     */
    public boolean isSetter() {
        return this.name.toLowerCase().startsWith("set");
    }
    
    /**
     * Method responsible for returning if the Method is Overwritten.
     * @return Method is Overwritten.
     */
    public boolean isOverwritten() {
        if (this.entity.getSuper() == null && 
            this.entity.getImplementsMethods().isEmpty())
            return false;
        for (MethodUML method : this.entity.getInheritedMethods()) {
            if (this.getCompleteSignature().equalsIgnoreCase(method.getCompleteSignature()))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for returning if the Method is Specific.
     * @return Method is Specific.
     */
    public boolean isSpecific() {
        return !this.isGetter() &&
               !this.isSetter() &&
               !this.isOverwritten() &&
               !this.constructor; 
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        this.changeReturn(oldType, newType);
        this.changeParameterTypes(oldType, newType);
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
     * Method responsible for returning Method Return.
     * @return Method Return.
     */
    public TypeUML getReturn() {
        return this.return_;
    }
    
    /**
     * Method responsible for defining Method Return.
     * @param return_ Method Return.
     */
    public void setReturn(TypeUML return_) {
        this.return_ = return_;
    }
    
    /**
     * Method responsible for defining Method Return.
     * @param element W3C Element.
     */
    private void setReturn(org.w3c.dom.Element element) {
        if (this.constructor)
            this.return_ = null;
    }
    
    /**
     * Method responsible for changing the Return.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeReturn(TypeUML oldType, TypeUML newType) {
        if (this.return_.equals(oldType))
            this.return_ = newType;
    }
    
    @Override
    public String getVisibility() {
        return this.visibility;
    }

    /**
     * Method responsible for defining Method Visibility.
     * @param visibility Method Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    /**
     * Method responsible for returning Method Constructor Flag.
     * @return Method Constructor Flag.
     */
    public boolean isConstructor() {
        return this.constructor;
    }

    /**
     * Method responsible for defining Method Constructor Flag.
     * @param constructor Method Constructor Flag.
     */
    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }
    
    @Override
    public Boolean isStatic() {
        return this.static_;
    }

    /**
     * Method responsible for defining Method Static Flag.
     * @param static_ Method Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }
    
    @Override
    public Boolean isFinal() {
        return this.final_;
    }
    
    /**
     * Method responsible for defining Method Final Flag.
     * @param final_ Method Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }

    @Override
    public Boolean isAbstract() {
        return this.abstract_;
    }
    
    /**
     * Method responsible for defining Method Abstract Flag.
     * @param abstract_ Method Abstract Flag.
     */
    public void setAbstract(boolean abstract_) {
        this.abstract_  = abstract_;
        this.updateVisibility();
        this.updateAbstractClass();
    }
    
    /**
     * Method responsible for updating the Visibility.
     */
    private void updateVisibility() {
        if (this.abstract_ && this.visibility.equalsIgnoreCase("private"))
            this.setVisibility("public");
    }
    
    /**
     * Method responsible for updating the Abstract Class.
     */
    private void updateAbstractClass() {
        if (this.entity.isClass() && this.abstract_)
           ((ClassUML) this.entity).setAbstract(this.abstract_);
    }
    
    /**
     * Method responsible for returning Parameters List.
     * @return Parameters List.
     */
    public List<ParameterUML> getParameters() {
        return this.parameters;
    }
    
    /**
     * Method responsible for adding a Parameter UML.
     * @param parameter Parameter UML.
     */
    public void addParameter(ParameterUML parameter) {
        if (this.parameters.contains(parameter) == false)
            this.parameters.add(parameter);
    }
    
    /**
     * Method responsible for defining Parameters List.
     * @param parameters Parameters List.
     */
    public void setParameters(List<ParameterUML> parameters) {
        this.parameters = parameters;
    }
    
    /**
     * Method responsible for changing the Paremeter Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeParameterTypes(TypeUML oldType, TypeUML newType) {
        List<ParameterUML>  list = this.getParameters();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeTypeUML(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Parameters.
     * @return Parameters.
     */
    private String printParameters() {
        if (this.parameters.isEmpty())   
            return "()";
        if (this.parameters.size() == 1)
            return "(" + this.parameters.get(0).getTitle() + ")";
        String toReturn  = "(" + this.parameters.get(0).getTitle();
        for (int i = 1; i < this.parameters.size(); ++i)
               toReturn += ", " + this.parameters.get(i).getTitle();
        return toReturn + ")";
    }
    
    /**
     * Method responsible for returning the Parameters Code.
     * @return Parameters Code.
     */
    private String getParametersCode() {
        if (this.parameters.isEmpty())   
            return "()";
        if (this.parameters.size() == 1)
            return "(" + this.parameters.get(0).exportCode() + ")";
        String code  = "("  + this.parameters.get(0).exportCode();
        for (int  i  = 1; i < this.parameters.size(); ++i)
               code += ", " + this.parameters.get(i).exportCode();
        return code +  ")";
    }
    
    /**
     * Method responsible for adding the Parameters Packages.
     * @param set Packages Set.
     */
    public void addParametersPackages(Set<String> set) {
        for (ParameterUML parameter : this.getParameters())
            set.add(parameter.getType().getSignature());
    }
    
    @Override
    public String exportCode() {
        String code  =  this.getSignatureCode();
               code += !this.constructor ? this.getReturnCode() : "";
               code +=  this.getNameCode();
               code +=  this.getParametersCode();
               code +=  this.abstract_   ? ";\n" : " {" + this.getDefaultBodyCode();
        return code;
    }
    
    /**
     * Method responsible for returning the Default Code.
     * @return Default Code.
     */
    public String exportDefaultCode() {
        String code  = this.getDefaultSignatureCode();
               code += this.getReturnCode();
               code += " "   + this.name;
               code += this.getParametersCode();
               code += "{\n" + this.getBodyCode();
        return code;
    }
    
    /**
     * Method responsible for returning the Signature Code.
     * @return Signature Code.
     */
    public String getSignatureCode() {
        String code  = this.visibility;
               code += this.static_   ? " static"   : "";
               code += this.abstract_ ? " abstract" : "";
               code += this.final_    ? " final"    : "";
        return code;
    }
    
    /**
     * Method responsible for returning the Default Signature Code.
     * @return Default Signature Code.
     */
    public String getDefaultSignatureCode() {
        String code  = this.visibility;
               code += this.static_ ? " static" : "";
               code += this.final_  ? " final"  : "";
        return code;
    }
    
    /**
     * Method responsible for returning the Return Code.
     * @return Return Code.
     */
    private String getReturnCode() {
        return " " + this.return_.getName();
    }
    
    /**
     * Method responsible for returning the Name Code.
     * @return Name Code.
     */
    public String getNameCode() {
        return " " + this.getName();
    }
    
    /**
     * Method responsible for returning the Default Body Code.
     * @return Default Body Code.
     */
    private String getDefaultBodyCode() {
        if (this.constructor)
            return "}\n";
        if (this.return_.isVoid())
            return "\n    }\n";
        return this.getBodyCode();
    }
    
    /**
     * Method responsible for returning the Body Code.
     * @return Body Code.
     */
    private String getBodyCode() {
        return "\n        " + this.return_.getBodyCode() + "\n    }\n";
    }
    
    /**
     * Method responsible for returning the Complete Signature.
     * @return Complete Signature.
     */
    public String getCompleteSignature() {
        return this.entity.getStereotypes(this) + this.getSignature();
    }
    
    /**
     * Method responsible for returning the Short Signature.
     * @return Short Signature.
     */
    public String getShortSignature() {
        return this.name + this.printParameters();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    public String getSignature() {
        return this.getVisibilitySymbol() + " " + this.getShortSignature() + this.printReturnType();
    }
    
    /**
     * Method responsible for returning Return Type.
     * @return Return Type.
     */
    private String printReturnType() {
        if (this.constructor)
            return "";
        if (this.return_ == null)
            return "";
        return " : " + this.return_.getName();
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
     * Method responsible for returning Method Description.
     * @return Method Description.
     */
    public String getDescription() {
        return this.getVisibilitySymbol() + " " + this.name + this.getParametersCode() + this.getReturnSignature();
    }
    
    /**
     * Method responsible for returning the Return Signature.
     * @return Return Signature.
     */
    private String getReturnSignature() {
        if (this.constructor)
            return "";
        if (this.return_ == null)
            return " : void";
        return " : " + this.return_.getName();
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "classes/method.png";
    }
    
    @Override
    public String getTitle() {
        return this.getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleMethodUML" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, this.getFontCode());
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FONTSIZE,  "12");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
        return style;
    }
    
    /**
     * Method responsible for returning Font Code.
     * @return Font Code.
     */
    private int getFontCode() {
        if (this.abstract_ && this.static_)
            return 3;
        if (this.abstract_)
            return 2;
        if (this.static_)
            return 1;
        return 4;
    }
    
    /**
     * responsible for exporting Method Return.
     * @return Method Return.
     */
    private String exportReturn() {
        if (this.return_ == null)
            return "return=\"TIPO#42\"";
        return "return=\"" + this.return_.getId() + "\"";
    }
    
    @Override
    public String export() {
        String    export  = "      <"  + this.type + "";
                  export += " id=\""          + this.id          + "\"";
                  export += " name=\""        + this.name        + "\"";
                  export += " "               + this.exportReturn();
                  export += " visibility=\""  + this.visibility  + "\"";
                  export += " constructor=\"" + this.constructor + "\"";
                  export += " static=\""      + this.static_     + "\"";
                  export += " final=\""       + this.final_      + "\"";
                  export += " abstract=\""    + this.abstract_   + "\"";
                  export += ">\n";
                  export += exportParameters();
                  export += "      </" + this.type + ">\n";
        return    export;
    }
    
    /**
     * Method responsible for exporting the Method Parameters.
     * @return Method Parameters.
     */
    private String exportParameters() {
        String export  = "";
        for (int i = 0; i < this.parameters.size(); i++)
               export += this.parameters.get(i).export();
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (object instanceof MethodUML == false)
            return false;
        return this.getShortSignature().equals(((MethodUML) object).getShortSignature());
    }

    @Override
    public int hashCode() {
        int    hash = 7;
               hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.getDescription();
    }
}