package steps.adminPanel;

import io.cucumber.java.en.And;

public class LayoutPageSteps {
    private final LayoutPage layoutPage;

    public LayoutPageSteps(LayoutPage layoutPage) {
        this.layoutPage = layoutPage;
    }

    @And("Устанавливаем макет {string} по умолчанию")
    public void setLayout_asDefault(String layoutName){
        layoutPage.setLayout_asDefault(layoutName);
    }

    @And("Настраиваем блок {string} в виде {string} списка")
    public void setBlockListType(String blockName, String listType) {
        layoutPage.setBlockListType(blockName, listType);
    }
}