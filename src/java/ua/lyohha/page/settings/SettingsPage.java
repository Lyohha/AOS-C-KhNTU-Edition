package ua.lyohha.page.settings;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ua.lyohha.language.Language;
import ua.lyohha.language.LanguageChangeEvent;
import ua.lyohha.page.Page;

public class SettingsPage extends Page implements LanguageChangeEvent {
    public ComboBox themeComboBox;
    private String styleClass = "/assets/page/settings/SettingsPage.css";
    private String page = "/assets/page/settings/SettingsPage.fxml";

    public Button backButton;
    public Label menuNameLabel;
    public Label languageLabel;
    public Label themeLabel;
    public ComboBox languageComboBox;

    @Override
    public Parent getParent() {
        return null;
    }

    @Override
    public String getStyleClass() {
        return styleClass;
    }

    @Override
    public String getPage() {
        return page;
    }

    @Override
    public void initializeComponent() {
        languageComboBox.getItems().clear();
        String[] languages = Language.getLanguages();
        for (String s : languages)
            languageComboBox.getItems().add(s);
        languageComboBox.setValue(Language.getLanguage());
        Language.addEvent(this);
        setLocalizedNames();
    }

    public void languageComboBoxSelectionChanged(ActionEvent actionEvent) {
        Language.setLanguage((String) languageComboBox.getValue());
    }

    public void themeComboBoxSelectionChanged(ActionEvent actionEvent) {
    }

    public void backButtonClick(ActionEvent actionEvent) {
        Language.removeEvent(this);
        navigation.navigateBack();
    }

    private void setLocalizedNames() {
        menuNameLabel.setText(Language.getLocalized("settings_menu.name"));
        themeLabel.setText(Language.getLocalized("settings_menu.theme.name"));
        languageLabel.setText(Language.getLocalized("settings_menu.language.name"));
        backButton.setText(Language.getLocalized("settings_menu.back_button.text"));

        themeComboBox.getItems().clear();
        themeComboBox.getItems().addAll(Language.getLocalized("settings_menu.theme_dark.name"), Language.getLocalized("settings_menu.theme_light.name"));
        themeComboBox.setValue(Language.getLocalized("settings_menu.theme_dark.name"));
    }

    @Override
    public void onLanguageChange() {
        setLocalizedNames();
    }
}
