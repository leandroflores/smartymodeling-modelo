package model.structural.base.product;

import funct.FunctString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Product</b>.</p>
 * <p>Class responsible for representing the <b>Product</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Product implements Exportable {
    private String  id;
    private String  name;
    private String  version;
    private String  description;
    private HashMap instances;
    
    /**
     * Default constructor method of Class.
     */
    public Product() {
        this.version     = "1.0";
        this.description = "";
        this.instances   = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Product(org.w3c.dom.Element element) {
        this();
        this.id          = element.getAttribute("id");
        this.name        = element.getAttribute("name");
        this.version     = element.getAttribute("version");
        this.description = "";
    }
    
    /**
     * Method responsible for returning the Product Id.
     * @return Product Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for setting the Product Id.
     * @param id Product Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }
   
    /**
     * Method responsible for returning the Product Name.
     * @return Product Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Product Name.
     * @param name Product Name.
     */
    public void setName(String name) {
        String string = new FunctString().getString(name);
        this.name     = string.isEmpty() ? this.name : string;
    }

    /**
     * Method responsible for returning the Product Version.
     * @return Product Version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Method responsible for setting the Product Version.
     * @param version Product Version.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Method responsible for returning the Product Description.
     * @return Product Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method responsible for returning the Product Description.
     * @param description Product Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for returning if Product is Empty.
     * @return Product is Empty.
     */
    public boolean isEmpty() {
        return this.getInstances().isEmpty();
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public HashMap<String, Instance> getInstances() {
        return this.instances;
    }
    
    /**
     * Method responsible for updating the Instances.
     */
    public void updateInstances() {
        for (Instance instance : this.getInstancesList()) {
            if (instance.isEmpty())
                this.removeInstance(instance);
        }
    }
    
    /**
     * Method responsible for returning the Instances List by Diagram Type.
     * @param  type Diagram Type.
     * @return Instances List found.
     */
    public List<Instance> getInstances(String type) {
        List   list = new ArrayList();
        for (Instance instance : this.getInstancesList()) {
            if (instance.getDiagram().getType().equalsIgnoreCase(type))
               list.add(instance);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public List<Instance> getInstancesList() {
        return new ArrayList<>(this.instances.values());
    }
    
    /**
     * Method responsible for returning the Artifacts List.
     * @return Artifacts List.
     */
    public List<Artifact> getArtifactsList() {
        List   list = new ArrayList<>();
        for (Instance instance : this.getInstancesList())
               list.addAll(instance.getArtifactsList());
        return list;
    }
    
    /**
     * Method responsible for returning the Next Instance Id.
     * @return Next Instance Id.
     */
    public String nextInstanceId() {
        Integer index  = this.instances.size();
        String  nextId = "INSTANCE#" + ++index;
        while (this.instances.get(nextId) != null)
                nextId = "INSTANCE#" + ++index;
        return  nextId;
    }
    
    /**
     * Method responsible for adding a Instance.
     * @param instance Instance.
     */
    public void addInstance(Instance instance) {
        instance.setId(this.nextInstanceId());
        instance.setProduct(this);
        this.instances.put(instance.getId(), instance);
    }
    
    /**
     * Method responsible for removing a Instance.
     * @param instance Instance.
     */
    public void removeInstance(Instance instance) {
        this.instances.remove(instance.getId());
    }
    
    /**
     * Method responsible for returning if a Product contains a Element.
     * @param  element Element.
     * @return Product contains a Element.
     */
    public boolean contains(Element element) {
        for (Instance instance : this.getInstancesList()) {
            if (instance.contains(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing the Instances by Diagram.
     * @param diagram Diagram.
     */
    public void remove(Diagram diagram) {
        for (Instance instance : this.getInstancesList()) {
            if (instance.getDiagram().equals(diagram))
                this.removeInstance(instance);
        }
    }
    
    /**
     * Method responsible for removing the Artefacts by Element.
     * @param element Element.
     */
    public void remove(Element element) {
        for (Instance instance : this.getInstancesList())
            instance.remove(element);
    }
    
    /**
     * Method responsible for removing the Relationships by Association.
     * @param association Association.
     */
    public void remove(Association association) {
        for (Instance instance : this.getInstancesList())
            instance.remove(association);
    }
    
    /**
     * Method responsible for exporting the Instances.
     * @return Instances.
     */
    private String exportInstances() {
        String export  = "";
        for (Instance instance : this.getInstancesList())
               export += instance.export();
        return export;
    }
    
    /**
     * Method responsible for setting the Product Instances.
     * @param instances Product Instances.
     */
    public void setInstances(HashMap<String, Instance> instances) {
        this.instances = instances;
    }
    
    /**
     * Method responsible for returning the Product Icon.
     * @return Product Icon.
     */
    public String getIcon() {
        return "icons/product/product.png";
    }
    
    @Override
    public String export() {
        String export  = "    <product id=\""  + this.id + "\" name=\"" + this.name + "\" version=\"" + this.version + "\">\n";
               export += "      <description>" + this.description + "</description>\n";
               export += this.exportInstances();
               export += "    </product>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name + " (" + this.version + ")";
    }
}