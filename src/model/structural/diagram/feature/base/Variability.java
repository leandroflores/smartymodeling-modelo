package model.structural.diagram.feature.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>Variability</b>.</p>
 * <p>Class responsible for representing <b>Variability</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-06-27
 * @see    model.structural.base.Element
 */
public class Variability extends Element {
    private String  category;
    private Feature variationPoint;
    private List<Feature> variants;
    
    /**
     * Default constructor method of Class.
     * @param diagram Feature Diagram.
     */
    public Variability(Diagram diagram) {
        super(diagram);
        this.name           = "";
        this.mandatory      = false;
        this.category       = "inclusive";
        this.variationPoint = null;
        this.variants       = new ArrayList<>();
        this.size           = new Point(50, 50);
        this.type           = "variability";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param diagram Feature Diagram.
     * @param category Variability Category.
     * @param variationPoint Variability Variation Point.
     */
    public Variability(Diagram diagram, String category, Feature variationPoint) {
        this(diagram);
        this.category       = category;
        this.variationPoint = variationPoint;
        this.variants       = new ArrayList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Feature Diagram.
     */
    public Variability(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.category  = element.getAttribute("category").trim();
        this.variants  = new ArrayList<>();
        this.type      = "feature";
    }
    
    @Override
    public String getTitle() {
        return this.category.equalsIgnoreCase("inclusive") ? "OR" : "XOR";
    }
    
    @Override
    public void setName(String name) {
        this.name = "";
    }
    
    /**
     * Method responsible for returning the Variability Category.
     * @return Variability Category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Method responsible for setting the Variability Category.
     * @param category Variability Category.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Method responsible for returning the Variability Variation Point.
     * @return Variability Variation Point.
     */
    public Feature getVariationPoint() {
        return this.variationPoint;
    }

    /**
     * Method responsible for defining the Variability Variation Point.
     * @param variationPoint Variability Variation Point.
     */
    public void setVariationPoint(Feature variationPoint) {
        this.variationPoint = variationPoint;
    }
    
    /**
     * Method responsible for returning the Variants.
     * @return Variants.
     */
    public List<Feature> getVariants() {
        return this.variants;
    }
    
    /**
     * Method responsible for returning the Variants List.
     * @return Variants List.
     */
    public String getVariantsList() {
        if (this.variants.size() == 1)
            return "{" + this.variants.get(0).getName() + "}";
        String toReturn  = "{" + this.variants.get(0) + ", \n";
        for (int i = 1; i < this.variants.size() - 1; i++)
            toReturn += this.variants.get(i).getName() + ", \n";
               toReturn += this.variants.get(this.variants.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Variant.
     * @param variant Variant.
     */
    public void addVariant(Feature variant) {
        if (this.variants.contains(variant) == false)
            this.variants.add(variant);
    }
    
    /**
     * Method responsible for checking if a Feature is Variant.
     * @param  feature Feature.
     * @return Feature is Variant.
     */
    public boolean isVariant(Feature feature) {
        return this.variants.contains(feature);
    }
    
    /**
     * Method responsible for checking if a Feature is Variant.
     * @param  feature Feature Name.
     * @return Feature is Variant.
     */
    public boolean isVariant(String feature) {
        for (Feature variant : this.variants) {
            if (variant.getName().equalsIgnoreCase(feature))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Variant.
     * @param variant Variant.
     */
    public void removeVariant(Feature variant) {
        if (this.variants.contains(variant))
            this.variants.remove(variant);
    }
    
    /**
     * Method responsible for checking if is no Variants.
     * @return No Variants.
     */
    public boolean emptyVariants() {
        return this.variants.isEmpty();
    }
    
    /**
     * Method responsible for defining Variability Variants.
     * @param variants Variability Variants.
     */
    public void setVariants(List<Feature> variants) {
        this.variants = variants;
    }
    
    @Override
    public String getIcon() {
        return this.category.equalsIgnoreCase("inclusive") ?
               "icons/diagram/feature/inclusive.png" :
               "icons/diagram/feature/exclusive.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleVariability" + this.id;
    }
    
    /**
     * Method responsible for returning the Fill Color Style.
     * @return Fill Color Style.
     */
    private String getFillColor() {
        return this.getCategory().equalsIgnoreCase("inclusive") ? "#FFFFFF" : "#000000";
    }
    
    /**
     * Method responsible for returning the Fill Color Style.
     * @return Font Color Style.
     */
    private String getFontColor() {
        return this.getCategory().equalsIgnoreCase("inclusive") ? "#000000" : "#FFFFFF";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "0");
//               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_FILLCOLOR,   this.getFillColor());
               style.put(mxConstants.STYLE_FONTCOLOR,   this.getFontColor());
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    /**
     * Method responsible for exporting the Variation Point String.
     * @return Variation Point String.
     */
    private String exportVariationPoint() {
        return " variationPoint=\"" + this.variationPoint.getId() + "\"";
    }
    
    /**
     * Method responsible for exporting the Variants String.
     * @return Variants String.
     */
    private String exportVariants() {
        String export  = "";
        for (Feature variant : this.variants)
               export += "      <variant id=\"" + variant.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "    <variability";
               export += " id=\""        + this.id           + "\"";
               export += " name=\""      + this.name         + "\"";
               export += " category=\""  + this.category     + "\"";
               export += this.exportVariationPoint();
               export += " mandatory=\"" + this.mandatory    + "\"";
               export += " x=\""         + this.getX()       + "\"";
               export += " y=\""         + this.getY()       + "\"";
               export += " globalX=\""   + this.getGlobalX() + "\"";
               export += " globalY=\""   + this.getGlobalY() + "\"";
               export += " height=\""    + this.getHeight()  + "\"";
               export += " width=\""     + this.getWidth()   + "\"";
               export += ">\n";
               export += this.exportVariants();
               export += "    </variability>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return "Variability: " + this.name;
    }
}