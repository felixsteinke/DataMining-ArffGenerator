package generator;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public abstract class GeneratorPreprocessor {

    protected String title;
    protected ArrayList<String> attributeList;
    protected ArrayList<String> dataList;

    public GeneratorPreprocessor() {
        this.title = "defaultTitle";
        this.attributeList = new ArrayList<>();
        this.dataList = new ArrayList<>();
    }

    protected abstract void processAttributeList();

    protected abstract void processDataList();
}
