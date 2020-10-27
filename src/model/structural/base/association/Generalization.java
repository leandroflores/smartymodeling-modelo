package model.structural.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Generalization</b>.</p>
 * <p>Class responsible for representing the <b>Generalization</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class Generalization extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Generalization(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "generalization";
    }

    /**
     * Method responsible for returning the Generalization Source.
     * @param  element Element.
     * @return Generalization Source.
     */
    public Element getSource(Element element) {
        return this.target.equals(element) ? this.source : null;
    }
    
    /**
     * Method responsible for returning the Generalization Target.
     * @param  element Element.
     * @return Generalization Target.
     */
    public Element getTarget(Element element) {
        return this.source.equals(element) ? this.target : null;
    }
    
    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getStyleLabel() {
        return "styleGeneralization";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_ENDFILL,     "#FFFFFF");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_BLOCK);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
}