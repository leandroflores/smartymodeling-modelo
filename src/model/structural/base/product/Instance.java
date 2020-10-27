package model.structural.base.product;

import funct.FunctString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Model <b>Instance</b>.</p>
 * <p>Class responsible for representing the <b>Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Instance implements Exportable {
    private String  id;
    private String  name;
    private Product product;
    private Diagram diagram;
    private HashMap artifacts;
    private HashMap relationships;
    
    /**
     * Default constructor method of Class.
     */
    public Instance() {
        this.name          = "NewInstance";
        this.product       = null;
        this.diagram       = null;
        this.artifacts     = new HashMap<>();
        this.relationships = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Instance(org.w3c.dom.Element element) {
        this();
        this.id   = element.getAttribute("id");
        this.name = element.getAttribute("name");
    }
    
    /**
     * Method responsible for returning the Instance Id.
     * @return Instance Id.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Method responsible for returning the Instance Complete Id.
     * @return Instance Complete Id.
     */
    public String getCompleteId() {
        return this.product.getId() + " - " + this.id;
    }

    /**
     * Method responsible for setting the Instance Id.
     * @param id Instance Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }
   
    /**
     * Method responsible for returning the Instance Name.
     * @return Instance Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Instance Name.
     * @param name Instance Name.
     */
    public void setName(String name) {
        String string = new FunctString().getString(name);
        this.name     = string.isEmpty() ? this.name : string;
    }

    /**
     * Method responsible for returning the Instance Product.
     * @return Instance Product.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Method responsible for setting the Instance Product.
     * @param product Instance Product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    /**
     * Method responsible for returning the Instance Diagram.
     * @return Instance Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for setting the Instance Diagram.
     * @param diagram Instance Diagram.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Method responsible for returning the Instance Artifacts.
     * @return Instance Artifacts.
     */
    public HashMap<String, Artifact> getArtifacts() {
        return this.artifacts;
    }
    
    /**
     * Method responsible for returning the Artifacts List.
     * @return Artifacts List.
     */
    public List<Artifact> getArtifactsList() {
        return new ArrayList<>(this.artifacts.values());
    }
    
    /**
     * Method responsible for returning the Next Artifact Id.
     * @return Next Artifact Id.
     */
    public String nextArtifactId() {
        Integer index  = this.artifacts.size() + 1;
        String  nextId = "ARTIFACT#" + index;
        while (this.artifacts.get(nextId) != null)
                nextId = "ARTIFACT#" + ++index;
        return  nextId;
    }
    
    /**
     * Method responsible for adding a Artifact.
     * @param artifact Artifact.
     */
    public void addArtifact(Artifact artifact) {
        artifact.setId(this.nextArtifactId());
        artifact.setInstance(this);
        this.artifacts.put(artifact.getId(), artifact);
    }
    
    /**
     * Method responsible for returning a Artifact by Id.
     * @param  id Artifact Id.
     * @return Artifact found.
     */
    public Artifact getArtifact(String id) {
        return (Artifact) this.artifacts.get(id);
    }
    
    /**
     * Method responsible for returning the Artifact by Element.
     * @param  element Element.
     * @return Artifact found.
     */
    public Artifact getArtifact(Element element) {
        for (Artifact  artifact : this.getArtifactsList()) {
            if (artifact.getElement().equals(element))
                return artifact;
        }
        return null;
    }
    
    /**
     * Method responsible for removing the Artifact by Element.
     * @param element Element.
     */
    public void remove(Element element) {
        Artifact artifact = this.getArtifact(element);
        if (artifact != null)
            this.removeArtifact(artifact);
    }
    
    /**
     * Method responsible for returning if the Instance contains a Element.
     * @param  element Element.
     * @return Instance contains Element.
     */
    public boolean contains(Element element) {
        for (Artifact artefact : this.getArtifactsList()) {
            if (artefact.getElement().equals(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Artifact.
     * @param  artifact Artifact.
     */
    public void removeArtifact(Artifact artifact) {
        this.removeRelationships(artifact);
        this.artifacts.remove(artifact.getId());
    }
    
    /**
     * Method responsible for Shift X for Cardinalities.
     * @param element Element.
     * @param distance Shift Distance.
     */
    public void dx(Element element, Integer distance) {
        List<Relationship> filter = this.getAssociations("association");
        for (Relationship relationship : filter) {
            relationship.dxSource(relationship.getAssociation().isSource(element) ? distance : 0);
            relationship.dxTarget(relationship.getAssociation().isTarget(element) ? distance : 0);
        }
    }
    
    /**
     * Method responsible for Shift Y for Cardinalities.
     * @param element Element.
     * @param distance Shift Distance.
     */
    public void dy(Element element, Integer distance) {
        List<Relationship> filter = this.getAssociations("association");
        for (Relationship relationship : filter) {
            relationship.dySource(relationship.getAssociation().isSource(element) ? distance : 0);
            relationship.dyTarget(relationship.getAssociation().isTarget(element) ? distance : 0);
        }
    }
    
    /**
     * Method responsible for setting the Instance Artifacts.
     * @param artifacts Instance Artifacts.
     */
    public void setArtifacts(HashMap<String, Artifact> artifacts) {
        this.artifacts = (HashMap) artifacts.clone();
    }
    
    /**
     * Method responsible for returning the Instance Relationships.
     * @return Instance Relationships.
     */
    public HashMap<String, Relationship> getRelationships() {
        return this.relationships;
    }
    
    /**
     * Method responsible for returning the Next Relationship Id.
     * @return Next Relationship Id.
     */
    public String nextRelationshipId() {
        Integer index  = this.relationships.size() + 1;
        String  nextId = "RELATIONSHIP#" + index;
        while (this.relationships.get(nextId) != null)
                nextId = "RELATIONSHIP#" + ++index;
        return  nextId;
    }
    
    /**
     * Method responsible for adding a Relationship.
     * @param relationship Relationship.
     */
    public void addRelationship(Relationship relationship) {
        relationship.setId(this.nextRelationshipId());
        relationship.setInstance(this);
        if (this.relationships.get(relationship.getId()) == null)
            this.relationships.put(relationship.getId(), relationship);
    }
    
    /**
     * Method responsible for returning a Relationship by Id.
     * @param  id Relationship Id.
     * @return Relationship found.
     */
    public Relationship getRelationship(String id) {
        return (Relationship) this.relationships.get(id);
    }
    
    /**
     * Method responsible for returning the Relationship by Association.
     * @param  association Association.
     * @return Relationship found.
     */
    public Relationship getRelationship(Association association) {
        for (Relationship relationship : this.getRelationshipsList()) {
            if (relationship.getAssociation().equals(association))
                return relationship;
        }
        return null;
    }
    
    /**
     * Method responsible for returning the Relationships List.
     * @return Relationships List.
     */
    public List<Relationship> getRelationshipsList() {
        return new ArrayList<>(this.relationships.values());
    }
    
    /**
     * Method responsible for removing a Relationship.
     * @param  relationship Relationship.
     */
    public void removeRelationship(Relationship relationship) {
        this.relationships.remove(relationship.getId());
    }
    
    /**
     * Method responsible for setting the Instance Relationships.
     * @param relationships Instance Relationships.
     */
    public void setRelationships(HashMap<String, Relationship> relationships) {
        this.relationships = (HashMap) relationships.clone();
    }
    
    /**
     * Method responsible for returning if Instance is Empty.
     * @return Instance is Empty.
     */
    public boolean isEmpty() {
        return this.getArtifactsList().isEmpty();
    }
    
    /**
     * Method responsible for reseting the Instance.
     */
    public void reset() {
        this.artifacts     = new HashMap();
        this.relationships = new HashMap();
    }
    
    /**
     * Method responsible for removing the Relationships by Association.
     * @param association Association.
     */
    public void remove(Association association) {
        for (Relationship relationship : this.getRelationshipsList()) {
            if (relationship.getAssociation().equals(association))
                this.removeRelationship(relationship);
        }
    }
    
    /**
     * Method responsible for removing the Relationship by Artifact.
     * @param artefact Artifact.
     */
    public void removeRelationships(Artifact artefact) {
        for (Relationship relationship : this.getRelationshipsList()) {
            if (relationship.getAssociation().contains(artefact.getElement()))
                this.removeRelationship(relationship);
        }
    }
    
    /**
     * Method responsible for updating the Instance Artifacts and Relationships.
     */
    public void update() {
        this.addPackages();
        this.addRelationships();
        this.removeRelationships();
    }
    
    /**
     * Method responsible for adding the Packages from a Class Diagram.
     */
    private void addPackages() {
        if (this.diagram instanceof ClassDiagram) {
            for (PackageUML packageUML : ((ClassDiagram) this.diagram).getPackagesList())
                this.addArtifact(new Artifact(packageUML));
        }
    }
    
    /**
     * Method responsible for adding the Instance Relationships.
     */
    public void addRelationships() {
        for (Association association : this.diagram.getAssociationsList()) {
            if ((this.contains(association.getSource()))
             && (this.contains(association.getTarget())))
                    this.addRelationship(new Relationship(this, association));
        }
    }
    
    /**
     * Method responsible for removing the Mutex and Requires Relationships.
     */
    private void removeRelationships() {
        for (Relationship relationship : this.getRelationshipsList()) {
            if (relationship.getAssociation() instanceof Requires
             || relationship.getAssociation() instanceof Mutex)
                this.removeRelationship(relationship);
        }
    }
    
    /**
     * Method responsible for returning the Abstract.
     * @return Abstract.
     */
    public String getAbstract() {
        return this.name + " (" + this.diagram.getType() + ")";
    }
    
    /**
     * Method responsible for returning the Tree Artifacts List.
     * @return Tree Artifacts List.
     */
    public List<Artifact> getTreeArtifactsList() {
        if (this.diagram instanceof ClassDiagram)
            return this.getClassArtifactsList();
        if (this.diagram instanceof ActivityDiagram)
            return this.getActivityArtifactsList();
        return this.getArtifactsList();
    }
    
    /**
     * Method responsible for returning the Artifacts List of a Class Diagram.
     * @return Artifacts List of a Class Diagram.
     */
    public List<Artifact> getClassArtifactsList() {
        List<Artifact> filter  = new ArrayList<>();
        for (Artifact  artifact : this.getArtifactsList()) {
            if (artifact.isPackage()  && ((PackageUML) artifact.getElement()).getParent() == null)
                filter.add(artifact);
            if (artifact.isEntity()   && ((Entity) artifact.getElement()).getPackageUML() == null)
                filter.add(artifact);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Artifacts List of a Activity Diagram.
     * @return Artifacts List of a Activity Diagram.
     */
    public List<Artifact> getActivityArtifactsList() {
        List<Artifact> filter  = new ArrayList<>();
        for (Artifact  artifact : this.getArtifactsList()) {
            if (artifact.getElement().getType().equalsIgnoreCase("activity"))
                filter.add(artifact);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Instance Icon.
     * @return Instance Icon.
     */
    public String getIcon() {
        return this.diagram.getInstanceIcon();
    }
    
    /**
     * Method responsible for exporting the Artifacts.
     * @return Artifacts.
     */
    private String exportArtifacts() {
        String export  = "";
        for (Artifact artifact : this.getArtifactsList())
               export += artifact.export();
        return export;
    }
    
    /**
     * Method responsible for exporting the Relationships.
     * @return Relationships.
     */
    private String exportRelationships() {
        String export  = "";
        for (Relationship relationship : this.getRelationshipsList())
               export +=  relationship.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Associations List by Type.
     * @param  type Association Type.
     * @return Associations List.
     */
    public List getAssociations(String type) {
        List    filter = new ArrayList<>();
        for (Relationship relationship : this.getRelationshipsList()) {
            if (relationship.getAssociation().getType().equalsIgnoreCase(type))
                filter.add(relationship);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Instance Summary.
     * @return Instance Summary.
     */
    public String getSummary() {
        return "Instance: " + this.getName() + " (" + this.diagram.getName() + ")";
    }
        
    @Override
    public String export() {
        String export  = "      <instance id=\"" + this.id + "\" name=\"" + this.name + "\"" + " diagram=\"" + this.diagram.getId() + "\">\n";
               export += this.exportArtifacts();
               export += this.exportRelationships();
               export += "      </instance>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name + " (" + this.diagram.getType() + ")";
    }
}