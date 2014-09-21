package uk.ac.rdg.resc.charme.client;

import java.util.List;

import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.control.Graticule;
import org.gwtopenmaps.openlayers.client.control.GraticuleOptions;
import org.gwtopenmaps.openlayers.client.symbolizer.LineSymbolizer;
import org.gwtopenmaps.openlayers.client.symbolizer.LineSymbolizerOptions;
import org.gwtopenmaps.openlayers.client.symbolizer.TextSymbolizer;
import org.gwtopenmaps.openlayers.client.symbolizer.TextSymbolizerOptions;

import uk.ac.rdg.resc.godiva.client.Godiva;
import uk.ac.rdg.resc.godiva.client.requests.ConnectionException;
import uk.ac.rdg.resc.godiva.client.requests.LayerTreeJSONParser;
import uk.ac.rdg.resc.godiva.shared.LayerMenuItem;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class CharmeMaps extends Godiva {
	
	public void init() {
		super.init();
		
		/*
		 * Add controls to map and do anything you need to do here.
		 * 
		 * i.e. this is your entry point.
		 */
		addCharmeAnnotations();
	}
	
    private void addCharmeAnnotations() {
    	Map map = mapArea.getMap();
    	
    	/*
    	 * Example - adds lat-lon grid
    	 */
    	GraticuleOptions options = new GraticuleOptions();
        TextSymbolizerOptions textOptions = new TextSymbolizerOptions();
        textOptions.setFontSize("8pt");
        options.setLabelSymbolizer(new TextSymbolizer(textOptions));
        LineSymbolizerOptions lineOptions = new LineSymbolizerOptions();
        lineOptions.setStrokeOpacity(0.5);
        lineOptions.setStrokeWidth(1);
        options.setLineSymbolyzer(new LineSymbolizer(lineOptions));
        options.setNumPoints(100);
        Graticule graticule = new Graticule(options);
        graticule.setAutoActivate(true);
        graticule.getJSObject().setProperty("labelFormat", "d");
        map.addControl(graticule);
        graticule.activate();
        
        List<String> availableDates = widgetCollection.getTimeSelector().getAvailableDates();
        widgetCollection.getTimeSelector().getSelectedDateTime();
        
        widgetCollection.getWmsUrlProvider().getSelectedId();
        String thisIsTheURLofTheDataset = widgetCollection.getMoreInfo().getInfo();
	}

    @Override
    protected void menuLoaded(LayerMenuItem menuTree) {
    	super.menuLoaded(menuTree);
    	// ID of loaded layer
    	menuTree.getId();
    	// if layer can be plotted
    	menuTree.isPlottable();
    }
    
	@Override
    protected void requestAndPopulateMenu() {
        /*
         * This is where we define the fact that we are working with a single
         * local server
         */
        final String wmsUrl = "http://cera.rdg.ac.uk/godiva3/wms";
        final RequestBuilder getMenuRequest = new RequestBuilder(RequestBuilder.GET,
                getUrlFromGetArgs(wmsUrl, "?request=GetMetadata&item=menu"));
        getMenuRequest.setCallback(new RequestCallback() {
            @Override
            public void onResponseReceived(Request req, Response response) {
                try {
                    if (response.getStatusCode() != Response.SC_OK) {
                        throw new ConnectionException("Error contacting server");
                    }
                    /*
                     * Once the menu has been received, parse it, and call
                     * menuLoaded()
                     * 
                     * This is a separate method so that subclasses can change
                     * the behaviour once the menu is loaded
                     */
                    JSONValue jsonMap = JSONParser.parseLenient(response.getText());
                    JSONObject parentObj = jsonMap.isObject();
                    LayerMenuItem menuTree = LayerTreeJSONParser.getTreeFromJson(wmsUrl, parentObj);

                    menuLoaded(menuTree);
                } catch (Exception e) {
                    invalidJson(e, response.getText(), getMenuRequest.getUrl());
                } finally {
                    setLoading(false);
                }
            }

            @Override
            public void onError(Request request, Throwable e) {
                setLoading(false);
                handleError(e);
            }
        });

        try {
            setLoading(true);
            getMenuRequest.send();
        } catch (RequestException e) {
            handleError(e);
        }
    }

}
