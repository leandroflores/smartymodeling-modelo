package model.structural.diagram.classes;

import com.mxgraph.util.mxConstants;
import funct.FunctDate;
import funct.FunctString;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.TypeUML;
import model.structural.diagram.classes.base.association.AssociationUML;

/**
 * <p>Class of Model <b>Entity</b>.</p>
 * <p>Class responsible for representing the <b>Entity</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.base.Element
 * @see    model.structural.diagram.classes.Encodable
 */
public abstract class Entity extends Element implements Encodable {
    protected String description;
    protected PackageUML packageUML;
    protected TypeUML typeUML;
    protected final LinkedHashMap attributes;
    protected final LinkedHashMap methods;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public Entity(ClassDiagram diagram) {
        super(diagram);
        this.description = "";
        this.packageUML  = null;
        this.size        = new Point(200, 120);
        this.attributes  = new LinkedHashMap();
        this.methods     = new LinkedHashMap();
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Diagram.
     */
    public Entity(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        this.description = "";
        this.packageUML  = null;
        this.attributes  = new LinkedHashMap();
        this.methods     = new LinkedHashMap();
    }
    
    /**
     * Method responsible for returning if the Entity is a Class UML.
     * @return Entity is a Class UML.
     */
    public boolean isClass() {
        return this instanceof ClassUML;
    }
    
    /**
     * Method responsible for returning if the Entity is a Interface UML.
     * @return Entity is a Interface UML.
     */
    public boolean isInterface() {
        return this instanceof InterfaceUML;
    }
    
    @Override
    public Boolean isAbstract() {
        return false;
    }

    @Override
    public Boolean isFinal() {
        return false;
    }
    
    @Override
    public Boolean isStatic() {
        return false;
    }
    
    @Override
    public String getVisibility() {
        return "public";
    }
    
    /**
     * Method responsible for updating the Entity Size.
     */
    public void updateSize() {
        this.setMinHeight();
        this.setMinWidth();
        this.updatePackageSize();
    }
    
    /**
     * Method responsible for returning the Min Height.
     * @return Min Height.
     */
    public Integer getMinHeight() {
        return    5
               + 21 * this.getStereotypesList().size()
               +  5
               + (this.type.equals("interface") ? 22 : 0)
               + 25
               +  3
               +  5
               + 16 * this.getAttributesList().size()
               +  3
               +  5
               + 16 * this.getMethodsList().size()
               + 5;
    }
    
    /**
     * Method responsible for setting the Min Height.
     */
    public void setMinHeight() {
        Integer height = this.getMinHeight();
        this.setHeight(height > this.getHeight() ? height : this.getHeight());
    }
    
    /**
     * Method responsible for returning the Min Width.
     * @return Min Width.
     */
    public Integer getMinWidth() {
        Integer[]   sizes = new Integer[]{this.getStereotypeSize(), this.getNameSize(), this.getAttributeSize(), this.getMethodSize(), 200};
        Arrays.sort(sizes);
        return sizes[4];
    }
    
    /**
     * Method responsible for setting the Min Width.
     */
    public void setMinWidth() {
        Integer width = this.getMinWidth();
        this.setWidth(width  >  this.getWidth() ?  width : this.getWidth());
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
     * Method responsible for changing the Types of Attributes and Methods.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeType(TypeUML oldType, TypeUML newType) {
         this.changeAttributeTypes(oldType, newType);
         this.changeMethodTypes(oldType, newType);
    }
    
    @Override
    public void setName(String name) {
        super.setName(name);
        this.typeUML.setName(this.getName());
        this.setMinWidth();
    }

    /**
     * Method responsible for updating the Y Position.
     */
    private void updateYPosition() {
        if (this.packageUML != null)
            this.position.y = Math.max(15, this.position.y);
    }
    
    @Override
    public void dx(Integer distance) {
        super.dx(distance);
        this.updatePackageSize();
    }

    @Override
    public void dy(Integer distance) {
        super.dy(distance);
        this.updateYPosition();
        this.updatePackageSize();
    }
    
    /**
     * Method responsible for updating the Package Size.
     */
    public void updatePackageSize() {
        if (this.packageUML != null)
            this.packageUML.updateSize();
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
     * Method responsible for returning the Entity Description.
     * @return Entity Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method responsible for setting the Entity Description.
     * @param description Entity Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Method responsible for returning the Package UML.
     * @return Package UML.
     */
    public PackageUML getPackageUML() {
        return this.packageUML;
    }
    
    /**
     * Method responsible for reseting the Package UML.
     */
    public void resetPackageUML() {
        if (this.packageUML != null)
            this.packageUML.removeEntity(this);
        this.packageUML = null;
    }
    
    /**
     * Method responsible for changing the Entity Package UML.
     * @param newPackageUML New Package.
     */
    public void changePackageUML(PackageUML newPackageUML) {
        if (this.packageUML != null)
            this.packageUML.removeEntity(this);
        if (newPackageUML   != null)
            newPackageUML.addEntity(this);
        this.setPackageUML(newPackageUML);
    }
    
    /**
     * Method responsible for setting the Package UML.
     * @param packageUML Package UML.
     */
    public void setPackageUML(PackageUML packageUML) {
        this.packageUML = packageUML;
    }
    
    /**
     * Method responsible for returning the Stereotypes List.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList() {
        return this.diagram.getStereotypesList(this);
    }
    
    /**
     * Method responsible for returning the Stereotype Size.
     * @return Stereotype Size.
     */
    public Integer getStereotypeSize() {
        Integer lenght  = 0;
        Integer current;
        for (Stereotype stereotype : this.getStereotypesList()) {
                current = stereotype.toString().length() * 8;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
    }
    
    /**
     * Method responsible for returning the Stereotypes by Element.
     * @param  element Element.
     * @return Stereotypes.
     */
    public String getStereotypes(Element element) {
        return this.diagram.getStereotypes(element, " ");
    }
    
    /**
     * Method responsible for returning the Interface Position.
     * @return Interface Position.
     */
    public Integer getInterfacePosition() {
        return this.getStereotypesList().size() * 21 + 5;
    }
    
    /**
     * Method responsible for returning the Name Position.
     * @return Name Position.
     */
    public Integer getNamePosition() {
        return this.getStereotypesList().size() * 21 + ((this.getType().equals("interface")) ? 20 : 0) + 5;
    }
    
    /**
     * Method responsible for returning the Name Size.
     * @return Name Size.
     */
    private Integer getNameSize() {
        return 10 * this.name.length();
    }
    
    /**
     * Method responsible for returning the Type UML.
     * @return Type UML.
     */
    public TypeUML getTypeUML() {
        return this.typeUML;
    }

    /**
     * Method responsible for setting the Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }
    
    /**
     * Method responsible for returning the Attributes HashMap.
     * @return Attributes HashMap.
     */
    public HashMap getAttributes() {
        return this.attributes;
    }
    
    /**
     * Method responsible for returning the Attributes List.
     * @return Attributes List.
     */
    public List<AttributeUML> getAttributesList() {
        return new ArrayList<>(this.attributes.values());
    }
    
    /**
     * Method responsible for changing the Attribute Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeAttributeTypes(TypeUML oldType, TypeUML newType) {
        List<AttributeUML>  list = this.getAttributesList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeTypeUML(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Attributes Position.
     * @return Attributes Position.
     */
    public Integer getAttributesPosition() {
        return this.getNamePosition() + 30;
    }
    
    /**
     * Method responsible for returning the Attribute Size.
     * @return Attribute Size.
     */
    public Integer getAttributeSize() {
        Integer lenght  = 0;
        Integer current;
        for (AttributeUML attribute : this.getAttributesList()) {
                current = attribute.getCompleteSignature().length() * 7;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
    }
    
    /**
     * Method responsible for returning the Attributes Names.
     * @return Attributes Names.
     */
    public List<String> getAttributesNames() {
        List   names = new ArrayList();
        for (AttributeUML attribute : this.getAttributesList())
               names.add(attribute.getName());
        return names;
    }
    
    /**
     * Method responsible for adding a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void addAttribute(AttributeUML attribute) {
        this.attributes.put(attribute.getId(), attribute);
//        this.updateSize();
    }
    
    /**
     * Method responsible for removing a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void removeAttribute(AttributeUML attribute) {
        this.attributes.remove(attribute.getId());
    }

    /**
     * Method responsible for returning Methods HashMap.
     * @return Methods HashMap.
     */
    public HashMap getMethods() {
        return this.methods;
    }
    
    /**
     * Method responsible for returning the Set of All Methods of a Entity.
     * @return All Methods Set.
     */
    public abstract Set<MethodUML> getAllMethods();
    
    /**
     * Method responsible for returning the Set of Exportable Methods of a Entity.
     * @return Exportable Methods Set.
     */
    public abstract Set<MethodUML> getExportableMethods();
    
    /**
     * Method responsible for returning the Set of Implements Methods of a Entity.
     * @return Implements Methods Set.
     */
    public abstract Set<MethodUML> getImplementsMethods();
    
    /**
     * Method responsible for returning the Extens Value.
     * @return Extens Value.
     */
    public abstract Double getExtensValue();
    
    /**
     * Method responsible for returning the Inherited Methods Set.
     * @return Inherited Methods Set.
     */
    public Set<MethodUML> getInheritedMethods() {
        Set    set = new HashSet<>();
        if (this.getSuper() != null)
            set.addAll(this.getSuper().getInheritedMethods());
               set.addAll(this.getVisibleMethods());
               set.addAll(this.getImplementsMethods());
        return set;
    }
    
    /**
     * Method responsible for returning if the Entity is the First Concrete Class.
     * @return Entity is First Concrete.
     */
    public abstract boolean isFirstConcrete();
    
    /**
     * Method responsible for returning the Abstract Inherited Methods Set.
     * @return Abstract Inherited Methods Set.
     */
    public Set<MethodUML> getAbstractInheritedMethods() {
        Set    set = new HashSet<>();
        for (MethodUML method : this.getInheritedMethods()) {
            if (this.isFirstConcrete() && method.isAbstract())
               set.add(method);
        }
        return set;
    }
    
    /**
     * Method responsible for returning the Visible Methods List.
     * @return Visible Methods List.
     */
    public List<MethodUML> getVisibleMethods() {
        List   list = new ArrayList<>();
               list.addAll(this.getMethods("public"));
               list.addAll(this.getMethods("protected"));
               list.addAll(this.getMethods("default"));
        return list;
    }
    
    /**
     * Method responsible for returning the Methods List by Visibility.
     * @param  visibility Method Visibility.
     * @return Methods List filtered.
     */
    public List<MethodUML> getMethods(String visibility) {
        List   list = new ArrayList<>();
        for (MethodUML method : this.getMethodsList()) {
            if (method.getVisibility().trim().equalsIgnoreCase(visibility.trim()))
               list.add(method);
        }
        return list;
    }
    
    /**
     * Method responsible for returning Methods List.
     * @return Methods List.
     */
    public List<MethodUML> getMethodsList() {
        return new ArrayList<>(this.methods.values());
    }
    
    /**
     * Method responsible for changing the Method Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeMethodTypes(TypeUML oldType, TypeUML newType) {
        List<MethodUML>     list = this.getMethodsList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeTypeUML(oldType, newType);
    }

    /**
     * Method responsible for returning Methods Position.
     * @return Methods Position.
     */
    public Integer getMethodsPosition() {
        return this.getAttributesPosition() 
             +  5 
             + 16 * this.getAttributesList().size()
             +  3;
    }
    
    /**
     * Method responsible for returning the Method by Name.
     * @param  name Method Name.
     * @return Method found.
     */
    public MethodUML getMethodByName(String name) {
        for (MethodUML method : this.getMethodsList()) {
            if (method.getName().equalsIgnoreCase(name))
                return method;
        }
        return null;
    }
    
    /**
     * Method responsible for returning the Method Size.
     * @return Method Size.
     */
    public Integer getMethodSize() {
        Integer lenght  = 0;
        Integer current;
        for (MethodUML method : this.getMethodsList()) {
                current = method.getCompleteSignature().length() * 7;
                lenght  = lenght > current ? lenght : current;
        }
        return  lenght;
    }
    
    /**
     * Method responsible for adding a Method UML.
     * @param method Method UML.
     */
    public void addMethod(MethodUML method) {
        this.methods.put(method.getId(), method);
//        this.updateSize();
    }
    
    /**
     * Method responsible for removing a Method UML.
     * @param method Method UML.
     */
    public void removeMethod(MethodUML method) {
        this.methods.remove(method.getId());
    }
    
    /**
     * Method responsible for returning the Comparator.
     * @return Comparator.
     */
    private Comparator<Element> getComparator() {
        return new Comparator<Element>() {
            @Override
            public int compare(Element element1, Element element2) {
                return element1.getId().compareTo(element2.getId());
            }
        };
    }
    
    /**
     * Method responsible for updating the Global X.
     * @param distance Distance.
     */
    public void updateGlobalX(Integer distance) {
        this.setGlobalX(this.getAbsoluteX() + distance);
    }
    
    /**
     * Method responsible for returning the Absolute X.
     * @return Absolute X.
     */
    public Integer getAbsoluteX() {
        if (this.packageUML == null)
            return this.getX();
        return this.getX() + this.packageUML.getAbsoluteX();
    }
    
    /**
     * Method responsible for updating the Global Y.
     * @param distance Distance.
     */
    public void updateGlobalY(Integer distance) {
        this.setGlobalY(this.getAbsoluteY() + distance);
        this.updateYPosition();
    }
    
    /**
     * Method responsible for returning the Absolute Y.
     * @return Absolute Y.
     */
    public Integer getAbsoluteY() {
        if (this.packageUML == null)
            return this.getY();
        return this.getY() + this.packageUML.getAbsoluteY();
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
        return style;
    }
    
    /**
     * Method responsible for returning Stereotype Style.
     * @return Stereotype Style.
     */
    public Map getStereotypeStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        return style;
    }
    
    /**
     * Method responsible for returning Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    /**
     * Method responsible for returning Line Style.
     * @return Line Style.
     */
    public Map getLineStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LINE);
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        return style;
    }
    
    /**
     * Method responsible for returning New Attribute Style.
     * @return New Attribute Style.
     */
    public Map getNewAttributeStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#FFFF1A");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFF1A");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFF1A");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_RESIZABLE,"0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE, "0");
        return style;
    }
    
    /**
     * Method responsible for returning New Method Style.
     * @return New Method Style.
     */
    public Map getNewMethodStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#4AAD7D");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#4AAD7D");
               style.put(mxConstants.STYLE_STROKECOLOR, "#4AAD7D");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_RESIZABLE,"0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE, "0");
        return style;
    }
    
