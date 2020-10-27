package model.structural.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Dependency</b>.</p>
 * <p>Class responsible for representing the <b>Dependency</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Element
 */
public class Dependency extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public Dependency(Element source, Element target) {
        this.source = source;
        this.target = target;
        this.type   = "dependency";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getStyleLabel() {
        return "styleDependency";
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,    "1");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_STARTSIZE, "15");
               style.put(mxConstants.STYLE_ENDSIZE,   "15");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
}