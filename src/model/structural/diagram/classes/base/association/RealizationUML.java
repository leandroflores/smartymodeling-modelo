package model.structural.diagram.classes.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;

/**
 * <p>Class of Model <b>RealizationUML</b>.</p>
 * <p>Class responsible for representing <b>Realization UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    model.structural.diagram.classes.base.InterfaceUML
 */
public class RealizationUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param class_ Classe UML.
     * @param interface_ Interface UML.
     */
    public RealizationUML(ClassUML class_, InterfaceUML interface_) {
        super();
        this.source = class_;
        this.target = interface_;
        this.type   = "realization";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public RealizationUML(org.w3c.dom.Element element) {
        super(element);
        this.type = "realization";
    }

    @Override
    public ClassUML getSource() {
        return (ClassUML) this.source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Source.
     */
    public void setSource(ClassUML source) {
        this.source = source;
    }

    @Override
    public InterfaceUML getTarget() {
        return (InterfaceUML) this.target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Target.
     */
    public void setTarget(InterfaceUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleRealizationUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_ENDSIZE,  "10");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDFILL,  "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_BLOCK);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <" + this.type;
               export += " id=\""        + this.id.trim()      + "\"";
               export += " class=\""     + this.source.getId() + "\"";
               export += " interface=\"" + this.target.getId() + "\"";
               export += ">\n";
        return export;
    }
}