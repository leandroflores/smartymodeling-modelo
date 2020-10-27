package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of Model <b>PackageUML</b>.</p>
 * <p>Class responsible for representing <b>Package UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    model.structural.base.Element
 */
public class PackageUML extends Element {
    private PackageUML parent;
    private HashMap packages;
    private HashMap entities;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public PackageUML(ClassDiagram diagram) {
        super(diagram);
        this.name = "package";
        this.size = new Point(250, 100);
        this.type = "package";
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Class Diagram.
     */
    public PackageUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.type = "package";
        this.init();
    }

    /**
     * Method responsible for initializing the Maps.
     */
    private void init() {
        this.parent   = null;
        this.packages = new HashMap<>();
        this.entities = new HashMap<>();
    }
    
    @Override
    public void setDefaultName() {
        super.setDefaultName();
        this.name = this.name.toLowerCase();
    }
    
    /**
     * Method responsible for updating the Package Size.
     */
    public void updateSize() {
        this.setMinHeight();
        this.setMinWidth();
        this.updateParentSize();
    }
    
    /**
     * Method responsible for updating the Parent Size.
     */
    public void updateParentSize() {
        if ((this.parent != null) && (!this.parent.equals(this)))
             this.parent.updateSize();
    }
    
    /**
     * Method responsible for setting the Min Height.
     */
    public void setMinHeight() {
        Integer height = this.getMinHeight();
        this.setHeight(height > this.getHeight() ? height : this.getHeight());
    }
    
    /**
     * Method responsible for setting the Min Width.
     */
    public void setMinWidth() {
        Integer width = this.getMinWidth();
        this.setWidth(width  >  this.getWidth() ?  width : this.getWidth());
    }
    
    /**
     * Method responsible for returning the Min Height.
     * @return Min Height.
     */
    public Integer getMinHeight() {
        Integer minStereotypes = this.getStereotypesList().size() * 21 + 50;
        Integer minPackages    = this.getPackagesMinHeight();
        Integer minEntities    = this.getEntitiesMinHeight();
        return  minPackages > minEntities ? minPackages : minEntities;
    }
    
    /**
     * Method responsible for returning the Packages Min Height.
     * @return Packages Min Height.
     */
    public Integer getPackagesMinHeight() {
        Integer minimum = 100;
        for (PackageUML packageUML : this.getPackagesList()) {
            if (packageUML.getAbsoluteHeight() > minimum)
                minimum = packageUML.getAbsoluteHeight();
        }
        return  minimum;
    }
    
    /**
     * Method responsible for returning the Entities Min Height.
     * @return Entities Min Height.
     */
    public Integer getEntitiesMinHeight() {
        Integer minimum = 100;
        for (Entity entity : this.getEntitiesList()) {
            if (entity.getAbsoluteHeight() > minimum)
                minimum = entity.getAbsoluteHeight();
        }
        return  minimum;
    }
    
    /**
     * Method responsible for returning the Min Width.
     * @return Min Width.
     */
    public Integer getMinWidth() {
        Integer minPackages = this.getPackagesMinWidth() + 10;
        Integer minEntities = this.getEntitiesMinWidth() + 10;
        return  minPackages > minEntities ? minPackages : minEntities;
    }
    
    /**
     * Method responsible for returning the Packages Min Width.
     * @return Packages Min Width.
     */
    public Integer getPackagesMinWidth() {
        Integer minimum = 100;
        for (PackageUML packageUML : this.getPackagesList()) {
            if (packageUML.getAbsoluteWidth() > minimum)
                minimum = packageUML.getAbsoluteWidth();
        }
        return  minimum;
    }
    
    /**
     * Method responsible for returning the Entities Min Width.
     * @return Entities Min Width.
     */
    public Integer getEntitiesMinWidth() {
        Integer minimum = 100;
        for (Entity entity : this.getEntitiesList()) {
            if (entity.getAbsoluteWidth() > minimum)
                minimum = entity.getAbsoluteWidth();
        }
        return  minimum;
    }
    
