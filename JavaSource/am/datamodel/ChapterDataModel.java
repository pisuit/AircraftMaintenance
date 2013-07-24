package am.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import am.model.Chapter;

public class ChapterDataModel extends ListDataModel<Chapter> implements SelectableDataModel<Chapter> {

	public ChapterDataModel(List<Chapter> data) {
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Chapter getRowData(String rowKey) {
		
		List<Chapter> chapters = (List<Chapter>) getWrappedData();
		
		for(Chapter chapter : chapters){
			if(chapter.getId().equals(rowKey)) return chapter;
		}
		return null;
	}

	@Override
	public Object getRowKey(Chapter chapter) {
		return chapter.getId();
	}
	
}
