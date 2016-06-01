package es.jclm.cs.rarasclm.util;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import es.jclm.cs.rarasclm.entities.BaseGrillaModelView;


public class RenderJqGrid<T> {
	
	private String selectorCss;
	private BaseGrillaModelView<T> baseModel;
	private List<String> columnasId = new ArrayList<String>();
	private Class<T> classT;
	private String url;

	
	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  RenderJqGrid(String selectorCss,BaseGrillaModelView<T> baseGrilla, String url) {
		this.selectorCss = selectorCss;
		this.baseModel = baseGrilla;
		this.url=url;
		if(baseGrilla.getRegistros()!=null && baseGrilla.getRegistros().size()>0 ) {
			classT = (Class<T>)baseGrilla.getRegistros().get(0).getClass();
			for(Method m : classT.getMethods()) {
				if(m.getName().substring(0,3).equals("get")) {
					columnasId.add(m.getName().substring(3,4).toLowerCase() + m.getName().substring(4));
				}
			}
		} else {
			classT=null;
		}
	}
	
	
	public String renderColModel(int nTabs, List<String> labels, List<String> widths, String key) {
		String tabs="";
		String tabs_1 ="";
		
		for(int i=0;i<nTabs;i++)
			tabs = tabs + "\t";
		
		for(int i=0;i<nTabs-1;i++)
			tabs_1 = tabs_1 + "\t";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for(String col : columnasId)
		{
			sb.append(tabs).append("{ label: ").append("'").append(labels.get(i)).append("', ");
			sb.append("name: '").append(col).append("' ");
			if(col.equals(key))
				sb.append("key: true, ");
			sb.append("width: ").append(widths.get(0)).append(" }\n");
		}
		sb.append(tabs_1).append("]");
		return sb.toString();
	}
	
	public String getJsGrid(List<String> labels, List<String> widths, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("$('").append(selectorCss).append("').jqGrid({");
		sb.append("\t").append("url: '").append(this.url).append("',\n");
		sb.append("\t").append("mtype: '").append("GET").append("',\n");
		sb.append("\t").append("datatype: '").append("jsonp").append("',\n");
		sb.append("\t").append("colModel: ").append(renderColModel(2, labels, widths, key)).append("\n");
		return sb.toString();
	}
	
	
}
