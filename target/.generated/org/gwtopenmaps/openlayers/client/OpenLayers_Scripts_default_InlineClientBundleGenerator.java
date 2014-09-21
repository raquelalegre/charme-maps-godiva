package org.gwtopenmaps.openlayers.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ResourcePrototype;

public class OpenLayers_Scripts_default_InlineClientBundleGenerator implements org.gwtopenmaps.openlayers.client.OpenLayers.Scripts {
  private static OpenLayers_Scripts_default_InlineClientBundleGenerator _instance0 = new OpenLayers_Scripts_default_InlineClientBundleGenerator();
  private void scriptUtilInitializer() {
    scriptUtil = new com.google.gwt.resources.client.TextResource() {
      // jar:file:/home/raquel/.m2/repository/org/gwtopenmaps/openlayers/gwt-openlayers-client/1.0/gwt-openlayers-client-1.0.jar!/org/gwtopenmaps/openlayers/client/js/gwt-openlayers/util.js
      public String getText() {
        return "/*\n * Utility class with helper functions and workarounds for problematic JSNI cases.\n *\n * gwt_openlayers_util.relay\n *   Relays calls to OpenLayers functions that taken an Array parameter\n *   and perform an instanceof test for the type Array, and possible for other\n *   corner cases.\n *\n * @author\n */\n\n//make namespacing object\nif(!gwt_openlayers_util){ var gwt_openlayers_util = new Object(); }\n\n\n//function for converting Object to Array\ngwt_openlayers_util.convertToArray = function(o){\n	var a = new Array();\n	for(var i = 0, m = o.length; i < m; i++){\n		a[i] = o[i];\n	}\n	return a;\n}\n\n//function for converting JSNI created javascript Object to plain javascript Object\n//GWT created javascript objects fail instanceof Object test\n//  TO DO make recursive\ngwt_openlayers_util.convertToPlainObject = function(o){\n	var obj = new Object();\n	for(prop in o){\n		var x = prop;\n	}\n	return obj;\n}\n\n//to get around the test: if(this.eventListeners instanceof Object){ ...}\ngwt_openlayers_util.eventListenersToObject = function(options){\n	if(options.eventListeners){\n		var obj = new Object();\n		for(i in options.eventListeners){\n			alert(i)\n			obj[i] = options.eventListeners[i]\n		}\n		options.eventListeners = new Object();\n		for(j in obj){\n			options.eventListeners[j] = obj[j]\n		}\n	}\n	return options;\n}\n\n//relay functions\n//TODO move these functions to JSNI methods on the Impl classes\ngwt_openlayers_util.relay = {\n\n	/* copy, paste directly below here, adjust name of function, and specify body\n		exampleRelayFnt : function(o){\n	},\n	*/\n\n	//paste new relay function here\n\n	writeArray : function(format, o){\n		return format.write(gwt_openlayers_util.convertToArray(o));\n	},\n\n	vectorAddFeatures : function(vector, o){\n		vector.addFeatures(gwt_openlayers_util.convertToArray(o));\n	}\n\n}\n\n/**\n * For performing output sanitization on strings that are inserted into the html dom at runtime.\n *\n * This is to prevent XSS. Sanitization should only be done on input that\n * can be inserted by \"users\" in some way. If an attacker can hijack javascript\n * methods that take input html than sanitization is not relevant anymore.\n *\n * Output sanitization should always be based on a whitelist.\n */\ngwt_openlayers_util.sanitize = function(input){\n	var originalInput = input;\n	var whiteListRegEx = [\n		new RegExp(\"<b>\", \"g\"),\n		new RegExp(\"</b>\", \"g\"),\n		new RegExp(\"<em>\", \"g\"),\n		new RegExp(\"</em>\", \"g\"),\n		new RegExp(\"<bdo dir=\\\"rtl\\\">\", \"g\"),\n		new RegExp(\"<bdo dir=\\\"ltr\\\">\", \"g\"),\n		new RegExp(\"</bdo>\", \"g\")\n	];\n\n	for(var i = 0, max = whiteListRegEx.length; i < max; i++){\n		input = input.replace(whiteListRegEx[i], \"\")\n	}\n\n	var whiteListCharsRegEx = /^[a-zA-Z0-9\\.\\s\",';:\\u00B0]+$/;\n	if(whiteListCharsRegEx.test(input)){\n		return originalInput;\n	} else {\n		return \"\";\n	}\n}\n\n\n";
      }
      public String getName() {
        return "scriptUtil";
      }
    }
    ;
  }
  private static class scriptUtilInitializer {
    static {
      _instance0.scriptUtilInitializer();
    }
    static com.google.gwt.resources.client.TextResource get() {
      return scriptUtil;
    }
  }
  public com.google.gwt.resources.client.TextResource scriptUtil() {
    return scriptUtilInitializer.get();
  }
  private static java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype> resourceMap;
  private static com.google.gwt.resources.client.TextResource scriptUtil;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      scriptUtil(), 
    };
  }
  public ResourcePrototype getResource(String name) {
    if (GWT.isScript()) {
      return getResourceNative(name);
    } else {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<java.lang.String, com.google.gwt.resources.client.ResourcePrototype>();
        resourceMap.put("scriptUtil", scriptUtil());
      }
      return resourceMap.get(name);
    }
  }
  private native ResourcePrototype getResourceNative(String name) /*-{
    switch (name) {
      case 'scriptUtil': return this.@org.gwtopenmaps.openlayers.client.OpenLayers.Scripts::scriptUtil()();
    }
    return null;
  }-*/;
}