    /**
     * Method responsible for returning the Entity Super.
     * @return Entity Super.
     */
    public Entity getSuper() {
        Element element  = this.diagram.getSuper(this);
        return  element != null ? (Entity) element : null;
    }
    
    /**
     * Method responsible for returning the Package Code.
     * @return Package Code.
     */
    protected String getPackageCode() {
        if (this.packageUML == null)
            return "";
        return "package " + this.packageUML.getPath() + ";\n\n";
    }
    
    /**
     * Method responsible for returning the Imports Code.
     * @return Imports Code.
     */
    protected String getImportsCode() {
        String code  = "";
        for (String string : this.getImportations())
               code += "import " + string + ";\n";
        return code + "\n";
    }
    
    /**
     * Method responsible for returning the Importation List.
     * @return Importation List.
     */
    protected List<String> getImportations() {
        Set<String> set  = new HashSet<>();
                    this.addSuperPackage(set);
                    this.addInterfacesPackages(set);
                    this.addAttributesPackages(set);
                    this.addAssociationsPackages(set);
                    this.addMethodsPackages(set);
        return      this.getImportationsList(set);
    }
    
    /**
     * Method responsible for returning the Importation List.
     * @param  set Packages Set.
     * @return Importations List.
     */
    private List<String> getImportationsList(Set<String> set) {
        List<String> list = this.filterImportations(set);
        Collections.sort(list);
        return list;
    }
    
