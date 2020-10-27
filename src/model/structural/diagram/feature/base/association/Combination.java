package model.structural.diagram.feature.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Combination</b>.</p>
 * <p>Class responsible for representing <b>Combination</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-06-27
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.feature.base.Feature
 * @see    model.structural.diagram.feature.base.Variability
 */
public class Combination extends Association {
    private boolean root;
    
    /**
     * Default constructor method of Class.
     * @param variability Combination Variability.
     * @param feature Combination Feature.
     * @param root Combination is Root.
     */
    public Combination(Variability variability, Feature feature, boolean root) {
        super();
        this.root   = root;
        this.source = variability;
        this.target = feature;
        this.type   = "combination";
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Combination(Element element) {
        super(element);
        this.root = element.getAttribute("root").equals("true");
        this.type = "combination";
    }
    
    /**
     * Method responsible for returning the Root Flag.
     * @return Root Flag.
     */
    public boolean isRoot() {
        return this.root;
    }
    
    /**
     * Method responsible for setting the Root Flag.
     * @param root Root Flag.
     */
    public void setRoot(boolean root) {
        this.root = root;
    }
    
    @Override
    public Variability getSource() {
        return (Variability) this.source;
    }

    /**
     * Method responsible for setting the Variability.
     * @param source Variability.
     */
    public void setSource(Variability source) {
        this.source = source;
    }

    @Override
    public Feature getTarget() {
        return (Feature) this.target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Feature.
     */
    public void setTarget(Feature target) {
        this.target = target;
    }

    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleCombination" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String header  = "    <"      + this.type;
               header += " id=\""     + this.id             + "\"";
               header += " source=\"" + this.source.getId() + "\"";
               header += " target=\"" + this.target.getId() + "\"";
               header += " root=\""   + this.root           + "\"";
               header += ">\n";
        return header;
    }
}