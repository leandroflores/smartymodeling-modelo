package model.structural.diagram.activity.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>FlowUML</b>.</p>
 * <p>Class responsible for representing the <b>Flow UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  17/07/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class FlowUML extends Association {
    private String guard;
    private String action;
    private String weight;
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public FlowUML(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.guard  = "";
        this.action = "";
        this.weight = "";
        this.type   = "flow";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public FlowUML(org.w3c.dom.Element element) {
        super(element);
        this.guard  = element.getAttribute("guard");
        this.action = element.getAttribute("action");
        this.weight = element.getAttribute("weight");
        this.type   = "flow";
    }

    /**
     * Method responsible for returning the Guard.
     * @return Guard.
     */
    public String getGuard() {
        return this.guard;
    }

    /**
     * Method responsible for returning the Formatted Guard.
     * @return Formatted Guard.
     */
    public String getFormattedGuard() {
        if (!this.guard.trim().equals(""))
            return "[" + this.guard + "]";
        return "";
    }
    
    /**
     * Method responsible for setting the Guard.
     * @param guard Guard.
     */
    public void setGuard(String guard) {
        this.guard = guard.replaceAll("\\[", "").replaceAll("\\]", "").trim();
    }

    /**
     * Method responsible for returning the Action.
     * @return Action.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Method responsible for returning the Formatted Action.
     * @return Formatted Action.
     */
    public String getFormattedAction() {
        if (!this.action.trim().equals(""))
            return " / " + this.action;
        return "";
    }
    
    /**
     * Method responsible for setting the Action.
     * @param action Action.
     */
    public void setAction(String action) {
        this.action = action.replace("\\/", "").trim();
    }

    /**
     * Method responsible for returning the Weight.
     * @return Weight.
     */
    public String getWeight() {
        return this.weight;
    }
    
    /**
     * Method responsible for returning the Formatted Weight.
     * @return Formatted Weight.
     */
    public String getFormattedWeight() {
        if (!this.weight.trim().equals(""))
            return " {" + this.weight + "}";
        return "";
    }

    /**
     * Method responsible for setting the Weight.
     * @param weight Weight.
     */
    public void setWeight(String weight) {
        this.weight = weight.replaceAll("\\{", "").replaceAll("\\}", "").trim();
    }
    
    @Override
    public String getTitle() {
        return (this.getFormattedGuard()
              + this.getFormattedAction()
              + this.getFormattedWeight()).trim();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleFlowUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"      + this.type;
               export += " id=\""     + this.id.trim()      + "\"";
               export += " source=\"" + this.source.getId() + "\"";
               export += " target=\"" + this.target.getId() + "\"";
               export += " guard=\""  + this.getGuard()     + "\"";
               export += " action=\"" + this.getAction()    + "\"";
               export += " weight=\"" + this.getWeight()    + "\"";
               export += ">\n";
        return export;
    }
}