    /**
     * Method responsible for filtering the Checked Importations.
     * @param  set Packages Set.
     * @return Checked Importations.
     */
    private List<String> filterImportations(Set<String> set) {
        List<String> filter = new ArrayList<>();
        for (String  string : new ArrayList<>(set)) {
            if ((!string.equals("")) && (string.contains(".")) && (!string.startsWith(this.getPackagePath())))
                filter.add(string);
        }
        return filter;
    }
    
    /**
     * Method responsible for adding the Super Package.
     * @param set Packages Set.
     */
    private void addSuperPackage(Set<String> set) {
        Entity  super_ = this.getSuper();
        String  path   = super_ != null ? this.setPath(super_.getFullPath()) : "";
        set.add(path);
    }
    
    /**
     * Method responsible for adding the Interfaces Packages.
     * @param set Packages Set.
     */
    private void addInterfacesPackages(Set<String> set) {
        if (this instanceof ClassUML)
            ((ClassUML) this).addInterfacesPackages(set);
    }
    
    /**
     * Method responsible for adding the Attributes Packages.
     * @param set Packages Set.
     */
    protected void addAttributesPackages(Set<String> set) {
        for (AttributeUML attribute : this.getAttributesList())
            set.add(this.setPath(attribute.getTypeUML().getSignature()));
    }
    
