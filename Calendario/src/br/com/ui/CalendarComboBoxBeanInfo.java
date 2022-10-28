/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ui;

import java.beans.*;

/**
 *
 * @author dti
 */
public class CalendarComboBoxBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( br.com.ui.CalendarComboBox.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_accessibleContext = 0;
    private static final int PROPERTY_action = 1;
    private static final int PROPERTY_actionCommand = 2;
    private static final int PROPERTY_actionListeners = 3;
    private static final int PROPERTY_actionMap = 4;
    private static final int PROPERTY_alignmentX = 5;
    private static final int PROPERTY_alignmentY = 6;
    private static final int PROPERTY_ancestorListeners = 7;
    private static final int PROPERTY_autoscrolls = 8;
    private static final int PROPERTY_background = 9;
    private static final int PROPERTY_backgroundSet = 10;
    private static final int PROPERTY_baselineResizeBehavior = 11;
    private static final int PROPERTY_border = 12;
    private static final int PROPERTY_bounds = 13;
    private static final int PROPERTY_colorModel = 14;
    private static final int PROPERTY_component = 15;
    private static final int PROPERTY_componentCount = 16;
    private static final int PROPERTY_componentListeners = 17;
    private static final int PROPERTY_componentOrientation = 18;
    private static final int PROPERTY_componentPopupMenu = 19;
    private static final int PROPERTY_components = 20;
    private static final int PROPERTY_containerListeners = 21;
    private static final int PROPERTY_cursor = 22;
    private static final int PROPERTY_cursorSet = 23;
    private static final int PROPERTY_debugGraphicsOptions = 24;
    private static final int PROPERTY_displayable = 25;
    private static final int PROPERTY_doubleBuffered = 26;
    private static final int PROPERTY_dropTarget = 27;
    private static final int PROPERTY_editable = 28;
    private static final int PROPERTY_editor = 29;
    private static final int PROPERTY_enabled = 30;
    private static final int PROPERTY_focusable = 31;
    private static final int PROPERTY_focusCycleRoot = 32;
    private static final int PROPERTY_focusCycleRootAncestor = 33;
    private static final int PROPERTY_focusListeners = 34;
    private static final int PROPERTY_focusOwner = 35;
    private static final int PROPERTY_focusTraversable = 36;
    private static final int PROPERTY_focusTraversalKeys = 37;
    private static final int PROPERTY_focusTraversalKeysEnabled = 38;
    private static final int PROPERTY_focusTraversalPolicy = 39;
    private static final int PROPERTY_focusTraversalPolicyProvider = 40;
    private static final int PROPERTY_focusTraversalPolicySet = 41;
    private static final int PROPERTY_font = 42;
    private static final int PROPERTY_fontSet = 43;
    private static final int PROPERTY_foreground = 44;
    private static final int PROPERTY_foregroundSet = 45;
    private static final int PROPERTY_graphics = 46;
    private static final int PROPERTY_graphicsConfiguration = 47;
    private static final int PROPERTY_height = 48;
    private static final int PROPERTY_hierarchyBoundsListeners = 49;
    private static final int PROPERTY_hierarchyListeners = 50;
    private static final int PROPERTY_ignoreRepaint = 51;
    private static final int PROPERTY_inheritsPopupMenu = 52;
    private static final int PROPERTY_inputContext = 53;
    private static final int PROPERTY_inputMap = 54;
    private static final int PROPERTY_inputMethodListeners = 55;
    private static final int PROPERTY_inputMethodRequests = 56;
    private static final int PROPERTY_inputVerifier = 57;
    private static final int PROPERTY_insets = 58;
    private static final int PROPERTY_itemAt = 59;
    private static final int PROPERTY_itemCount = 60;
    private static final int PROPERTY_itemListeners = 61;
    private static final int PROPERTY_keyListeners = 62;
    private static final int PROPERTY_keySelectionManager = 63;
    private static final int PROPERTY_layout = 64;
    private static final int PROPERTY_lightweight = 65;
    private static final int PROPERTY_lightWeightPopupEnabled = 66;
    private static final int PROPERTY_locale = 67;
    private static final int PROPERTY_location = 68;
    private static final int PROPERTY_locationOnScreen = 69;
    private static final int PROPERTY_managingFocus = 70;
    private static final int PROPERTY_maximumRowCount = 71;
    private static final int PROPERTY_maximumSize = 72;
    private static final int PROPERTY_maximumSizeSet = 73;
    private static final int PROPERTY_minimumSize = 74;
    private static final int PROPERTY_minimumSizeSet = 75;
    private static final int PROPERTY_model = 76;
    private static final int PROPERTY_mouseListeners = 77;
    private static final int PROPERTY_mouseMotionListeners = 78;
    private static final int PROPERTY_mousePosition = 79;
    private static final int PROPERTY_mouseWheelListeners = 80;
    private static final int PROPERTY_name = 81;
    private static final int PROPERTY_nextFocusableComponent = 82;
    private static final int PROPERTY_opaque = 83;
    private static final int PROPERTY_optimizedDrawingEnabled = 84;
    private static final int PROPERTY_paintingForPrint = 85;
    private static final int PROPERTY_paintingTile = 86;
    private static final int PROPERTY_parent = 87;
    private static final int PROPERTY_peer = 88;
    private static final int PROPERTY_popupMenuListeners = 89;
    private static final int PROPERTY_popupVisible = 90;
    private static final int PROPERTY_preferredSize = 91;
    private static final int PROPERTY_preferredSizeSet = 92;
    private static final int PROPERTY_propertyChangeListeners = 93;
    private static final int PROPERTY_prototypeDisplayValue = 94;
    private static final int PROPERTY_registeredKeyStrokes = 95;
    private static final int PROPERTY_renderer = 96;
    private static final int PROPERTY_requestFocusEnabled = 97;
    private static final int PROPERTY_rootPane = 98;
    private static final int PROPERTY_selectedIndex = 99;
    private static final int PROPERTY_selectedItem = 100;
    private static final int PROPERTY_selectedObjects = 101;
    private static final int PROPERTY_showing = 102;
    private static final int PROPERTY_size = 103;
    private static final int PROPERTY_text = 104;
    private static final int PROPERTY_toolkit = 105;
    private static final int PROPERTY_toolTipText = 106;
    private static final int PROPERTY_topLevelAncestor = 107;
    private static final int PROPERTY_transferHandler = 108;
    private static final int PROPERTY_treeLock = 109;
    private static final int PROPERTY_UI = 110;
    private static final int PROPERTY_UIClassID = 111;
    private static final int PROPERTY_valid = 112;
    private static final int PROPERTY_validateRoot = 113;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 114;
    private static final int PROPERTY_vetoableChangeListeners = 115;
    private static final int PROPERTY_visible = 116;
    private static final int PROPERTY_visibleRect = 117;
    private static final int PROPERTY_width = 118;
    private static final int PROPERTY_x = 119;
    private static final int PROPERTY_y = 120;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[121];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", br.com.ui.CalendarComboBox.class, "getAccessibleContext", null ); // NOI18N
            properties[PROPERTY_action] = new PropertyDescriptor ( "action", br.com.ui.CalendarComboBox.class, "getAction", "setAction" ); // NOI18N
            properties[PROPERTY_actionCommand] = new PropertyDescriptor ( "actionCommand", br.com.ui.CalendarComboBox.class, "getActionCommand", "setActionCommand" ); // NOI18N
            properties[PROPERTY_actionListeners] = new PropertyDescriptor ( "actionListeners", br.com.ui.CalendarComboBox.class, "getActionListeners", null ); // NOI18N
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", br.com.ui.CalendarComboBox.class, "getActionMap", "setActionMap" ); // NOI18N
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", br.com.ui.CalendarComboBox.class, "getAlignmentX", "setAlignmentX" ); // NOI18N
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", br.com.ui.CalendarComboBox.class, "getAlignmentY", "setAlignmentY" ); // NOI18N
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", br.com.ui.CalendarComboBox.class, "getAncestorListeners", null ); // NOI18N
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", br.com.ui.CalendarComboBox.class, "getAutoscrolls", "setAutoscrolls" ); // NOI18N
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", br.com.ui.CalendarComboBox.class, "getBackground", "setBackground" ); // NOI18N
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", br.com.ui.CalendarComboBox.class, "isBackgroundSet", null ); // NOI18N
            properties[PROPERTY_baselineResizeBehavior] = new PropertyDescriptor ( "baselineResizeBehavior", br.com.ui.CalendarComboBox.class, "getBaselineResizeBehavior", null ); // NOI18N
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", br.com.ui.CalendarComboBox.class, "getBorder", "setBorder" ); // NOI18N
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", br.com.ui.CalendarComboBox.class, "getBounds", "setBounds" ); // NOI18N
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", br.com.ui.CalendarComboBox.class, "getColorModel", null ); // NOI18N
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", br.com.ui.CalendarComboBox.class, null, null, "getComponent", null ); // NOI18N
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", br.com.ui.CalendarComboBox.class, "getComponentCount", null ); // NOI18N
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", br.com.ui.CalendarComboBox.class, "getComponentListeners", null ); // NOI18N
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", br.com.ui.CalendarComboBox.class, "getComponentOrientation", "setComponentOrientation" ); // NOI18N
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", br.com.ui.CalendarComboBox.class, "getComponentPopupMenu", "setComponentPopupMenu" ); // NOI18N
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", br.com.ui.CalendarComboBox.class, "getComponents", null ); // NOI18N
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", br.com.ui.CalendarComboBox.class, "getContainerListeners", null ); // NOI18N
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", br.com.ui.CalendarComboBox.class, "getCursor", "setCursor" ); // NOI18N
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", br.com.ui.CalendarComboBox.class, "isCursorSet", null ); // NOI18N
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", br.com.ui.CalendarComboBox.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" ); // NOI18N
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", br.com.ui.CalendarComboBox.class, "isDisplayable", null ); // NOI18N
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", br.com.ui.CalendarComboBox.class, "isDoubleBuffered", "setDoubleBuffered" ); // NOI18N
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", br.com.ui.CalendarComboBox.class, "getDropTarget", "setDropTarget" ); // NOI18N
            properties[PROPERTY_editable] = new PropertyDescriptor ( "editable", br.com.ui.CalendarComboBox.class, "isEditable", "setEditable" ); // NOI18N
            properties[PROPERTY_editor] = new PropertyDescriptor ( "editor", br.com.ui.CalendarComboBox.class, "getEditor", "setEditor" ); // NOI18N
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", br.com.ui.CalendarComboBox.class, "isEnabled", "setEnabled" ); // NOI18N
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", br.com.ui.CalendarComboBox.class, "isFocusable", "setFocusable" ); // NOI18N
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", br.com.ui.CalendarComboBox.class, "isFocusCycleRoot", "setFocusCycleRoot" ); // NOI18N
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", br.com.ui.CalendarComboBox.class, "getFocusCycleRootAncestor", null ); // NOI18N
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", br.com.ui.CalendarComboBox.class, "getFocusListeners", null ); // NOI18N
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", br.com.ui.CalendarComboBox.class, "isFocusOwner", null ); // NOI18N
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", br.com.ui.CalendarComboBox.class, "isFocusTraversable", null ); // NOI18N
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", br.com.ui.CalendarComboBox.class, null, null, null, "setFocusTraversalKeys" ); // NOI18N
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", br.com.ui.CalendarComboBox.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", br.com.ui.CalendarComboBox.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", br.com.ui.CalendarComboBox.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", br.com.ui.CalendarComboBox.class, "isFocusTraversalPolicySet", null ); // NOI18N
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", br.com.ui.CalendarComboBox.class, "getFont", "setFont" ); // NOI18N
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", br.com.ui.CalendarComboBox.class, "isFontSet", null ); // NOI18N
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", br.com.ui.CalendarComboBox.class, "getForeground", "setForeground" ); // NOI18N
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", br.com.ui.CalendarComboBox.class, "isForegroundSet", null ); // NOI18N
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", br.com.ui.CalendarComboBox.class, "getGraphics", null ); // NOI18N
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", br.com.ui.CalendarComboBox.class, "getGraphicsConfiguration", null ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", br.com.ui.CalendarComboBox.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", br.com.ui.CalendarComboBox.class, "getHierarchyBoundsListeners", null ); // NOI18N
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", br.com.ui.CalendarComboBox.class, "getHierarchyListeners", null ); // NOI18N
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", br.com.ui.CalendarComboBox.class, "getIgnoreRepaint", "setIgnoreRepaint" ); // NOI18N
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", br.com.ui.CalendarComboBox.class, "getInheritsPopupMenu", "setInheritsPopupMenu" ); // NOI18N
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", br.com.ui.CalendarComboBox.class, "getInputContext", null ); // NOI18N
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", br.com.ui.CalendarComboBox.class, "getInputMap", null ); // NOI18N
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", br.com.ui.CalendarComboBox.class, "getInputMethodListeners", null ); // NOI18N
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", br.com.ui.CalendarComboBox.class, "getInputMethodRequests", null ); // NOI18N
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", br.com.ui.CalendarComboBox.class, "getInputVerifier", "setInputVerifier" ); // NOI18N
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", br.com.ui.CalendarComboBox.class, "getInsets", null ); // NOI18N
            properties[PROPERTY_itemAt] = new IndexedPropertyDescriptor ( "itemAt", br.com.ui.CalendarComboBox.class, null, null, "getItemAt", null ); // NOI18N
            properties[PROPERTY_itemCount] = new PropertyDescriptor ( "itemCount", br.com.ui.CalendarComboBox.class, "getItemCount", null ); // NOI18N
            properties[PROPERTY_itemListeners] = new PropertyDescriptor ( "itemListeners", br.com.ui.CalendarComboBox.class, "getItemListeners", null ); // NOI18N
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", br.com.ui.CalendarComboBox.class, "getKeyListeners", null ); // NOI18N
            properties[PROPERTY_keySelectionManager] = new PropertyDescriptor ( "keySelectionManager", br.com.ui.CalendarComboBox.class, "getKeySelectionManager", "setKeySelectionManager" ); // NOI18N
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", br.com.ui.CalendarComboBox.class, "getLayout", "setLayout" ); // NOI18N
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", br.com.ui.CalendarComboBox.class, "isLightweight", null ); // NOI18N
            properties[PROPERTY_lightWeightPopupEnabled] = new PropertyDescriptor ( "lightWeightPopupEnabled", br.com.ui.CalendarComboBox.class, "isLightWeightPopupEnabled", "setLightWeightPopupEnabled" ); // NOI18N
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", br.com.ui.CalendarComboBox.class, "getLocale", "setLocale" ); // NOI18N
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", br.com.ui.CalendarComboBox.class, "getLocation", "setLocation" ); // NOI18N
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", br.com.ui.CalendarComboBox.class, "getLocationOnScreen", null ); // NOI18N
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", br.com.ui.CalendarComboBox.class, "isManagingFocus", null ); // NOI18N
            properties[PROPERTY_maximumRowCount] = new PropertyDescriptor ( "maximumRowCount", br.com.ui.CalendarComboBox.class, "getMaximumRowCount", "setMaximumRowCount" ); // NOI18N
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", br.com.ui.CalendarComboBox.class, "getMaximumSize", "setMaximumSize" ); // NOI18N
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", br.com.ui.CalendarComboBox.class, "isMaximumSizeSet", null ); // NOI18N
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", br.com.ui.CalendarComboBox.class, "getMinimumSize", "setMinimumSize" ); // NOI18N
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", br.com.ui.CalendarComboBox.class, "isMinimumSizeSet", null ); // NOI18N
            properties[PROPERTY_model] = new PropertyDescriptor ( "model", br.com.ui.CalendarComboBox.class, "getModel", "setModel" ); // NOI18N
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", br.com.ui.CalendarComboBox.class, "getMouseListeners", null ); // NOI18N
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", br.com.ui.CalendarComboBox.class, "getMouseMotionListeners", null ); // NOI18N
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", br.com.ui.CalendarComboBox.class, "getMousePosition", null ); // NOI18N
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", br.com.ui.CalendarComboBox.class, "getMouseWheelListeners", null ); // NOI18N
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", br.com.ui.CalendarComboBox.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", br.com.ui.CalendarComboBox.class, "getNextFocusableComponent", "setNextFocusableComponent" ); // NOI18N
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", br.com.ui.CalendarComboBox.class, "isOpaque", "setOpaque" ); // NOI18N
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", br.com.ui.CalendarComboBox.class, "isOptimizedDrawingEnabled", null ); // NOI18N
            properties[PROPERTY_paintingForPrint] = new PropertyDescriptor ( "paintingForPrint", br.com.ui.CalendarComboBox.class, "isPaintingForPrint", null ); // NOI18N
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", br.com.ui.CalendarComboBox.class, "isPaintingTile", null ); // NOI18N
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", br.com.ui.CalendarComboBox.class, "getParent", null ); // NOI18N
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", br.com.ui.CalendarComboBox.class, "getPeer", null ); // NOI18N
            properties[PROPERTY_popupMenuListeners] = new PropertyDescriptor ( "popupMenuListeners", br.com.ui.CalendarComboBox.class, "getPopupMenuListeners", null ); // NOI18N
            properties[PROPERTY_popupVisible] = new PropertyDescriptor ( "popupVisible", br.com.ui.CalendarComboBox.class, "isPopupVisible", "setPopupVisible" ); // NOI18N
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", br.com.ui.CalendarComboBox.class, "getPreferredSize", "setPreferredSize" ); // NOI18N
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", br.com.ui.CalendarComboBox.class, "isPreferredSizeSet", null ); // NOI18N
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", br.com.ui.CalendarComboBox.class, "getPropertyChangeListeners", null ); // NOI18N
            properties[PROPERTY_prototypeDisplayValue] = new PropertyDescriptor ( "prototypeDisplayValue", br.com.ui.CalendarComboBox.class, "getPrototypeDisplayValue", "setPrototypeDisplayValue" ); // NOI18N
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", br.com.ui.CalendarComboBox.class, "getRegisteredKeyStrokes", null ); // NOI18N
            properties[PROPERTY_renderer] = new PropertyDescriptor ( "renderer", br.com.ui.CalendarComboBox.class, "getRenderer", "setRenderer" ); // NOI18N
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", br.com.ui.CalendarComboBox.class, "isRequestFocusEnabled", "setRequestFocusEnabled" ); // NOI18N
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", br.com.ui.CalendarComboBox.class, "getRootPane", null ); // NOI18N
            properties[PROPERTY_selectedIndex] = new PropertyDescriptor ( "selectedIndex", br.com.ui.CalendarComboBox.class, "getSelectedIndex", "setSelectedIndex" ); // NOI18N
            properties[PROPERTY_selectedItem] = new PropertyDescriptor ( "selectedItem", br.com.ui.CalendarComboBox.class, "getSelectedItem", "setSelectedItem" ); // NOI18N
            properties[PROPERTY_selectedObjects] = new PropertyDescriptor ( "selectedObjects", br.com.ui.CalendarComboBox.class, "getSelectedObjects", null ); // NOI18N
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", br.com.ui.CalendarComboBox.class, "isShowing", null ); // NOI18N
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", br.com.ui.CalendarComboBox.class, "getSize", "setSize" ); // NOI18N
            properties[PROPERTY_text] = new PropertyDescriptor ( "text", br.com.ui.CalendarComboBox.class, "getText", null ); // NOI18N
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", br.com.ui.CalendarComboBox.class, "getToolkit", null ); // NOI18N
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", br.com.ui.CalendarComboBox.class, "getToolTipText", "setToolTipText" ); // NOI18N
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", br.com.ui.CalendarComboBox.class, "getTopLevelAncestor", null ); // NOI18N
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", br.com.ui.CalendarComboBox.class, "getTransferHandler", "setTransferHandler" ); // NOI18N
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", br.com.ui.CalendarComboBox.class, "getTreeLock", null ); // NOI18N
            properties[PROPERTY_UI] = new PropertyDescriptor ( "UI", br.com.ui.CalendarComboBox.class, "getUI", "setUI" ); // NOI18N
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", br.com.ui.CalendarComboBox.class, "getUIClassID", null ); // NOI18N
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", br.com.ui.CalendarComboBox.class, "isValid", null ); // NOI18N
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", br.com.ui.CalendarComboBox.class, "isValidateRoot", null ); // NOI18N
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", br.com.ui.CalendarComboBox.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" ); // NOI18N
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", br.com.ui.CalendarComboBox.class, "getVetoableChangeListeners", null ); // NOI18N
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", br.com.ui.CalendarComboBox.class, "isVisible", "setVisible" ); // NOI18N
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", br.com.ui.CalendarComboBox.class, "getVisibleRect", null ); // NOI18N
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", br.com.ui.CalendarComboBox.class, "getWidth", null ); // NOI18N
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", br.com.ui.CalendarComboBox.class, "getX", null ); // NOI18N
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", br.com.ui.CalendarComboBox.class, "getY", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_actionListener = 0;
    private static final int EVENT_ancestorListener = 1;
    private static final int EVENT_componentListener = 2;
    private static final int EVENT_containerListener = 3;
    private static final int EVENT_focusListener = 4;
    private static final int EVENT_hierarchyBoundsListener = 5;
    private static final int EVENT_hierarchyListener = 6;
    private static final int EVENT_inputMethodListener = 7;
    private static final int EVENT_itemListener = 8;
    private static final int EVENT_keyListener = 9;
    private static final int EVENT_mouseListener = 10;
    private static final int EVENT_mouseMotionListener = 11;
    private static final int EVENT_mouseWheelListener = 12;
    private static final int EVENT_popupMenuListener = 13;
    private static final int EVENT_propertyChangeListener = 14;
    private static final int EVENT_vetoableChangeListener = 15;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[16];
    
        try {
            eventSets[EVENT_actionListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "actionListener", java.awt.event.ActionListener.class, new String[] {"actionPerformed"}, "addActionListener", "removeActionListener" ); // NOI18N
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorRemoved", "ancestorMoved"}, "addAncestorListener", "removeAncestorListener" ); // NOI18N
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentResized", "componentMoved", "componentShown", "componentHidden"}, "addComponentListener", "removeComponentListener" ); // NOI18N
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" ); // NOI18N
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" ); // NOI18N
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" ); // NOI18N
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" ); // NOI18N
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener" ); // NOI18N
            eventSets[EVENT_itemListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "itemListener", java.awt.event.ItemListener.class, new String[] {"itemStateChanged"}, "addItemListener", "removeItemListener" ); // NOI18N
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyTyped", "keyPressed", "keyReleased"}, "addKeyListener", "removeKeyListener" ); // NOI18N
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"}, "addMouseListener", "removeMouseListener" ); // NOI18N
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" ); // NOI18N
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" ); // NOI18N
            eventSets[EVENT_popupMenuListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "popupMenuListener", javax.swing.event.PopupMenuListener.class, new String[] {"popupMenuWillBecomeVisible", "popupMenuWillBecomeInvisible", "popupMenuCanceled"}, "addPopupMenuListener", "removePopupMenuListener" ); // NOI18N
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( br.com.ui.CalendarComboBox.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_action0 = 0;
    private static final int METHOD_actionPerformed1 = 1;
    private static final int METHOD_add2 = 2;
    private static final int METHOD_add3 = 3;
    private static final int METHOD_add4 = 4;
    private static final int METHOD_add5 = 5;
    private static final int METHOD_add6 = 6;
    private static final int METHOD_add7 = 7;
    private static final int METHOD_addItem8 = 8;
    private static final int METHOD_addNotify9 = 9;
    private static final int METHOD_addPropertyChangeListener10 = 10;
    private static final int METHOD_applyComponentOrientation11 = 11;
    private static final int METHOD_areFocusTraversalKeysSet12 = 12;
    private static final int METHOD_bounds13 = 13;
    private static final int METHOD_checkImage14 = 14;
    private static final int METHOD_checkImage15 = 15;
    private static final int METHOD_computeVisibleRect16 = 16;
    private static final int METHOD_configureEditor17 = 17;
    private static final int METHOD_contains18 = 18;
    private static final int METHOD_contains19 = 19;
    private static final int METHOD_contentsChanged20 = 20;
    private static final int METHOD_countComponents21 = 21;
    private static final int METHOD_createImage22 = 22;
    private static final int METHOD_createImage23 = 23;
    private static final int METHOD_createToolTip24 = 24;
    private static final int METHOD_createVolatileImage25 = 25;
    private static final int METHOD_createVolatileImage26 = 26;
    private static final int METHOD_deliverEvent27 = 27;
    private static final int METHOD_disable28 = 28;
    private static final int METHOD_dispatchEvent29 = 29;
    private static final int METHOD_doLayout30 = 30;
    private static final int METHOD_enable31 = 31;
    private static final int METHOD_enable32 = 32;
    private static final int METHOD_enableInputMethods33 = 33;
    private static final int METHOD_findComponentAt34 = 34;
    private static final int METHOD_findComponentAt35 = 35;
    private static final int METHOD_firePopupMenuCanceled36 = 36;
    private static final int METHOD_firePopupMenuWillBecomeInvisible37 = 37;
    private static final int METHOD_firePopupMenuWillBecomeVisible38 = 38;
    private static final int METHOD_firePropertyChange39 = 39;
    private static final int METHOD_firePropertyChange40 = 40;
    private static final int METHOD_firePropertyChange41 = 41;
    private static final int METHOD_firePropertyChange42 = 42;
    private static final int METHOD_firePropertyChange43 = 43;
    private static final int METHOD_firePropertyChange44 = 44;
    private static final int METHOD_firePropertyChange45 = 45;
    private static final int METHOD_firePropertyChange46 = 46;
    private static final int METHOD_getActionForKeyStroke47 = 47;
    private static final int METHOD_getBaseline48 = 48;
    private static final int METHOD_getBounds49 = 49;
    private static final int METHOD_getClientProperty50 = 50;
    private static final int METHOD_getComponentAt51 = 51;
    private static final int METHOD_getComponentAt52 = 52;
    private static final int METHOD_getComponentZOrder53 = 53;
    private static final int METHOD_getConditionForKeyStroke54 = 54;
    private static final int METHOD_getDefaultLocale55 = 55;
    private static final int METHOD_getFocusTraversalKeys56 = 56;
    private static final int METHOD_getFontMetrics57 = 57;
    private static final int METHOD_getInsets58 = 58;
    private static final int METHOD_getListeners59 = 59;
    private static final int METHOD_getLocation60 = 60;
    private static final int METHOD_getMousePosition61 = 61;
    private static final int METHOD_getPopupLocation62 = 62;
    private static final int METHOD_getPropertyChangeListeners63 = 63;
    private static final int METHOD_getSize64 = 64;
    private static final int METHOD_getToolTipLocation65 = 65;
    private static final int METHOD_getToolTipText66 = 66;
    private static final int METHOD_gotFocus67 = 67;
    private static final int METHOD_grabFocus68 = 68;
    private static final int METHOD_handleEvent69 = 69;
    private static final int METHOD_hasFocus70 = 70;
    private static final int METHOD_hasValidDate71 = 71;
    private static final int METHOD_hide72 = 72;
    private static final int METHOD_hidePopup73 = 73;
    private static final int METHOD_imageUpdate74 = 74;
    private static final int METHOD_insertItemAt75 = 75;
    private static final int METHOD_insets76 = 76;
    private static final int METHOD_inside77 = 77;
    private static final int METHOD_intervalAdded78 = 78;
    private static final int METHOD_intervalRemoved79 = 79;
    private static final int METHOD_invalidate80 = 80;
    private static final int METHOD_isAncestorOf81 = 81;
    private static final int METHOD_isFocusCycleRoot82 = 82;
    private static final int METHOD_isLightweightComponent83 = 83;
    private static final int METHOD_keyDown84 = 84;
    private static final int METHOD_keyUp85 = 85;
    private static final int METHOD_layout86 = 86;
    private static final int METHOD_list87 = 87;
    private static final int METHOD_list88 = 88;
    private static final int METHOD_list89 = 89;
    private static final int METHOD_list90 = 90;
    private static final int METHOD_list91 = 91;
    private static final int METHOD_locate92 = 92;
    private static final int METHOD_location93 = 93;
    private static final int METHOD_lostFocus94 = 94;
    private static final int METHOD_minimumSize95 = 95;
    private static final int METHOD_mouseDown96 = 96;
    private static final int METHOD_mouseDrag97 = 97;
    private static final int METHOD_mouseEnter98 = 98;
    private static final int METHOD_mouseExit99 = 99;
    private static final int METHOD_mouseMove100 = 100;
    private static final int METHOD_mouseUp101 = 101;
    private static final int METHOD_move102 = 102;
    private static final int METHOD_nextFocus103 = 103;
    private static final int METHOD_paint104 = 104;
    private static final int METHOD_paintAll105 = 105;
    private static final int METHOD_paintComponents106 = 106;
    private static final int METHOD_paintImmediately107 = 107;
    private static final int METHOD_paintImmediately108 = 108;
    private static final int METHOD_postEvent109 = 109;
    private static final int METHOD_preferredSize110 = 110;
    private static final int METHOD_prepareImage111 = 111;
    private static final int METHOD_prepareImage112 = 112;
    private static final int METHOD_print113 = 113;
    private static final int METHOD_printAll114 = 114;
    private static final int METHOD_printComponents115 = 115;
    private static final int METHOD_processKeyEvent116 = 116;
    private static final int METHOD_putClientProperty117 = 117;
    private static final int METHOD_registerKeyboardAction118 = 118;
    private static final int METHOD_registerKeyboardAction119 = 119;
    private static final int METHOD_remove120 = 120;
    private static final int METHOD_remove121 = 121;
    private static final int METHOD_remove122 = 122;
    private static final int METHOD_removeAll123 = 123;
    private static final int METHOD_removeAllItems124 = 124;
    private static final int METHOD_removeItem125 = 125;
    private static final int METHOD_removeItemAt126 = 126;
    private static final int METHOD_removeNotify127 = 127;
    private static final int METHOD_removePropertyChangeListener128 = 128;
    private static final int METHOD_repaint129 = 129;
    private static final int METHOD_repaint130 = 130;
    private static final int METHOD_repaint131 = 131;
    private static final int METHOD_repaint132 = 132;
    private static final int METHOD_repaint133 = 133;
    private static final int METHOD_requestDefaultFocus134 = 134;
    private static final int METHOD_requestFocus135 = 135;
    private static final int METHOD_requestFocus136 = 136;
    private static final int METHOD_requestFocusInWindow137 = 137;
    private static final int METHOD_resetKeyboardActions138 = 138;
    private static final int METHOD_reshape139 = 139;
    private static final int METHOD_resize140 = 140;
    private static final int METHOD_resize141 = 141;
    private static final int METHOD_revalidate142 = 142;
    private static final int METHOD_scrollRectToVisible143 = 143;
    private static final int METHOD_selectWithKeyChar144 = 144;
    private static final int METHOD_setBounds145 = 145;
    private static final int METHOD_setComponentZOrder146 = 146;
    private static final int METHOD_setDefaultLocale147 = 147;
    private static final int METHOD_show148 = 148;
    private static final int METHOD_show149 = 149;
    private static final int METHOD_showPopup150 = 150;
    private static final int METHOD_size151 = 151;
    private static final int METHOD_toString152 = 152;
    private static final int METHOD_transferFocus153 = 153;
    private static final int METHOD_transferFocusBackward154 = 154;
    private static final int METHOD_transferFocusDownCycle155 = 155;
    private static final int METHOD_transferFocusUpCycle156 = 156;
    private static final int METHOD_unregisterKeyboardAction157 = 157;
    private static final int METHOD_update158 = 158;
    private static final int METHOD_updateUI159 = 159;
    private static final int METHOD_validate160 = 160;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[161];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor(java.awt.Component.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_actionPerformed1] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("actionPerformed", new Class[] {java.awt.event.ActionEvent.class})); // NOI18N
            methods[METHOD_actionPerformed1].setDisplayName ( "" );
            methods[METHOD_add2] = new MethodDescriptor(java.awt.Component.class.getMethod("add", new Class[] {java.awt.PopupMenu.class})); // NOI18N
            methods[METHOD_add2].setDisplayName ( "" );
            methods[METHOD_add3] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_add3].setDisplayName ( "" );
            methods[METHOD_add4] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.lang.String.class, java.awt.Component.class})); // NOI18N
            methods[METHOD_add4].setDisplayName ( "" );
            methods[METHOD_add5] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_add5].setDisplayName ( "" );
            methods[METHOD_add6] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_add6].setDisplayName ( "" );
            methods[METHOD_add7] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_add7].setDisplayName ( "" );
            methods[METHOD_addItem8] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("addItem", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_addItem8].setDisplayName ( "" );
            methods[METHOD_addNotify9] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("addNotify", new Class[] {})); // NOI18N
            methods[METHOD_addNotify9].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener10] = new MethodDescriptor(java.awt.Container.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_addPropertyChangeListener10].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation11] = new MethodDescriptor(java.awt.Container.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class})); // NOI18N
            methods[METHOD_applyComponentOrientation11].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet12] = new MethodDescriptor(java.awt.Container.class.getMethod("areFocusTraversalKeysSet", new Class[] {int.class})); // NOI18N
            methods[METHOD_areFocusTraversalKeysSet12].setDisplayName ( "" );
            methods[METHOD_bounds13] = new MethodDescriptor(java.awt.Component.class.getMethod("bounds", new Class[] {})); // NOI18N
            methods[METHOD_bounds13].setDisplayName ( "" );
            methods[METHOD_checkImage14] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage14].setDisplayName ( "" );
            methods[METHOD_checkImage15] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage15].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect16] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_computeVisibleRect16].setDisplayName ( "" );
            methods[METHOD_configureEditor17] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("configureEditor", new Class[] {javax.swing.ComboBoxEditor.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_configureEditor17].setDisplayName ( "" );
            methods[METHOD_contains18] = new MethodDescriptor(java.awt.Component.class.getMethod("contains", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_contains18].setDisplayName ( "" );
            methods[METHOD_contains19] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("contains", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_contains19].setDisplayName ( "" );
            methods[METHOD_contentsChanged20] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("contentsChanged", new Class[] {javax.swing.event.ListDataEvent.class})); // NOI18N
            methods[METHOD_contentsChanged20].setDisplayName ( "" );
            methods[METHOD_countComponents21] = new MethodDescriptor(java.awt.Container.class.getMethod("countComponents", new Class[] {})); // NOI18N
            methods[METHOD_countComponents21].setDisplayName ( "" );
            methods[METHOD_createImage22] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class})); // NOI18N
            methods[METHOD_createImage22].setDisplayName ( "" );
            methods[METHOD_createImage23] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createImage23].setDisplayName ( "" );
            methods[METHOD_createToolTip24] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("createToolTip", new Class[] {})); // NOI18N
            methods[METHOD_createToolTip24].setDisplayName ( "" );
            methods[METHOD_createVolatileImage25] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createVolatileImage25].setDisplayName ( "" );
            methods[METHOD_createVolatileImage26] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class, java.awt.ImageCapabilities.class})); // NOI18N
            methods[METHOD_createVolatileImage26].setDisplayName ( "" );
            methods[METHOD_deliverEvent27] = new MethodDescriptor(java.awt.Container.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_deliverEvent27].setDisplayName ( "" );
            methods[METHOD_disable28] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("disable", new Class[] {})); // NOI18N
            methods[METHOD_disable28].setDisplayName ( "" );
            methods[METHOD_dispatchEvent29] = new MethodDescriptor(java.awt.Component.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class})); // NOI18N
            methods[METHOD_dispatchEvent29].setDisplayName ( "" );
            methods[METHOD_doLayout30] = new MethodDescriptor(java.awt.Container.class.getMethod("doLayout", new Class[] {})); // NOI18N
            methods[METHOD_doLayout30].setDisplayName ( "" );
            methods[METHOD_enable31] = new MethodDescriptor(java.awt.Component.class.getMethod("enable", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enable31].setDisplayName ( "" );
            methods[METHOD_enable32] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("enable", new Class[] {})); // NOI18N
            methods[METHOD_enable32].setDisplayName ( "" );
            methods[METHOD_enableInputMethods33] = new MethodDescriptor(java.awt.Component.class.getMethod("enableInputMethods", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enableInputMethods33].setDisplayName ( "" );
            methods[METHOD_findComponentAt34] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_findComponentAt34].setDisplayName ( "" );
            methods[METHOD_findComponentAt35] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_findComponentAt35].setDisplayName ( "" );
            methods[METHOD_firePopupMenuCanceled36] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("firePopupMenuCanceled", new Class[] {})); // NOI18N
            methods[METHOD_firePopupMenuCanceled36].setDisplayName ( "" );
            methods[METHOD_firePopupMenuWillBecomeInvisible37] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("firePopupMenuWillBecomeInvisible", new Class[] {})); // NOI18N
            methods[METHOD_firePopupMenuWillBecomeInvisible37].setDisplayName ( "" );
            methods[METHOD_firePopupMenuWillBecomeVisible38] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("firePopupMenuWillBecomeVisible", new Class[] {})); // NOI18N
            methods[METHOD_firePopupMenuWillBecomeVisible38].setDisplayName ( "" );
            methods[METHOD_firePropertyChange39] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, byte.class, byte.class})); // NOI18N
            methods[METHOD_firePropertyChange39].setDisplayName ( "" );
            methods[METHOD_firePropertyChange40] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, short.class, short.class})); // NOI18N
            methods[METHOD_firePropertyChange40].setDisplayName ( "" );
            methods[METHOD_firePropertyChange41] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, long.class, long.class})); // NOI18N
            methods[METHOD_firePropertyChange41].setDisplayName ( "" );
            methods[METHOD_firePropertyChange42] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, float.class, float.class})); // NOI18N
            methods[METHOD_firePropertyChange42].setDisplayName ( "" );
            methods[METHOD_firePropertyChange43] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, double.class, double.class})); // NOI18N
            methods[METHOD_firePropertyChange43].setDisplayName ( "" );
            methods[METHOD_firePropertyChange44] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_firePropertyChange44].setDisplayName ( "" );
            methods[METHOD_firePropertyChange45] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, int.class, int.class})); // NOI18N
            methods[METHOD_firePropertyChange45].setDisplayName ( "" );
            methods[METHOD_firePropertyChange46] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, char.class, char.class})); // NOI18N
            methods[METHOD_firePropertyChange46].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke47] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getActionForKeyStroke47].setDisplayName ( "" );
            methods[METHOD_getBaseline48] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBaseline", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getBaseline48].setDisplayName ( "" );
            methods[METHOD_getBounds49] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_getBounds49].setDisplayName ( "" );
            methods[METHOD_getClientProperty50] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_getClientProperty50].setDisplayName ( "" );
            methods[METHOD_getComponentAt51] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getComponentAt51].setDisplayName ( "" );
            methods[METHOD_getComponentAt52] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getComponentAt52].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder53] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_getComponentZOrder53].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke54] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getConditionForKeyStroke54].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale55] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getDefaultLocale", new Class[] {})); // NOI18N
            methods[METHOD_getDefaultLocale55].setDisplayName ( "" );
            methods[METHOD_getFocusTraversalKeys56] = new MethodDescriptor(java.awt.Container.class.getMethod("getFocusTraversalKeys", new Class[] {int.class})); // NOI18N
            methods[METHOD_getFocusTraversalKeys56].setDisplayName ( "" );
            methods[METHOD_getFontMetrics57] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class})); // NOI18N
            methods[METHOD_getFontMetrics57].setDisplayName ( "" );
            methods[METHOD_getInsets58] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getInsets", new Class[] {java.awt.Insets.class})); // NOI18N
            methods[METHOD_getInsets58].setDisplayName ( "" );
            methods[METHOD_getListeners59] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getListeners", new Class[] {java.lang.Class.class})); // NOI18N
            methods[METHOD_getListeners59].setDisplayName ( "" );
            methods[METHOD_getLocation60] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getLocation", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getLocation60].setDisplayName ( "" );
            methods[METHOD_getMousePosition61] = new MethodDescriptor(java.awt.Container.class.getMethod("getMousePosition", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_getMousePosition61].setDisplayName ( "" );
            methods[METHOD_getPopupLocation62] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getPopupLocation62].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners63] = new MethodDescriptor(java.awt.Component.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getPropertyChangeListeners63].setDisplayName ( "" );
            methods[METHOD_getSize64] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getSize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_getSize64].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation65] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipLocation65].setDisplayName ( "" );
            methods[METHOD_getToolTipText66] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipText66].setDisplayName ( "" );
            methods[METHOD_gotFocus67] = new MethodDescriptor(java.awt.Component.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_gotFocus67].setDisplayName ( "" );
            methods[METHOD_grabFocus68] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("grabFocus", new Class[] {})); // NOI18N
            methods[METHOD_grabFocus68].setDisplayName ( "" );
            methods[METHOD_handleEvent69] = new MethodDescriptor(java.awt.Component.class.getMethod("handleEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_handleEvent69].setDisplayName ( "" );
            methods[METHOD_hasFocus70] = new MethodDescriptor(java.awt.Component.class.getMethod("hasFocus", new Class[] {})); // NOI18N
            methods[METHOD_hasFocus70].setDisplayName ( "" );
            methods[METHOD_hasValidDate71] = new MethodDescriptor(br.com.ui.CalendarComboBox.class.getMethod("hasValidDate", new Class[] {})); // NOI18N
            methods[METHOD_hasValidDate71].setDisplayName ( "" );
            methods[METHOD_hide72] = new MethodDescriptor(java.awt.Component.class.getMethod("hide", new Class[] {})); // NOI18N
            methods[METHOD_hide72].setDisplayName ( "" );
            methods[METHOD_hidePopup73] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("hidePopup", new Class[] {})); // NOI18N
            methods[METHOD_hidePopup73].setDisplayName ( "" );
            methods[METHOD_imageUpdate74] = new MethodDescriptor(java.awt.Component.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, int.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_imageUpdate74].setDisplayName ( "" );
            methods[METHOD_insertItemAt75] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("insertItemAt", new Class[] {java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_insertItemAt75].setDisplayName ( "" );
            methods[METHOD_insets76] = new MethodDescriptor(java.awt.Container.class.getMethod("insets", new Class[] {})); // NOI18N
            methods[METHOD_insets76].setDisplayName ( "" );
            methods[METHOD_inside77] = new MethodDescriptor(java.awt.Component.class.getMethod("inside", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_inside77].setDisplayName ( "" );
            methods[METHOD_intervalAdded78] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("intervalAdded", new Class[] {javax.swing.event.ListDataEvent.class})); // NOI18N
            methods[METHOD_intervalAdded78].setDisplayName ( "" );
            methods[METHOD_intervalRemoved79] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("intervalRemoved", new Class[] {javax.swing.event.ListDataEvent.class})); // NOI18N
            methods[METHOD_intervalRemoved79].setDisplayName ( "" );
            methods[METHOD_invalidate80] = new MethodDescriptor(java.awt.Container.class.getMethod("invalidate", new Class[] {})); // NOI18N
            methods[METHOD_invalidate80].setDisplayName ( "" );
            methods[METHOD_isAncestorOf81] = new MethodDescriptor(java.awt.Container.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isAncestorOf81].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot82] = new MethodDescriptor(java.awt.Container.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class})); // NOI18N
            methods[METHOD_isFocusCycleRoot82].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent83] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isLightweightComponent83].setDisplayName ( "" );
            methods[METHOD_keyDown84] = new MethodDescriptor(java.awt.Component.class.getMethod("keyDown", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyDown84].setDisplayName ( "" );
            methods[METHOD_keyUp85] = new MethodDescriptor(java.awt.Component.class.getMethod("keyUp", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyUp85].setDisplayName ( "" );
            methods[METHOD_layout86] = new MethodDescriptor(java.awt.Container.class.getMethod("layout", new Class[] {})); // NOI18N
            methods[METHOD_layout86].setDisplayName ( "" );
            methods[METHOD_list87] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {})); // NOI18N
            methods[METHOD_list87].setDisplayName ( "" );
            methods[METHOD_list88] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintStream.class})); // NOI18N
            methods[METHOD_list88].setDisplayName ( "" );
            methods[METHOD_list89] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintWriter.class})); // NOI18N
            methods[METHOD_list89].setDisplayName ( "" );
            methods[METHOD_list90] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintStream.class, int.class})); // NOI18N
            methods[METHOD_list90].setDisplayName ( "" );
            methods[METHOD_list91] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintWriter.class, int.class})); // NOI18N
            methods[METHOD_list91].setDisplayName ( "" );
            methods[METHOD_locate92] = new MethodDescriptor(java.awt.Container.class.getMethod("locate", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_locate92].setDisplayName ( "" );
            methods[METHOD_location93] = new MethodDescriptor(java.awt.Component.class.getMethod("location", new Class[] {})); // NOI18N
            methods[METHOD_location93].setDisplayName ( "" );
            methods[METHOD_lostFocus94] = new MethodDescriptor(java.awt.Component.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_lostFocus94].setDisplayName ( "" );
            methods[METHOD_minimumSize95] = new MethodDescriptor(java.awt.Container.class.getMethod("minimumSize", new Class[] {})); // NOI18N
            methods[METHOD_minimumSize95].setDisplayName ( "" );
            methods[METHOD_mouseDown96] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDown96].setDisplayName ( "" );
            methods[METHOD_mouseDrag97] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDrag97].setDisplayName ( "" );
            methods[METHOD_mouseEnter98] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseEnter98].setDisplayName ( "" );
            methods[METHOD_mouseExit99] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseExit99].setDisplayName ( "" );
            methods[METHOD_mouseMove100] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseMove100].setDisplayName ( "" );
            methods[METHOD_mouseUp101] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseUp101].setDisplayName ( "" );
            methods[METHOD_move102] = new MethodDescriptor(java.awt.Component.class.getMethod("move", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_move102].setDisplayName ( "" );
            methods[METHOD_nextFocus103] = new MethodDescriptor(java.awt.Component.class.getMethod("nextFocus", new Class[] {})); // NOI18N
            methods[METHOD_nextFocus103].setDisplayName ( "" );
            methods[METHOD_paint104] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paint", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paint104].setDisplayName ( "" );
            methods[METHOD_paintAll105] = new MethodDescriptor(java.awt.Component.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintAll105].setDisplayName ( "" );
            methods[METHOD_paintComponents106] = new MethodDescriptor(java.awt.Container.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintComponents106].setDisplayName ( "" );
            methods[METHOD_paintImmediately107] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_paintImmediately107].setDisplayName ( "" );
            methods[METHOD_paintImmediately108] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_paintImmediately108].setDisplayName ( "" );
            methods[METHOD_postEvent109] = new MethodDescriptor(java.awt.Component.class.getMethod("postEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_postEvent109].setDisplayName ( "" );
            methods[METHOD_preferredSize110] = new MethodDescriptor(java.awt.Container.class.getMethod("preferredSize", new Class[] {})); // NOI18N
            methods[METHOD_preferredSize110].setDisplayName ( "" );
            methods[METHOD_prepareImage111] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage111].setDisplayName ( "" );
            methods[METHOD_prepareImage112] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage112].setDisplayName ( "" );
            methods[METHOD_print113] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("print", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_print113].setDisplayName ( "" );
            methods[METHOD_printAll114] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("printAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printAll114].setDisplayName ( "" );
            methods[METHOD_printComponents115] = new MethodDescriptor(java.awt.Container.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printComponents115].setDisplayName ( "" );
            methods[METHOD_processKeyEvent116] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("processKeyEvent", new Class[] {java.awt.event.KeyEvent.class})); // NOI18N
            methods[METHOD_processKeyEvent116].setDisplayName ( "" );
            methods[METHOD_putClientProperty117] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_putClientProperty117].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction118] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction118].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction119] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction119].setDisplayName ( "" );
            methods[METHOD_remove120] = new MethodDescriptor(java.awt.Component.class.getMethod("remove", new Class[] {java.awt.MenuComponent.class})); // NOI18N
            methods[METHOD_remove120].setDisplayName ( "" );
            methods[METHOD_remove121] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {int.class})); // NOI18N
            methods[METHOD_remove121].setDisplayName ( "" );
            methods[METHOD_remove122] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_remove122].setDisplayName ( "" );
            methods[METHOD_removeAll123] = new MethodDescriptor(java.awt.Container.class.getMethod("removeAll", new Class[] {})); // NOI18N
            methods[METHOD_removeAll123].setDisplayName ( "" );
            methods[METHOD_removeAllItems124] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("removeAllItems", new Class[] {})); // NOI18N
            methods[METHOD_removeAllItems124].setDisplayName ( "" );
            methods[METHOD_removeItem125] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("removeItem", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_removeItem125].setDisplayName ( "" );
            methods[METHOD_removeItemAt126] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("removeItemAt", new Class[] {int.class})); // NOI18N
            methods[METHOD_removeItemAt126].setDisplayName ( "" );
            methods[METHOD_removeNotify127] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("removeNotify", new Class[] {})); // NOI18N
            methods[METHOD_removeNotify127].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener128] = new MethodDescriptor(java.awt.Component.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_removePropertyChangeListener128].setDisplayName ( "" );
            methods[METHOD_repaint129] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {})); // NOI18N
            methods[METHOD_repaint129].setDisplayName ( "" );
            methods[METHOD_repaint130] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {long.class})); // NOI18N
            methods[METHOD_repaint130].setDisplayName ( "" );
            methods[METHOD_repaint131] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint131].setDisplayName ( "" );
            methods[METHOD_repaint132] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {long.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint132].setDisplayName ( "" );
            methods[METHOD_repaint133] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_repaint133].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus134] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestDefaultFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestDefaultFocus134].setDisplayName ( "" );
            methods[METHOD_requestFocus135] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestFocus135].setDisplayName ( "" );
            methods[METHOD_requestFocus136] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_requestFocus136].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow137] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocusInWindow", new Class[] {})); // NOI18N
            methods[METHOD_requestFocusInWindow137].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions138] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("resetKeyboardActions", new Class[] {})); // NOI18N
            methods[METHOD_resetKeyboardActions138].setDisplayName ( "" );
            methods[METHOD_reshape139] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("reshape", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_reshape139].setDisplayName ( "" );
            methods[METHOD_resize140] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_resize140].setDisplayName ( "" );
            methods[METHOD_resize141] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_resize141].setDisplayName ( "" );
            methods[METHOD_revalidate142] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("revalidate", new Class[] {})); // NOI18N
            methods[METHOD_revalidate142].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible143] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_scrollRectToVisible143].setDisplayName ( "" );
            methods[METHOD_selectWithKeyChar144] = new MethodDescriptor(br.com.ui.CalendarComboBox.class.getMethod("selectWithKeyChar", new Class[] {char.class})); // NOI18N
            methods[METHOD_selectWithKeyChar144].setDisplayName ( "" );
            methods[METHOD_setBounds145] = new MethodDescriptor(java.awt.Component.class.getMethod("setBounds", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_setBounds145].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder146] = new MethodDescriptor(java.awt.Container.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_setComponentZOrder146].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale147] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class})); // NOI18N
            methods[METHOD_setDefaultLocale147].setDisplayName ( "" );
            methods[METHOD_show148] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {})); // NOI18N
            methods[METHOD_show148].setDisplayName ( "" );
            methods[METHOD_show149] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_show149].setDisplayName ( "" );
            methods[METHOD_showPopup150] = new MethodDescriptor(javax.swing.JComboBox.class.getMethod("showPopup", new Class[] {})); // NOI18N
            methods[METHOD_showPopup150].setDisplayName ( "" );
            methods[METHOD_size151] = new MethodDescriptor(java.awt.Component.class.getMethod("size", new Class[] {})); // NOI18N
            methods[METHOD_size151].setDisplayName ( "" );
            methods[METHOD_toString152] = new MethodDescriptor(java.awt.Component.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString152].setDisplayName ( "" );
            methods[METHOD_transferFocus153] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocus", new Class[] {})); // NOI18N
            methods[METHOD_transferFocus153].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward154] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusBackward", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusBackward154].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle155] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusDownCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusDownCycle155].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle156] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocusUpCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusUpCycle156].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction157] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_unregisterKeyboardAction157].setDisplayName ( "" );
            methods[METHOD_update158] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("update", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_update158].setDisplayName ( "" );
            methods[METHOD_updateUI159] = new MethodDescriptor(br.com.ui.CalendarComboBox.class.getMethod("updateUI", new Class[] {})); // NOI18N
            methods[METHOD_updateUI159].setDisplayName ( "" );
            methods[METHOD_validate160] = new MethodDescriptor(java.awt.Container.class.getMethod("validate", new Class[] {})); // NOI18N
            methods[METHOD_validate160].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

    // Here you can add code for customizing the methods array.
    
        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

    