    /**
     * Method responsible for returning the Name Position.
     * @return Name Position.
     */
    public Integer getNamePosition() {
        return (this.getStereotypesList().size() * 21) + 5;
    }
    
    @Override
    public void dx(Integer distance) {
        super.dx(distance);
        this.updateSize();
    }
    
    /**
     * Method responsible for moving X.
     * @param distance Distance.
     */
    public void dxPackages(Integer distance) {
        for (PackageUML packageUML : this.getPackagesList()) {
            packageUML.dx(distance);
        }
    }
    
    /**
     * Method responsible for moving X.
     * @param distance Distance.
     */
    public void dxEntities(Integer distance) {
        for (Entity entity : this.getEntitiesList())
            entity.dx(distance);
    }
    
    @Override
    public void dy(Integer distance) {
        super.dy(distance);
        this.updateSize();
    }
    
    /**
     * Method responsible for moving Y.
     * @param distance Distance.
     */
    public void dyPackages(Integer distance) {
        for (PackageUML packageUML : this.getPackagesList())
            packageUML.dy(distance);
    }
    
    /**
     * Method responsible for moving Y.
     * @param distance Distance.
     */
    public void dyEntities(Integer distance) {
        for (Entity entity : this.getEntitiesList())
            entity.dy(distance);
    }
    
    /**
     * Method responsible for returning the Absolute Height.
     * @return Absolute Height.
     */
    public Integer getAbsoluteHeight() {
        return this.getY() + this.getHeight() + 10;
    }
    
    /**
     * Method responsible for returning the Absolute Width.
     * @return Absolute Width.
     */
    public Integer getAbsoluteWidth() {
        return this.getX() + this.getWidth()  + 10;
    }
    