    /**
     * Method responsible for adding the Associations Packages.
     * @param set Packages Set.
     */
    protected void addAssociationsPackages(Set<String> set) {
        this.addSourceAssociationsPackages(set);
        this.addTargetAssociationsPackages(set);
    }
    
    /**
     * Method responsible for adding the Source Associations Packages.
     * @param set Packages Set.
     */
    protected void addSourceAssociationsPackages(Set<String> set) {
        for (Association association : this.diagram.getTargetAssociations("association", this))
            set.add(((AssociationUML) association).getSource().getFullPath());
    }
    
    /**
     * Method responsible for adding the Target Associations Packages.
     * @param set Packages Set.
     */
    protected void addTargetAssociationsPackages(Set<String> set) {
        for (Association association : this.diagram.getSourceAssociations("association", this)) {
            if (!((AssociationUML) association).isDirection())
                set.add(((AssociationUML) association).getTarget().getFullPath());
        }
    }
    
    /**
     * Method responsible for adding the Method Packages.
     * @param set Packages Set.
     */
    protected void addMethodsPackages(Set<String> set) {
        for (MethodUML method : this.getMethodsList()) {
            set.add(this.setPath(method.getReturn().getSignature()));
            method.addParametersPackages(set);
        }
    }
    
    /**
     * Method responsible for returning the Java Doc.
     * @return Java Doc.
     */
    private String getJavaDocCode() {
        String entity = new FunctString().getInitUpperCase(this.type);
        String date   = new FunctDate().getFormattedDate("MM-dd-yyyy", new Date());
        String code   = "/**\n";
               code  += " * <p>" + entity + " <b>" + this.name + "</b>.</p>\n";
               code  += " * <p>Source code of <b>" + entity + "</b> automatically generated by <b>SMartyModeling</b>.</p>\n";
               code  += " * @author SMartyModeling\n";
               code  += " * @since  " + date + "\n";
               code  += " */\n";
        return code;
    }
    
