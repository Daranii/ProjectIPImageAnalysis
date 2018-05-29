/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

/**
 *
 * @author Andrew
 */
public class JsonTransform {
    private String filepath;
    private String classs;
    private String concept;
    private String metadata;
    private String classmatching;
    private String conceptmatching;
    private String others;
    private String description;
    
    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
    
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getClassmatching() {
        return classmatching;
    }

    public void setClassmatching(String classmatching) {
        this.classmatching = classmatching;
    }

    public String getConceptmatching() {
        return conceptmatching;
    }

    public void setConceptmatching(String conceptmatching) {
        this.conceptmatching = conceptmatching;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
