package model.structural.diagram.classes.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>Usage</b>.</p>
 * <p>Class responsible for representing <b>Usage</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-05-11
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class Usage extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Usage(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "usage";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Usage(org.w3c.dom.Element element) {
        super(element);
        this.type = "usage";
    }
    
    @Override
    public String getTitle() {
        return "use";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleUsage";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "1");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
}