    /**
     * Method responsible for returning the Signature Code.
     * @return Signature Code.
     */
    public abstract String getSignatureCode();
    
    /**
     * Method responsible for returning the Extends Code.
     * @return Extends Code.
     */
    protected String getExtendsCode() {
        Entity entity  = this.getSuper();
        return entity != null ? "extends " + entity.getName() + " " : "";
    }
    
    /**
     * Method responsible for returning the Attributes Code.
     * @return Attributes Code.
     */
    protected String getAttributesCode() {
        String code  = "";
        for (AttributeUML attribute : this.getAttributesList())
               code += "    " + attribute.exportCode() + "\n";
        return code;
    }
    
    /**
     * Method responsible for returning the Associations Code.
     * @return Associations Code.
     */
    protected String getAssociationsCode() {
        return this.getAssociationsSourceCode()
             + this.getAssociationsTargetCode();
    }
    
    /**
     * Method responsible for returning the Associations Source Code.
     * @return Associations Source Code.
     */
    protected String getAssociationsSourceCode() {
        String code  = "";
        for (Association association : this.diagram.getTargetAssociations("association", this))
               code += "    " + ((AssociationUML) association).getSourceCode() + "\n";
        return code;
    }
    
    /**
     * Method responsible for returning the Associations Target Code.
     * @return Associations Target Code.
     */
    protected String getAssociationsTargetCode() {
        String code  = "";
        for (Association association : this.diagram.getSourceAssociations("association", this)) {
            if (((AssociationUML) association).isTarget(this) && !((AssociationUML) association).isDirection())
               code += "    " + ((AssociationUML) association).getTargetCode() + "\n";
        }
        return code;
    }
    