//GEN-FIRST:Superclass

    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
	
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     * 
     * @return BeanDescriptor describing the editable
     * properties of this bean.  May return null if the
     * information should be obtained by automatic analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
	return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     * 
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean.  May return null if the
     * information should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will
     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.
     * A client of getPropertyDescriptors can use "instanceof" to check
     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
	return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     * 
     * @return  An array of EventSetDescriptors describing the kinds of 
     * events fired by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
	return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     * 
     * @return  An array of MethodDescriptors describing the methods 
     * implemented by this bean.  May return null if the information
     * should be obtained by automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
	return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are 
     * customizing the bean.
     * @return  Index of default property in the PropertyDescriptor array
     * 		returned by getPropertyDescriptors.
     * <P>	Returns -1 if there is no default property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will
     * mostly commonly be used by human's when using the bean. 
     * @return Index of default event in the EventSetDescriptor array
     *		returned by getEventSetDescriptors.
     * <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to
     * represent the bean in toolboxes, toolbars, etc.   Icon images
     * will typically be GIFs, but may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from
     * this method.
     * <p>
     * There are four possible flavors of icons (16x16 color,
     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only
     * support a single icon we recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background
     * so they can be rendered onto an existing background.
     *
     * @param  iconKind  The kind of icon requested.  This should be
     *    one of the constant values ICON_COLOR_16x16, ICON_COLOR_32x32, 
     *    ICON_MONO_16x16, or ICON_MONO_32x32.
     * @return  An image object representing the requested icon.  May
     *    return null if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch ( iconKind ) {
        case ICON_COLOR_16x16:
            if ( iconNameC16 == null )
                return null;
            else {
                if( iconColor16 == null )
                    iconColor16 = loadImage( iconNameC16 );
                return iconColor16;
            }
        case ICON_COLOR_32x32:
            if ( iconNameC32 == null )
                return null;
            else {
                if( iconColor32 == null )
                    iconColor32 = loadImage( iconNameC32 );
                return iconColor32;
            }
        case ICON_MONO_16x16:
            if ( iconNameM16 == null )
                return null;
            else {
                if( iconMono16 == null )
                    iconMono16 = loadImage( iconNameM16 );
                return iconMono16;
            }
        case ICON_MONO_32x32:
            if ( iconNameM32 == null )
                return null;
            else {
                if( iconMono32 == null )
                    iconMono32 = loadImage( iconNameM32 );
                return iconMono32;
            }
	default: return null;
        }
    }

}

