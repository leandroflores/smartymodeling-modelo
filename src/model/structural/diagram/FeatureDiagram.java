package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.association.Association;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import model.structural.diagram.feature.base.association.Connection;

/**
 * <p>Class of Model <b>FeatureDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Feature Diagram</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  13/02/2020
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Diagram
 * @see    model.structural.diagram.feature.base.Feature
 * @see    model.structural.diagram.feature.base.Variability
 * @see    model.structural.diagram.feature.base.association.Connection
 */
public final class FeatureDiagram extends Diagram {
    private HashMap<String, Feature>     features;
    private HashMap<String, Variability> variability;
    private HashMap<String, Connection>  connections;
    private HashMap<String, Combination> combinations;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public FeatureDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public FeatureDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }

    @Override
    public void init() {
        this.type         = "Feature";
        this.features     = new HashMap<>();
        this.variability  = new HashMap<>();
        this.connections  = new HashMap<>();
        this.combinations = new HashMap<>();
    }

    /**
     * Method responsible for adding a Feature.
     * @param feature Feature.
     */
    public void addFeature(Feature feature) {
        feature.setId(this.nextId(feature));
        if (this.features.get(feature.getId()) == null) {
            this.features.put(feature.getId(), feature);
            this.addElement(feature);
        }
    }
    
    /**
     * Method responsible for removing a Feature.
     * @param feature Feature.
     */
    public void removeFeature(Feature feature) {
        this.removeVariationPoint(feature);
        this.removeAssociations(feature);
        this.removeElement(feature);
        this.features.remove(feature.getId());
    }
    
    /**
     * Method responsible for returning Features List.
     * @return Features List.
     */
    public List<Feature> getFeaturesList() {
        return new ArrayList<>(this.features.values());
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(this.nextId(variability));
        if (this.variability.get(variability.getId()) == null) {
            this.variability.put(variability.getId(), variability);
            this.addElement(variability);
        }
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        this.removeAssociations(variability);
        this.removeElement(variability);
        this.variability.remove(variability.getId());
    }
    
    /**
     * Method responsible for removing Variabilities by Variation Point.
     * @param feature Variation Point.
     */
    private void removeVariationPoint(Feature feature) {
        for (Variability variability_ : this.getVariationPoints(feature))
            this.removeVariability(variability_);
    }
    
    /**
     * Method responsible for returning Variation Points by Feature.
     * @param  feature Feature.
     * @return Variation Points.
     */
    public List<Variability> getVariationPoints(Feature feature) {
        List<Variability> list   = this.getVariability();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVariationPoint().equals(feature))
                filter.add(list.get(i));
        }
        return filter;
    } 
    
    /**
     * Method responsible for returning Variability List.
     * @return Variability List.
     */
    public List<Variability> getVariability() {
        return new ArrayList<>(this.variability.values());
    }
    
    /**
     * Method responsible for returning if a Feature is Root.
     * @param  feature Feature.
     * @return Feature is Root.
     */
    public boolean isRoot(Feature feature) {
        return this.getTargetAssociations("connection", feature).isEmpty();
    }
    
    /**
     * Method responsible for returning the Roots List.
     * @return Roots List.
     */
    public List<Feature> getRootsList() {
        List   roots = new ArrayList<>();
        for (Feature feature : this.getFeaturesList()) {
            if (this.isRoot(feature))
               roots.add(feature);
        }
        return roots;
    }
    
    
    
    /**
     * Method responsible for adding a Connection.
     * @param connection Connection.
     */
    public void addConnection(Connection connection) {
        connection.setId(this.nextId(connection));
        if (this.connections.get(connection.getId()) == null) {
            this.connections.put(connection.getId(), connection);
            this.addAssociation(connection);
        }
    }
    
    /**
     * Method responsible for removing a Connection.
     * @param connection Connection.
     */
    public void removeConnection(Connection connection) {
        super.removeAssociation(connection);
        this.connections.remove(connection.getId());
    }
    
    /**
     * Method responsible for returning the Connections List.
     * @return Connections List.
     */
    public List<Connection> getConnectionsList() {
        return new ArrayList(this.connections.values());
    }
    
    /**
     * Method responsible for adding a Combination.
     * @param combination Combination.
     */
    public void addCombination(Combination combination) {
        combination.setId(this.nextId(combination));
        if (this.combinations.get(combination.getId()) == null) {
            this.combinations.put(combination.getId(), combination);
            this.addAssociation(combination);
        }
    }
    
    /**
     * Method responsible for removing a Combination.
     * @param combination Combination.
     */
    public void removeCombination(Combination combination) {
        super.removeAssociation(combination);
        this.combinations.remove(combination.getId());
    }
    
    /**
     * Method responsible for returning the Combinations List.
     * @return Combinations List.
     */
    public List<Combination> getCombinationsList() {
        return new ArrayList(this.combinations.values());
    }
    
    /**
     * Method responsible for removing Connections by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.createMap(this.connections.values().toArray()));
        this.removeAssociation(element, this.createMap(this.combinations.values().toArray()));
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof Connection)
            this.removeConnection((Connection) association);
        if (association instanceof Combination)
            this.removeCombination((Combination) association);
        super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Categories Array.
     * @return Categories Array.
     */
    public String[] getCategories() {
        return new String[]{"mandatory", "optional"};
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "feature.png";
    }
    
    @Override
    public String getInstanceIcon() {
        return "";
    }
    
    @Override
    public FeatureDiagram getClone() {
        try {
            FeatureDiagram diagram = (FeatureDiagram) super.clone();
                           diagram.setElements(new HashMap<>(this.elements));
                           diagram.setAssociations(new HashMap<>(this.associations));
                           diagram.setVariabilities(new HashMap<>(this.variabilities));
            return         diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}