    /**
     * Method responsible for returning the Methods Code.
     * @return Methods Code.
     */
    protected String getMethodsCode() {
        String code  = "";
        for (MethodUML method : this.getMethodsList())
               code += "    " + method.exportCode() + "\n";
        return code;
    }
    
    @Override
    public String exportCode() {
        String export  = this.getPackageCode();
               export += this.getImportsCode();
               export += this.getJavaDocCode();
               export += this.getSignatureCode()    + "{\n";
               export += this.getAttributesCode();
               export += this.getAssociationsCode() + "\n";
               export += this.getMethodsCode();
        return export + "}";
    }
    
    /**
     * Method responsible for exporting the Header.
     * @return Header.
     */
    public abstract String exportHeader();
    
    /**
     * Method responsible for exporting the Parent.
     * @return Parent.
     */
    protected String exportParent() {
        if (this.packageUML == null)
            return " parent=\"\"";
        return " parent=\"" + this.getPackageUML().getId() + "\"";
    }
    
    /**
     * Method responsible for exporting the Description.
     * @return Description.
     */
    public String exportDescription() {
        String export  = "      ";
               export += "<description>";
               export += this.description;
               export +=  "</description>\n";
        return export;
    }
    
    /**
     * Method responsible for exporting Attributes.
     * @return Attributes.
     */
    public String exportAttributes() {
        String export  = "";
        for (AttributeUML attribute : this.getAttributesList())
               export +=  attribute.export();
        return export;
    }
    
    /**
     * Method responsible for exporting Methods.
     * @return Methods.
     */
    public String exportMethods() {
        String export  = "";
        for (MethodUML   method : this.getMethodsList())
               export += method.export();
        return export;
    }
    
    /**
     * Method responsible for exporting Footer.
     * @return Footer.
     */
    public String exportFooter() {
        return "    </" + this.type + ">\n";
    }
    
    @Override
    public String export() {
        return   this.exportHeader()
               + this.exportDescription()
               + this.exportAttributes()
               + this.exportMethods()
               + this.exportFooter();
    }
    
    /**
     * Method responsible for returning if is in the Same Package of a Entity.
     * @param  entity Entity.
     * @return Same Package.
     */
    public boolean samePackage(Entity entity) {
        return this.getPackagePath().equals(entity.getPackagePath());
    }
    
    /**
     * Method responsible for returning the Package Path.
     * @return Package Path.
     */
    public String getPackagePath() {
        return this.packageUML == null ? this.name : this.packageUML.getPath();
    }
    
    /**
     * Method responsible for returning the Full Path.
     * @return Full Path.
     */
    public String getFullPath() {
        return (this.packageUML != null ? this.packageUML.getPath() + "." : "") + this.name;
    }
    
    /**
     * Method responsible for returning the Import Path.
     * @param  path Original Path.
     * @return Import Path.
     */
    protected String setPath(String path) {
        if ((path.equals("")) || (path.contains(".") == false))
            return "";
        return path.startsWith(".") ? path.substring(path.indexOf(".") + 1) : path;
    }
    
    @Override
    public String toString() {
        return this.getFullPath();
    }
}