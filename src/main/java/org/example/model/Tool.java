package org.example.model;

public class Tool {
    private String toolCode;
    private String toolType;
    private String toolBrand;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    @Override
    public String toString() {
        return "{" +
                "toolCode='" + toolCode + '\'' +
                ", toolType='" + toolType + '\'' +
                ", toolBrand='" + toolBrand + '\'' +
                '}';
    }
}