    /**
     * Method responsible for returning the Stereotypes List.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList() {
        return this.diagram.getStereotypesList(this);
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }

    /**
     * Method responsible for setting the Class Diagram.
     * @param diagram Class Diagram.
     */
    public void setDiagram(ClassDiagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Parent Package.
     * @return Parent Package.
     */
    public PackageUML getParent() {
        return this.parent;
    }

    /**
     * Method responsible for reseting the Parent.
     */
    public void resetParent() {
        if (this.parent != null)
            this.parent.removePackage(this);
        this.parent = null;
    }
    
    /**
     * Method responsible for changing the Parent Package UML.
     * @param newPackageUML New Package.
     */
    public void changePackageUML(PackageUML newPackageUML) {
        if (this.parent   != null)
            this.parent.removePackage(this);
        if (newPackageUML != null) {
            newPackageUML.addPackage(this);
            newPackageUML.updateSize();
        }
        this.setParent(newPackageUML);
    }
    
    /**
     * Method responsible for setting the Parent Package.
     * @param parent Parent Package.
     */
    public void setParent(PackageUML parent) {
        this.parent = parent;
    }
    
    /**
     * Method responsible for returning the Packages HashMap.
     * @return Packages HashMap.
     */
    public HashMap getPackages() {
        return this.packages;
    }
    
    /**
     * Method responsible for returning the Packages List.
     * @return Packages List.
     */
    public List<PackageUML> getPackagesList() {
        return new ArrayList<>(this.packages.values());
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param package_ Package UML.
     */
    public void addPackage(PackageUML package_) {
        if (!package_.equals(this))
            this.packages.put(package_.getId(), package_);
    }
    
    /**
     * Method responsible for removing a Package UML.
     * @param package_ Package UML.
     */
    public void removePackage(PackageUML package_) {
        this.packages.remove(package_.getId());
    }
    
    /**
     * Method responsible for setting the Package HashMap.
     * @param packages Package HashMap.
     */
    public void setPackages(HashMap packages) {
        this.packages = packages;
    }
    
    /**
     * Method responsible for returning the Entity HashMap.
     * @return Entity HashMap.
     */
    public HashMap getEntities() {
        return this.entities;
    }
    
    /**
     * Method responsible for returning the Entities List.
     * @return Entities List.
     */
    public List<Entity> getEntitiesList() {
        return new ArrayList<>(this.entities.values());
    }
    
    /**
     * Method responsible for adding a Entity.
     * @param entity Entity.
     */
    public void addEntity(Entity entity) {
        this.entities.put(entity.getId(), entity);
    }
    
    /**
     * Method responsible for removing a Entity.
     * @param entity Entity.
     */
    public void removeEntity(Entity entity) {
        this.entities.remove(entity.getId());
    }
    
    /**
     * Method responsible for setting the Entity HashMap.
     * @param entities Entity HashMap.
     */
    public void setEntities(HashMap entities) {
        this.entities = entities;
    }
    
    /**
     * Method responsible for updating the Global X.
     * @param distance Distance.
     */
    public void updateGlobalX(Integer distance) {
        this.setGlobalX(this.getAbsoluteX() + distance);
    }
    
    /**
     * Method responsible for updating the Packages Global Positions.
     * @param x X Value.
     * @param y Y Value.
     */
    public void updateGlobal(Integer x, Integer y) {
        this.updateGlobalPackages(x, y);
        this.updateGlobalEntities(x, y);
    }
    
    /**
     * Method responsible for updating the Packages Global Positions.
     * @param x X Value.
     * @param y Y Value.
     */
    private void updateGlobalPackages(Integer x, Integer y) {
        for (PackageUML packageUML : this.getPackagesList()) {
            packageUML.updateGlobal(x, y);
            this.updateGlobalEntities(x, y);
            packageUML.dxGlobal(x);
            packageUML.dyGlobal(y);
        }
    }
    
    /**
     * Method responsible for updating the Entities Global Positions.
     * @param x X Value.
     * @param y Y Value.
     */
    private void updateGlobalEntities(Integer x, Integer y) {
        for (Entity entity : this.getEntitiesList()) {
            entity.dxGlobal(x);
            entity.dyGlobal(y);
        }
    }
    
    /**
     * Method responsible for returning the Absolute X.
     * @return Absolute X.
     */
    public Integer getAbsoluteX() {
        if (this.parent == null)
            return this.getX();
        return this.getX() + this.parent.getAbsoluteX();
    }
    
    /**
     * Method responsible for updating the Global Y.
     * @param distance Distance.
     */
    public void updateGlobalY(Integer distance) {
        this.setGlobalY(this.getAbsoluteY() + distance);
    }
    
    /**
     * Method responsible for returning the Absolute Y.
     * @return Absolute Y.
     */
    public Integer getAbsoluteY() {
        if (this.parent == null)
            return this.getY();
        return this.getY() + this.parent.getAbsoluteY();
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "classes/package.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "stylePackageUML" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    /**
     * Method responsible for returning the Package Style.
     * @return Package Style.
     */
    public Map getPackageStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_DASH_PATTERN, "0");
               style.put(mxConstants.STYLE_FILLCOLOR,    mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR,  "#000000");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        return style;
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               style.put(mxConstants.STYLE_DASH_PATTERN, "0");
               style.put(mxConstants.STYLE_FILLCOLOR,    mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR,  mxConstants.NONE);
               style.put(mxConstants.STYLE_SHAPE,     mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    /**
     * Method responsible for returning the Package Path.
     * @return Package Path.
     */
    public String getPath() {
        if (this.parent == null)
            return this.name;
        if (this.parent.getParent() == null)
            return this.parent.getName() + "." + this.name;
        return this.parent.getPath() + "." + this.name;
    }
    
    /**
     * Method responsible for returning the Folder Path.
     * @return Folder Path.
     */
    public String getFolderPath() {
        return this.getPath().replace(".", "\\");
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}